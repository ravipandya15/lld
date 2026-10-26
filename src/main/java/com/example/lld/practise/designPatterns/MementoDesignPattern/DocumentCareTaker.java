package com.example.lld.practise.designPatterns.MementoDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class DocumentCareTaker {
    List<DocumentMememto> documentMementoList;

    public DocumentCareTaker() {
        this.documentMementoList = new ArrayList<>();
    }

    public void addMemento(DocumentMememto memento) {
        this.documentMementoList.add(memento);
    }

    public DocumentMememto getMemento(int index) {
        if (index >= documentMementoList.size()) {
            System.out.println("Invalid index provided to getMemento");
            return null;
        }
        return this.documentMementoList.get(index);
    }
}
