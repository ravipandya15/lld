package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.IteratorDesignPatterns;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Science", 900));
        bookList.add(new Book("Physics", 760));
        bookList.add(new Book("Chemistry", 590));
        bookList.add(new Book("Biology", 600));

        Aggregator library = new Library(bookList);
        Iterator it = library.createIterator();

        while(it.hasNext()) {
            Book book = (Book)it.next();
            System.out.println("price of the book is: " + book.getPrice());
        }
    }
}
