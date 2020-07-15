package com.sadsoft.functionextremesfinder.service.genetic_operation;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Mutation implements GeneticOperator {

    private static final Logger log = LoggerFactory.getLogger(Mutation.class);

    @Override
    public void doOperation(Population population, GeneticAlgorithmPropertiesRequestDTO request) {

        log.debug("[Mutation] Mutation starts...");
        for (int i=0; i<population.getPopulation().length; i++) {
            int mutationChance = Util.generateRandom(0, 100);
            if (mutationChance <= request.getMutationChance()) {
                int locus = Util.generateRandom(0, Util.getRangeLength(request.getMaxRange())-1);
                int newValue = 1-population.getPopulation()[i].getGenes()[locus];
                population.getPopulation()[i].getGenes()[locus] = newValue;
                log.debug("[Mutation] Mutation occurs for individual {}, locus={}",
                        i,
                        locus);
            }
        }
        Util.updatePopulation(population, request);
        log.debug("[Mutation] Mutation stops...");
    }

//    @Override
//    public void doOperation(Population population, GeneticAlgorithmPropertiesRequestDTO request) {
//        log.debug("[Mutation] Mutation starts");
//        Util.logPopulation(population, log);
//        population
//                .getPopulation()
//                .stream()
//                .filter(individual -> {
//                    int random = Util.generateRandom(0, 100);
//                    if (random <= request.getMutationChance()) return true;
//                    return false;
//                })
//                .map(individual -> {
//                    int locus = Util.generateRandom(0, individual.getGenes().length-1);
//                    int newValue = 1-individual.getGenes()[locus];
//                    Individual newIndividual = new Individual();
//                    int[] newGenes = individual.getGenes();
//                    newGenes[locus] = newValue;
//                    newIndividual.setGenes(individual.getGenes());
//                    return newIndividual;
//                })
//                .collect(Collectors.toList());
//        Util.updatePopulation(population, request);
//        Util.logPopulation(population, log);
//        log.debug("[Mutation] Mutation stop...");
//    }
}
