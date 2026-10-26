package com.example.lld.practise.Coding_Questions.StreamAPI;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Country {
    private String name;
    private List<City> citiesList;

    public Country(String name) {
        this.name = name;
        citiesList = new ArrayList<>();
    }

    public Country(String name, List<City> list) {
        this.name = name;
        this.citiesList = list;
    }

    public void addCity(City city) {
        citiesList.add(city);
    }
}
