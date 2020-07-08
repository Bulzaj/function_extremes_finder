package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PopulationInitializerImpl implements PopulationInitializer {

    private static final Logger log = LoggerFactory.getLogger(PopulationInitializerImpl.class);

    @Override
    public Population initialize(int maxRange, int populationSize) {
        log.debug("Population initializer starts...");
        int genesLength = Integer
                .toBinaryString(maxRange)
                .length();
        List<Individual> individuals = new ArrayList<>();
        for (int i=0; i<populationSize; i++) {
            log.debug("Individual no.{} initializing", i);
            int[] genes = new int[genesLength];
            for (int j=0; j<genesLength; j++) {
                genes[j] = RandomGenerator.generateRandom(0,1);
            }
            log.debug("... with genes: {}", Arrays.toString(genes));
            individuals.add(new Individual(genes));
        }
        if (individuals.isEmpty()) throw new IllegalStateException("Individual list is empty");
        log.debug("Population initializer stop...");
        return new Population(individuals);
    }


}
