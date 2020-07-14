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
        float maxFitness = 0;
        List<Individual> newPopulation = new ArrayList<>();

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
                    int[] newGenes = Arrays.copyOf(population.getPopulation()[j].getGenes(), Util.getRangeLength(requestDTO.getMaxRange()));
                    newPopulation.add( new Individual(newGenes)) ;
                }
                lowerBand = upperBand;
            }
        }
        population.setPopulation(newPopulation.toArray(new Individual[0]));
        Util.updatePopulation(population, requestDTO);
    }
}
