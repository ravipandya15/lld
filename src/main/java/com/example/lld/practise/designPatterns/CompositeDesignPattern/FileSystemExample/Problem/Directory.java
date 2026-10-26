package com.example.lld.practise.designPatterns.CompositeDesignPattern.FileSystemExample.Problem;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    String dirName;
    List<Object> objectList;

    public Directory(String dirName) {
        this.dirName = dirName;
        objectList = new ArrayList<>();
    }

    public void add(Object object) {
        this.objectList.add(object);
    }

    public void ls() {
        System.out.println("Directory name: " + dirName);

        for (Object object: objectList) {
            if (object instanceof File) {
                ((File)object).ls();
            } else if (object instanceof Directory) {
                ((Directory)object).ls();
            }
        }
    }
}
