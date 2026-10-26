package com.example.lld.practise.designPatterns.CompositeDesignPattern.FileSystemExample.Solution;

public class Main {

    public static void main(String[] args) {
        Directory comedyMovie = new Directory("comedyMovie");
        File hulchal = new File("Hulchal");
        File DeDanaDan = new File("DeDanaDan");
        comedyMovie.add(hulchal);
        comedyMovie.add(DeDanaDan);

        File border = new File("Border");
        Directory movie = new Directory("Movie");
        movie.add(border);
        movie.add(comedyMovie);
        movie.ls();
    }
}
