package com.sadsoft.functionextremesfinder.model;

import java.util.Arrays;

public class Individual  {

    private int[] genes;
    private double x;
    private double value;
    private double fitness;

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Genes: " + Arrays.toString(genes) + " x: " + getValue() + " Value: " + getValue() + " Fitness: " + getFitness();
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
