package com.example.lld.practise.LLD_Questions.LogParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParserApp {

    // Represents a successfully parsed log record.
    record LogRecord(
            String timestamp,
            String level,
            String message
    ) {}

    // Parses and validates individual log lines.
    static class LogParser {

        private static final Pattern LOG_PATTERN = Pattern.compile(
                "^(\\d{4}-\\d{2}-\\d{2})\\s+" +
                        "(\\d{2}:\\d{2}:\\d{2})\\s+" +
                        "([A-Za-z][A-Za-z0-9_-]*)\\s+" +
                        "(.+)$"
        );

        public Optional<LogRecord> parse(String line) {

            if (line == null || line.isBlank()) {
                return Optional.empty();
            }

            Matcher matcher = LOG_PATTERN.matcher(line);

            if (!matcher.matches()) {
                return Optional.empty();
            }

            String timestamp = matcher.group(1) + " " + matcher.group(2);
            String level = matcher.group(3).toUpperCase();
            String message = matcher.group(4).trim();

            if (message.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(
                    new LogRecord(timestamp, level, message)
            );
        }
    }

    // Processes the file using streaming.
    static class LogFileProcessor {

        private final LogParser parser;

        // Supports any log level without modifying the core logic.
        private final Map<String, Long> levelCounts =
                new LinkedHashMap<>();

        private long totalRecords;
        private long malformedRecords;

        // Keep only a limited number of malformed samples.
        private static final int MAX_MALFORMED_SAMPLES = 5;

        private final java.util.List<String> malformedSamples =
                new java.util.ArrayList<>();

        public LogFileProcessor(LogParser parser) {
            this.parser = parser;
        }

        public void process(Path filePath) throws IOException {

            try (BufferedReader reader = Files.newBufferedReader(
                    filePath, StandardCharsets.UTF_8)) {

                String line;

                while ((line = reader.readLine()) != null) {

                    totalRecords++;

                    Optional<LogRecord> record = parser.parse(line);

                    if (record.isEmpty()) {

                        malformedRecords++;

                        if (malformedSamples.size()
                                < MAX_MALFORMED_SAMPLES) {
                            malformedSamples.add(line);
                        }

                        continue;
                    }

                    String level = record.get().level();

                    // Generic counting logic for every log level.
                    levelCounts.merge(level, 1L, Long::sum);
                }
            }
        }

        public void printReport() {

            System.out.println("\n===== LOG ANALYSIS REPORT =====");

            System.out.println("Total records: " + totalRecords);

            System.out.println("\nLog Level Counts:");

            // Always display the three requested levels.
            printCount("INFO");
            printCount("WARN");
            printCount("ERROR");

            // Display any additional levels dynamically.
            System.out.println("\nOther Log Levels:");

            levelCounts.entrySet().stream()
                    .filter(entry ->
                            !entry.getKey().equals("INFO") &&
                                    !entry.getKey().equals("WARN") &&
                                    !entry.getKey().equals("ERROR"))
                    .forEach(entry ->
                            System.out.println(
                                    entry.getKey() + " = " + entry.getValue()
                            ));

            System.out.println(
                    "\nMalformed records: " + malformedRecords
            );

            if (!malformedSamples.isEmpty()) {
                System.out.println("\nMalformed Record Samples:");

                malformedSamples.forEach(
                        sample -> System.out.println(sample)
                );
            }

            System.out.println("===============================");
        }

        private void printCount(String level) {
            System.out.println(
                    level + " = " + levelCounts.getOrDefault(level, 0L)
            );
        }

        public Map<String, Long> getLevelCounts() {
            return Map.copyOf(levelCounts);
        }

        public long getMalformedRecords() {
            return malformedRecords;
        }
    }

    public static void main(String[] args) {

        // Pass file path as a command-line argument.
        // Otherwise use application.log.
        Path filePath = Path.of(
                args.length > 0 ? args[0] : "/Users/ravipandya/Projects/lld/src/main/java/com/example/lld/practise/LLD_Questions/LogParser/application.log"
        );

        LogParser parser = new LogParser();

        LogFileProcessor processor =
                new LogFileProcessor(parser);

        try {
            processor.process(filePath);
            processor.printReport();

        } catch (IOException e) {
            System.err.println(
                    "Failed to process log file: " + e.getMessage()
            );
        }
    }
}

