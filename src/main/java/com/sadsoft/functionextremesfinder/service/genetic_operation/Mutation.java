package com.sadsoft.functionextremesfinder.service.genetic_operation;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mutation implements GeneticOperator {

    private static final Logger log = LoggerFactory.getLogger(Mutation.class);

    @Override
    public void doOperation(Population population, GeneticAlgorithmPropertiesRequestDTO request) {
        int mutationChance = Util.generateRandom(0, 100);
        if (mutationChance <= request.getMutationChance()) {
            int locus = Util.generateRandom(0, Util.getRangeLength(request.getMaxRange()));
            int randomIndividual = Util.generateRandom(0, Util.getRangeLength(request.getMaxRange())-1);
            log.debug("[Mutation] Mutation starts...");
            log.debug("[Mutation] Generated locus: {}", locus);
            for (int i=0; i<Util.getRangeLength(request.getMaxRange()); i++) {
                if (i==locus) {
                    int newValue = 1-population.getPopulation().get(randomIndividual).getGenes()[i];
                    population.getPopulation().get(randomIndividual).getGenes()[i] = newValue;
                }
            }
            population
                    .getPopulation()
                    .get(randomIndividual)
                    .setX(Util.computeXValue(population.getPopulation().get(randomIndividual).getGenes()));
            population
                    .getPopulation()
                    .get(randomIndividual)
                    .setValue(Util.computeFunctionValueInX(population.getPopulation().get(randomIndividual).getX(), request.getFunctionBody()));
        }
    }
}
