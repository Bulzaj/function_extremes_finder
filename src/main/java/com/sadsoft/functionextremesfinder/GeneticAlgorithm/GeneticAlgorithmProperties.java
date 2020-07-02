package com.sadsoft.functionextremesfinder.GeneticAlgorithm;

import org.springframework.stereotype.Component;

@Component
public interface GeneticAlgorithmProperties {

    int getMinRange();
    int getMaxRange();
    int getPopulationSize();
    int getIterationsCount();
}
