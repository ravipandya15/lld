package com.example.lld.practise.designPatterns.FlyweightDesignPattern.WordProcessor.Problem;

public class Character {

    char character;
    String fontType;
    int size;
    int row;
    int col;

    Character(char character, String fontType, int size, int row, int col) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
        this.row = row;
        this.col = col;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
