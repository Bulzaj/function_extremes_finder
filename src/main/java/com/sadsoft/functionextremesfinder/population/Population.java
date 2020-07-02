package com.sadsoft.functionextremesfinder.population;

import com.sadsoft.functionextremesfinder.population.individual.Individual;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Population {

    List<Individual> getPopulation();
    Individual getIndividual(int index);
    
}
