package com.sadsoft.functionextremesfinder.model;

import java.util.List;

public class Population {

    private List<Individual> population;

    public Population(List<Individual> population) {
        this.population = population;
    }

    public Population() {

    }

    public List<Individual> getPopulation() {
        return population;
    }

    public void setPopulation(List<Individual> population) {
        this.population = population;
    }

    public String toString() {
        String result = "";
        for (Individual individual: population) {
            result += individual.toString() + "\n";
        }
        return result;
    }
}
