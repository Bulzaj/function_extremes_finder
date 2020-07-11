package com.sadsoft.functionextremesfinder.service.genetic_algorithm.selector;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouletteWheelSelector implements Selector {

    private static final Logger log = LoggerFactory.getLogger(RouletteWheelSelector.class);

    @Override
    public void select(Population population) {
        log.debug("[Roulette wheel] Roulette wheel selector start...");
        List<Individual> newPopulation = new ArrayList<>();
        float[] rouletteWheel = new float[population.getPopulation().size()];
        float maxFitness = 0;
        for (Individual individual : population.getPopulation()) {
            maxFitness += individual.getFitness();
        }
        float fitnessSum = 0;
        for (int i=0; i<rouletteWheel.length; i++) {
            fitnessSum += population.getPopulation().get(i).getFitness();
            rouletteWheel[i] = (fitnessSum/maxFitness)*100;
            log.debug("[Roulette wheel] Item {}, {}", i, rouletteWheel[i]);
        }
        Individual selectedIndividual = null;
        int populationSize = population.getPopulation().size();
        for (int i=0; i<rouletteWheel.length; i++) {
            float randomFloat = Util.generateRandom(0f, 100f);
            if (randomFloat <= rouletteWheel[0]) {
                selectedIndividual = population.getPopulation().get(0);
                log.debug("[Roulette wheel] Generated random: {} => Selected individual: {}", randomFloat, rouletteWheel[0]);
            } else if (randomFloat > rouletteWheel[populationSize-2] && randomFloat <= 100f) {
                selectedIndividual = population.getPopulation().get(populationSize-1);
                log.debug("[Roulette wheel] Generated random: {} => Selected individual: {}", randomFloat, rouletteWheel[populationSize-1]);
            } else if (randomFloat > rouletteWheel[0] && randomFloat < rouletteWheel[populationSize-2]) {
                for (int j=1; j<=populationSize-2; j++) {
                    if (randomFloat > rouletteWheel[j-1] && randomFloat <= rouletteWheel[j]) {
                        selectedIndividual = population.getPopulation().get(j);
                        log.debug("[Roulette wheel] Generated random: {} => Selected individual: {}", randomFloat, rouletteWheel[j]);
                    }
                }
            } else throw new RuntimeException("Somethings went wrong");
            if(selectedIndividual != null) newPopulation.add(selectedIndividual);
            else throw new RuntimeException("Selected individual can not be null");
        }
        population.setPopulation(newPopulation);
    }
}
