package com.sadsoft.functionextremesfinder.population;

import com.sadsoft.functionextremesfinder.population.individual.Individual;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PopulationImpl implements Population {

    private final PopulationInitializer initializer;
    private List<Individual> population;

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

    @Override
    public String toString() {
        String result = "";
        for (Individual individual: population) {
            result += individual.toString() + "\n";
        }
        return result;
    }
}
