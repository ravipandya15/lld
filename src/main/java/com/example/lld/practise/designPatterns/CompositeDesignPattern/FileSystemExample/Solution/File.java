package com.example.lld.practise.designPatterns.CompositeDesignPattern.FileSystemExample.Solution;

public class File implements FileSystem {
    String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public void ls() {
        System.out.println("File name: " + fileName);
    }
}
