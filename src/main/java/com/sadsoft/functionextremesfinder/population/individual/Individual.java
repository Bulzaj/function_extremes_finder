package com.sadsoft.functionextremesfinder.population.individual;

import org.springframework.stereotype.Component;

@Component
public interface Individual {

    double getValue();
    int[] getGenes();
    double getFitness();
}
