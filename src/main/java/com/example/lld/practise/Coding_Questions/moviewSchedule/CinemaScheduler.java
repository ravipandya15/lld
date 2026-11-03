package com.example.lld.practise.Coding_Questions.moviewSchedule;


import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public final class CinemaScheduler {

    private static final LocalTime OPEN =
            LocalTime.of(10, 0);

    private static final LocalTime CLOSE =
            LocalTime.of(23, 0);

    // Key: movie start time, Value: movie schedule
    private final TreeMap<LocalTime, MovieSchedule> schedule = new TreeMap<>();

    public record MovieSchedule(
            LocalTime startTime,
            LocalTime endTime
    ) {
        public MovieSchedule {
            Objects.requireNonNull(startTime);
            Objects.requireNonNull(endTime);

            if (!startTime.isBefore(endTime)) {
                throw new IllegalArgumentException(
                        "Start time must be before end time"
                );
            }

            if (startTime.isBefore(OPEN) ||
                    endTime.isAfter(CLOSE)) {
                throw new IllegalArgumentException(
                        "Movie must be between 10 AM and 11 PM"
                );
            }
        }
    }

    // O(log N)
    public synchronized boolean canSchedule(MovieSchedule movie) {

        Objects.requireNonNull(movie);

        // Find the closest movie starting at or before candidate
        Map.Entry<LocalTime, MovieSchedule> previous =
                schedule.floorEntry(movie.startTime());

        // Find the closest movie starting at or after candidate
        Map.Entry<LocalTime, MovieSchedule> next =
                schedule.ceilingEntry(movie.startTime());

        // Check previous movie
        if (previous != null &&
                previous.getValue().endTime()
                        .isAfter(movie.startTime())) {
            return false;
        }

        // Check next movie
        if (next != null &&
                next.getValue().startTime()
                        .isBefore(movie.endTime())) {
            return false;
        }

        return true;
    }

    // Atomic check + booking
    public synchronized boolean scheduleMovie(MovieSchedule movie) {

        if (!canSchedule(movie)) {
            return false;
        }

        schedule.put(movie.startTime(), movie);

        return true;
    }

    public synchronized List<MovieSchedule> getSchedule() {
        return List.copyOf(schedule.values());
    }

    private static MovieSchedule movie(String start, String end) {
        return new MovieSchedule(
                LocalTime.parse(start),
                LocalTime.parse(end)
        );
    }

    static void main() {
        CinemaScheduler scheduler = new CinemaScheduler();

        System.out.println("===== Cinema Scheduling Tests =====");

        // 1. First movie
        System.out.println("First movie: " +
                scheduler.scheduleMovie(movie("10:00", "12:00")));
        // true

        // 2. Back-to-back movie
        System.out.println("Back-to-back: " +
                scheduler.scheduleMovie(movie("12:00", "14:00")));
        // true

        // 3. Overlap with previous movie
        System.out.println("Overlap previous: " +
                scheduler.scheduleMovie(movie("11:00", "13:00")));
        // false

        // 4. Back-to-back with next movie
        System.out.println("Back-to-back next: " +
                scheduler.scheduleMovie(movie("14:00", "16:00")));
        // true

        // 5. Overlap with next movie
        System.out.println("Overlap next: " +
                scheduler.scheduleMovie(movie("15:00", "17:00")));
        // false

        // 6. Schedule in available gap
        System.out.println("Schedule in gap: " +
                scheduler.scheduleMovie(movie("16:00", "17:00")));
        // true

        // 7. End of day
        System.out.println("End of day: " +
                scheduler.scheduleMovie(movie("21:00", "23:00")));
        // true

        // 8. Overlap multiple movies
        System.out.println("Overlap multiple: " +
                scheduler.scheduleMovie(movie("11:00", "18:00")));
        // false

        // 9. Invalid operating hours
        try {
            scheduler.scheduleMovie(movie("09:00", "11:00"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid opening time: PASS");
        }

        // 10. Invalid duration
        try {
            scheduler.scheduleMovie(movie("15:00", "14:00"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid duration: PASS");
        }

        // Print final schedule
        System.out.println("\n===== Final Schedule =====");

        for (MovieSchedule m : scheduler.getSchedule()) {
            System.out.println(m.startTime() + " - " + m.endTime());
        }
    }
}

