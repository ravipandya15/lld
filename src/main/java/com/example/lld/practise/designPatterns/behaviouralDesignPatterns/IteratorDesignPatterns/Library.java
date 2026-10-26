package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.IteratorDesignPatterns;

import java.util.List;

public class Library implements Aggregator {
    private final List<Book> bookList;

    public Library(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public Iterator createIterator() {
        return new BookIterator(this.bookList);
    }
}
