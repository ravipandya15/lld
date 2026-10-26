package com.example.lld.practise.designPatterns.CompositeDesignPattern.FileSystemExample.Solution;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {
    String dirName;
    List<FileSystem> fileSystems; // as FileSystem can be either directory or File.

    public Directory(String dirName) {
        this.dirName = dirName;
        fileSystems = new ArrayList<>();
    }

    public void add(FileSystem fileSystem) {
        this.fileSystems.add(fileSystem);
    }

    public void ls() {
        System.out.println("Directory name: " + dirName);
        for (FileSystem fileSystem: fileSystems) {
            fileSystem.ls();
        }
    }
}
