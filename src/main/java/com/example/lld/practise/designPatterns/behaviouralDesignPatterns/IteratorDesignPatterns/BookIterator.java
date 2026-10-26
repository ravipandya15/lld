package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.IteratorDesignPatterns;

import java.util.List;

public class BookIterator implements Iterator {

    private final List<Book> bookList;
    private int index;

    public BookIterator(List<Book> bookList) {
        this.bookList = bookList;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookList.size();
    }

    @Override
    public Object next() { // return type can be Book as well
        if (this.hasNext()) {
            return bookList.get(index++);
        }
        return null;
    }
}
