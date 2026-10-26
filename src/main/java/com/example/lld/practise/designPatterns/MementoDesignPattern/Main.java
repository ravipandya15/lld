package com.example.lld.practise.designPatterns.MementoDesignPattern;

public class Main {
    public static void main(String[] args) {
        DocumentOriginator document = new DocumentOriginator("Initial Content");
        DocumentCareTaker history = new DocumentCareTaker();

        // save content in word document
        history.addMemento(document.createMemento());
        // connent is saved

        System.out.println("Current Saved content is: " + document.getContent());

        System.out.println("Adding new content:");
        document.write(" New Data");
        history.addMemento(document.createMemento()); // new content is saved.

        System.out.println("Current Saved content is: " + document.getContent());

        System.out.println("Adding new content:");
        document.write(" Last Connent");
        history.addMemento(document.createMemento()); // Last Content is saved.

        System.out.println("Current Saved content is: " + document.getContent());

        // restore previous memento
        System.out.println("undo Operation:");
        document.restoreMemento(history.getMemento(1));
        System.out.println("Current saved content is: " + document.getContent());
    }
}
