package com.sadsoft.functionextremesfinder.model;

import java.util.Arrays;

public class Individual  {

    private int[] genes;
    private int x;
    private float value;
    private float fitness;

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Genes: " + Arrays.toString(genes) + " x: " + getX() + " Value: " + getValue() + " Fitness: " + getFitness();
    }
}
