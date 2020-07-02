package com.sadsoft.functionextremesfinder.population;

import com.sadsoft.functionextremesfinder.population.individual.Individual;

import java.util.List;

public class PopulationImpl implements Population {

    private List<Individual> population;
    private final PopulationInitializer initializer;

    public PopulationImpl(PopulationInitializer initializer) {
        this.initializer = initializer;
        population = initializer.initialize();
    }

    @Override
    public List<Individual> getPopulation() {
        return population;
    }

    @Override
    public Individual getIndividual(int index) {
        return population.get(index);
    }
}
