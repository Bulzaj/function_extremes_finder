package com.sadsoft.functionextremesfinder.population.individual;

public class IndividualImpl implements Individual {

    private int[] genes;

    public IndividualImpl(int[] genes) {
        validateGenes(genes);
        this.genes = genes;

    }

    @Override
    public double getFitness() {
        double result = 0;
        int j=0;
        for (int i=genes.length-1; i>=0; i--) {
            result += genes[i]*Math.pow(2, j);
            j++;
        }
        return result;
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

    private void validateGenes(int[] genes) {
        if (genes == null) throw new NullPointerException("Genes array can't be null");
        for(int gene : genes) {
            if (gene != 1 && gene != 0) {
                throw new IllegalArgumentException("Genes can have values 1 or 0");
            }
        }
    }
}
