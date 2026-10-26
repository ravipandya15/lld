package com.example.lld.practise.designPatterns.MementoDesignPattern;

public class DocumentOriginator {

    String content;

    public DocumentOriginator(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void write(String text) {
        this.content += text;
    }

    public DocumentMememto createMemento() {
        return new DocumentMememto(this.content);
    }

    public void restoreMemento(DocumentMememto documentMememto) {
        this.content = documentMememto.getSavedContent();
    }
}
