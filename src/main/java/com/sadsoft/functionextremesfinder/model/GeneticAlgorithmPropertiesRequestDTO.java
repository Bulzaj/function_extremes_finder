package com.sadsoft.functionextremesfinder.model;

public class GeneticAlgorithmPropertiesRequestDTO {

    private String functionBody;
    private int minRange;
    private int maxRange;
    private int populationSize;
    private int maxIterations;
    private int maxWithoutChanges;
    private String selectorType;
    private int crossoverChance;
    private int mutationChance;

    public GeneticAlgorithmPropertiesRequestDTO(String functionBody, int minRange, int maxRange, int populationSize, int maxIterations, int maxWithoutChanges, String selectorType, int crossoverChance, int mutationChance) {
        this.functionBody = functionBody;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.populationSize = populationSize;
        this.maxIterations = maxIterations;
        this.maxWithoutChanges = maxWithoutChanges;
        this.selectorType = selectorType;
        this.crossoverChance = crossoverChance;
        this.mutationChance = mutationChance;
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

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public int getMaxWithoutChanges() {
        return maxWithoutChanges;
    }

    public void setMaxWithoutChanges(int maxWithoutChanges) {
        this.maxWithoutChanges = maxWithoutChanges;
    }

    public String getSelectorType() {
        return selectorType;
    }

    public void setSelectorType(String selectorType) {
        this.selectorType = selectorType;
    }

    public int getCrossoverChance() {
        return crossoverChance;
    }

    public void setCrossoverChance(int crossoverChance) {
        this.crossoverChance = crossoverChance;
    }

    public int getMutationChance() {
        return mutationChance;
    }

    public void setMutationChance(int mutationChance) {
        this.mutationChance = mutationChance;
    }
}
