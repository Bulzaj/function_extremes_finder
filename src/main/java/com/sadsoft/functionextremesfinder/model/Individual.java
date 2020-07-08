package com.sadsoft.functionextremesfinder.model;

import java.util.Arrays;
import java.util.Objects;

public class Individual  {

    private int[] genes;
    private double value;
    double fitness;

    public Individual(int[] genes) {
        validateGenes(genes);
        this.genes = genes;
        fitness = 0;
        countValue();
    }

    public double getValue() {
        return value;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int[] getGenes() {
        return genes;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return "Genes: " + Arrays.toString(genes) + " Value: " + getValue() + " Fitness: " + getFitness();
    }

    private void validateGenes(int[] genes) {
        if (genes == null) throw new NullPointerException("Genes array can't be null");
        for(int gene : genes) {
            if (gene != 1 && gene != 0) {
                throw new IllegalArgumentException("Genes can have values 1 or 0");
            }
        }
    }

    private void countValue() {
        double result = 0;
        int j=0;
        for (int i=genes.length-1; i>=0; i--) {
            result += genes[i]*Math.pow(2, j);
            j++;
        }
        value = result;
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
