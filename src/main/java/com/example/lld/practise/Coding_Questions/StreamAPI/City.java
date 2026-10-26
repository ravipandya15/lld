package com.example.lld.practise.Coding_Questions.StreamAPI;

import lombok.Data;

@Data
public class City {
    private String name;
    private int population;

    public City() {}

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }
}
