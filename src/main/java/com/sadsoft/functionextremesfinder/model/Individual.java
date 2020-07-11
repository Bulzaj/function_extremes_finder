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

    private void validateGenes(int[] genes) {
        if (genes == null) throw new NullPointerException("Genes array can't be null");
        for(int gene : genes) {
            if (gene != 1 && gene != 0) {
                throw new IllegalArgumentException("Genes can have values 1 or 0");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return Arrays.equals(genes, that.genes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(genes);
    }
}
