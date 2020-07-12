package com.sadsoft.functionextremesfinder.service.genetic_operation;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class Crossover implements GeneticOperator {

    private static final Logger log = LoggerFactory.getLogger(Crossover.class);

    @Override
    public void doOperation(Population population, GeneticAlgorithmPropertiesRequestDTO request) {
        log.debug("[Crossover] Crossover called...");
        int crossoverChance = Util.generateRandom(0, 100);
        if (crossoverChance <= request.getCrossoverChance()) {
            int maxRange = Util.getRangeLength(request.getMaxRange());
            int arrSize = population.getPopulation().get(0).getGenes().length;
            log.debug("[Crossover] Crossover starts...");
            log.debug("[Crossover] Max range: {}", maxRange);
            int locus = Util.generateRandom(1, maxRange - 1);
            log.debug("[Crossover] Generated locus: {}", locus);
            log.debug("[Crossover] Iterations count: {}", (int)population.getPopulation().size()/2);
            int arrSizeCut;
            if (population.getPopulation().size()%2==0) arrSizeCut = 1;
            else arrSizeCut = 2;
            for (int i=0; i <= (int)population.getPopulation().size()-arrSizeCut; i=i+2) {
                int genes1[] = new int[arrSize];
                int genes2[] = new int[arrSize];
                for (int j=0; j<=maxRange-1; j++) {
                    if (j<locus) {
                        genes1[j] = population.getPopulation().get(i+1).getGenes()[j];
                        genes2[j] = population.getPopulation().get(i).getGenes()[j];
                    } else {
                        genes1[j] = population.getPopulation().get(i).getGenes()[j];
                        genes2[j] = population.getPopulation().get(i+1).getGenes()[j];
                    }
                }
                log.debug("[Crossover] 1 {} => {}",
                        Arrays.toString(population.getPopulation().get(i).getGenes()),
                        Arrays.toString(genes1));
                log.debug("[Crossover] 2 {} => {}",
                        Arrays.toString(population.getPopulation().get(i+1).getGenes()),
                        Arrays.toString(genes2));
                population
                        .getPopulation()
                        .get(i)
                        .setGenes(genes1);
                population
                        .getPopulation()
                        .get(i+1)
                        .setGenes(genes2);
            }
            population
                    .getPopulation()
                    .stream()
                    .map(individual -> {
                        Individual tmp = individual;
                        tmp.setX(Util.computeXValue(individual.getGenes()));
                        tmp.setValue(Util.computeFunctionValueInX(tmp.getX(), request.getFunctionBody()));
                        return tmp;
                    }).collect(Collectors.toList());
        }
    }
}
