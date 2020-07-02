package com.sadsoft.functionextremesfinder.population;

import com.sadsoft.functionextremesfinder.GeneticAlgorithm.GeneticAlgorithmProperties;
import com.sadsoft.functionextremesfinder.population.individual.Individual;
import com.sadsoft.functionextremesfinder.population.individual.IndividualImpl;
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
    private final GeneticAlgorithmProperties properties;

    public PopulationInitializerImpl(GeneticAlgorithmProperties properties) {
        this.properties = properties;
    }

    @Override
    public List<Individual> initialize() {
        log.debug("Population initializer starts...");
        int genesLength = Integer
                .toBinaryString(properties.getMaxRange())
                .length();
        List<Individual> result = new ArrayList<>();
        for (int i=0; i<properties.getPopulationSize(); i++) {
            log.debug("Individual no.{} initializing", i);
            int[] genes = new int[genesLength];
            for (int j=0; j<genesLength; j++) {
                genes[j] = RandomGenerator.generateRandom(0,1);
            }
            log.debug("... with genes: {}", Arrays.toString(genes));
            result.add(new IndividualImpl(genes));
        }
        log.debug("Population initializer stops...");
        return result;
    }


}
