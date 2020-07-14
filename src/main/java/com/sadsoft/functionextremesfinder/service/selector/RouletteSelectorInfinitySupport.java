package com.sadsoft.functionextremesfinder.service.selector;

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
public class RouletteSelectorInfinitySupport implements Selector {

    @Override
    public void select(Population population) {
        List<Individual> newPopulation = new ArrayList<>();
        float maxFitness = 0;
        for (Individual individual: population.getPopulation()) {
            if (individual.getFitness() == Float.POSITIVE_INFINITY) maxFitness = Float.MAX_VALUE;
            else maxFitness += individual.getFitness();
        }
        for (int i=0; i<population.getPopulation().size(); i++) {
            float lowerBand = 0;
            float upperBand = 0;
            int random = Util.generateRandom(1, 100);
            for (Individual individual: population.getPopulation()) {
                upperBand += individual.getFitness();
                if (random > (lowerBand/maxFitness)*100 && random <= (upperBand/maxFitness)*100) {
                    newPopulation.add(individual);
                }
                lowerBand = upperBand;
            }
        }
        population.setPopulation(newPopulation);
    }
}
