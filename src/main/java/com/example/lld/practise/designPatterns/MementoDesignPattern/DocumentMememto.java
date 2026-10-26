package com.example.lld.practise.designPatterns.MementoDesignPattern;

public class DocumentMememto {

    private final String content;

    public DocumentMememto(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return this.content;
    }
}
