package com.example.lld.practise.designPatterns.FlyweightDesignPattern.WordProcessor.Solution;


import java.util.Map;
import java.util.HashMap;

public class LetterFactory {

    private static final Map<Character, ILetter> characterCache = new HashMap<>();

    public static ILetter createLetter(char character) {

        if (characterCache.containsKey(character)) {
            return characterCache.get(character);
        }

        DocumentCharacter documentCharacter = new DocumentCharacter(character, "Arial", 10);
        characterCache.put(character, documentCharacter);
        return documentCharacter;
    }
}
