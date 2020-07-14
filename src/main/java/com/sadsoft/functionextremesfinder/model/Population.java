package com.sadsoft.functionextremesfinder.model;

public class Population {

    private Individual[] population;

    public Population(Individual[] population) {
        this.population = population;
    }

    public Population() { }

    public Individual[] getPopulation() {
        return population;
    }

    public void setPopulation(Individual[] population) {
        this.population = population;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Individual individual: population) {
            result.append(individual.toString()).append("\n");
        }
        return result.toString();
    }
}
