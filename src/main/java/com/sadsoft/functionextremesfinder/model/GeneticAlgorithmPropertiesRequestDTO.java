package com.sadsoft.functionextremesfinder.model;

public class GeneticAlgorithmPropertiesRequestDTO {

    private String functionBody;
    private int minRange;
    private int maxRange;
    private int populationSize;

    public GeneticAlgorithmPropertiesRequestDTO(String functionBody, int minRange, int maxRange, int populationSize) {
        this.functionBody = functionBody;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.populationSize = populationSize;
    }

    public GeneticAlgorithmPropertiesRequestDTO() {
    }

    public String getFunctionBody() {
        return functionBody;
    }

    public void setFunctionBody(String functionBody) {
        this.functionBody = functionBody;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
}
