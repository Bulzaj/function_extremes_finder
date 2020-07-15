package com.sadsoft.functionextremesfinder.service.genetic_operation;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Crossover implements GeneticOperator {

    private static final Logger log = LoggerFactory.getLogger(Crossover.class);

    @Override
    public void doOperation(Population population, GeneticAlgorithmPropertiesRequestDTO request) {

        log.debug("[Crossover] Crossover starts...");

        for (int i=0; i<population.getPopulation().length; i+=2) {
            int crossoverChance = Util.generateRandom(0, 100);
            if (crossoverChance <= request.getCrossoverChance()) {
                int locus = Util.generateRandom(0, Util.getRangeLength(request.getMaxRange()));
                int[] newGenes1 = Arrays.copyOf(population.getPopulation()[i].getGenes(), Util.getRangeLength(request.getMaxRange()));
                int[] newGenes2 = Arrays.copyOf(population.getPopulation()[i+1].getGenes(), Util.getRangeLength(request.getMaxRange()));
                for (int j=locus; j<Util.getRangeLength(request.getMaxRange()); j++) {
                    int tmp = newGenes1[j];
                    newGenes1[j] = newGenes2[j];
                    newGenes2[j] = tmp;
                }
                log.debug("[Crossover] Crossover occurs for individuals: {} and {}, locus={}", i, i+1, locus);
                log.debug("[Crossover] Individual: {}: {} => {}",i, Arrays.toString(population.getPopulation()[i].getGenes()), Arrays.toString(newGenes1));
                log.debug("[Crossover] Individual: {}: {} => {}",i+1, Arrays.toString(population.getPopulation()[i+1].getGenes()), Arrays.toString(newGenes2));
                population.getPopulation()[i].setGenes(newGenes1);
                population.getPopulation()[i+1].setGenes(newGenes2);
            }
        }
        Util.updatePopulation(population, request);
        log.debug("[Crossover] Crossover stops...");
    }
}
