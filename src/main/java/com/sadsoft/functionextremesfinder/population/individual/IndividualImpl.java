package com.sadsoft.functionextremesfinder.population.individual;

import java.util.Arrays;

public class IndividualImpl implements Individual {

    private int[] genes;
    double fitness;

    public IndividualImpl(int[] genes) {
        validateGenes(genes);
        this.genes = genes;
        fitness = 0;
    }

    @Override
    public double getValue() {
        double result = 0;
        int j=0;
        for (int i=genes.length-1; i>=0; i--) {
            result += genes[i]*Math.pow(2, j);
            j++;
        }
        return result;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public double getFitness() {
        return fitness;
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
    public String toString() {
        return "Genes: " + Arrays.toString(genes) + " Value: " + getValue();
    }
}
