package com.example.lld.practise.Coding_Questions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Node {
    int value;
    double prob;

    Node (int value, double prob) {
        this.value = value;
        this.prob = prob;
    }
}
public class Text_Justification_68 {
    public static void main(String[] args) {
        PriorityQueue<Node> maxi = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if (b.prob > a.prob) {
                    return 1;
                }
                return -1;
            }
        });
    }


    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        List<String> currentLineWords = new ArrayList<>();
        int currentLength = 0;

        for (int i = 0; i < words.length; i++) {
            if (currentLength + currentLineWords.size() + words[i].length() > maxWidth) {
                // not possible to add word[i] in current line. So prepare currentLine and add it in result.

                StringBuilder currentLine = new StringBuilder();
                int extraSpaces = maxWidth - currentLength;
                int evenlyExtraSpaces = extraSpaces / Math.max(1, currentLineWords.size() - 1);
                int remainderSpaces = extraSpaces % Math.max(1, currentLineWords.size() - 1);
                int maxLoopCount = Math.max(1, currentLineWords.size());
                if (maxLoopCount == 1) {
                    currentLine.append(currentLineWords.get(0));
                    for (int l = 0; l < extraSpaces; l++) {
                        currentLine.append(" ");
                    }
                } else {
                    for (int j = 0; j < maxLoopCount; j++) {
                        currentLine.append(currentLineWords.get(j));

                        if (j < maxLoopCount - 1) {
                            for (int k = 0; k < evenlyExtraSpaces; k++) {
                                currentLine.append(" ");
                            }
                            if (remainderSpaces > 0) {
                                currentLine.append(" ");
                                remainderSpaces--;
                            }
                        }
                    }
                }

                result.add(currentLine.toString());
                currentLineWords = new ArrayList<>();
                currentLength = 0;
            }

            currentLineWords.add(words[i]);
            currentLength += words[i].length();
        }

        StringBuilder lastLine = new StringBuilder();
        int extraSpaces = maxWidth - currentLength;
        for (int k = 0; k < currentLineWords.size(); k++) {
            lastLine.append(currentLineWords.get(k));
            if (k < currentLineWords.size() - 1) {
                lastLine.append(" ");
                extraSpaces--;
            }
        }
        for (int l = 0; l < extraSpaces; l++) {
            lastLine.append(" ");
        }
        result.add(lastLine.toString());

        return result;
    }
}
