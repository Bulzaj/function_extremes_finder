package com.sadsoft.functionextremesfinder.service.genetic_algorithm.population_initializer;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PopulationInitializerImpl implements PopulationInitializer {

    private static final Logger log = LoggerFactory.getLogger(PopulationInitializerImpl.class);

    @Override
    public Population initialize(GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        log.debug("Population initializer starts...");
        int genesLength = Integer
                .toBinaryString(requestDTO.getMaxRange())
                .length();
        List<Individual> individuals = new ArrayList<>();
        for (int i=0; i<requestDTO.getPopulationSize(); i++) {
            int[] genes = new int[genesLength];
            for (int j=0; j<genesLength; j++) {
                genes[j] = Util.generateRandom(0,1);
            }
            int x = countXValue(genes);
            Individual individual = new Individual();
            individual.setGenes(genes);
            individual.setX(x);
            individual.setFitness(0);
            individual.setValue(Util.computeFunctionValueInX(x, requestDTO.getFunctionBody()));
            individuals.add(individual);
        }
        if (individuals.isEmpty()) throw new IllegalStateException("Individual list is empty");
        log.debug("Population initializer stop...");
        return new Population(individuals);
    }

    private int countXValue(int[] genes) {
        int result = 0;
        int j=0;
        for (int i=genes.length-1; i>=0; i--) {
            result += genes[i]*Math.pow(2, j);
            j++;
        }
        return result;
    }
}
