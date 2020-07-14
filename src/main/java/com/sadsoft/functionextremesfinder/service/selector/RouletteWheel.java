package com.sadsoft.functionextremesfinder.service.selector;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Float.MAX_VALUE;

@Service
public class RouletteWheel implements Selector {

    private static final Logger log = LoggerFactory.getLogger(RouletteWheel.class);

    @Override
    public void select(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO) {

        log.debug("[Roulette wheel]: Roulette wheel starts...");
        Individual[] newPopulation = new Individual[population.getPopulation().length];
        float maxFitness = 0;

        for (Individual individual : population.getPopulation()) {
            if (individual.getFitness() == Float.POSITIVE_INFINITY) maxFitness = MAX_VALUE;
            else maxFitness += individual.getFitness();
        }

        log.debug("[Roulette wheel] Max fitness: {}", maxFitness);

        for (int i=0; i<population.getPopulation().length; i++) {
            float lowerBand = 0;
            float upperBand = 0;
            int random = Util.generateRandom(1, 100);

            for (int j=0; j<population.getPopulation().length; j++) {
                upperBand += population.getPopulation()[j].getFitness();
                if (random > (lowerBand/maxFitness)*100 && random <= (upperBand/maxFitness)*100) {
                    log.debug("[Roulette wheel] Drawn: {} < {} <= {}, individual: {}", lowerBand, random, upperBand, j);
                    Individual newIndividual = new Individual();
                    newIndividual.setGenes(population.getPopulation()[j].getGenes());
                    newPopulation[i] = newIndividual;
//                    newIndividual = null;
                }
                lowerBand = upperBand;
            }
        }
        for (int i=0; i<population.getPopulation().length; i++) {
            log.debug("[Roulette wheel] new individual: {}", newPopulation[i]);
        }
        population = new Population();
        population.setPopulation(newPopulation);
        Util.updatePopulation(population, requestDTO);
    }
}
