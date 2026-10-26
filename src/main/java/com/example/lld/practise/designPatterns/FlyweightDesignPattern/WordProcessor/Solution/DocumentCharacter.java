package com.example.lld.practise.designPatterns.FlyweightDesignPattern.WordProcessor.Solution;

public class DocumentCharacter implements ILetter {

    private char character;
    private String fontType;
    private int size;

    DocumentCharacter(char character, String fontType, int size) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
    }

    public char getCharacter() {
        return character;
    }

    public String getFontType() {
        return fontType;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void display(int row, int col) {
        // display the character of particular font and size
        // at given location row, col
    }
}
