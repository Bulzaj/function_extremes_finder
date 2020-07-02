package com.sadsoft.functionextremesfinder.GeneticAlgorithm;

public class DefaultGeneticAlgorithmPropertiesImpl implements GeneticAlgorithmProperties {

    @Override
    public int getMinRange() {
        return 0;
    }

    @Override
    public int getMaxRange() {
        return 202;
    }

    @Override
    public int getPopulationSize() {
        return 20;
    }

    @Override
    public int getIterationsCount() {
        return 1000;
    }
}
