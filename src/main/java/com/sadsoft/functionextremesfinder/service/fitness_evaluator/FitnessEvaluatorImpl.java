package com.sadsoft.functionextremesfinder.service.fitness_evaluator;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FitnessEvaluatorImpl implements FitnessEvaluator {

    private static final Logger log = LoggerFactory.getLogger(FitnessEvaluatorImpl.class);

    @Override
    public void countFitness(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO) {

        log.debug("[Evaluator] Evaluator starts...");

        float leastFitnessValue = 0;

        for (int i=0; i<population.getPopulation().length; i++) {
            float fitness = Util.computeFunctionValueInX(population.getPopulation()[i].getX(), requestDTO.getFunctionBody());
            population.getPopulation()[i].setFitness(fitness);
            if (fitness < leastFitnessValue) leastFitnessValue = fitness;
        }

        if (leastFitnessValue < 0 ) {
            log.debug("[Evaluator] Negative fitness value, recalculation...");
            for (int i=0; i< population.getPopulation().length; i++) {
                float newFitness = population.getPopulation()[i].getFitness() + Math.abs(leastFitnessValue);
                population.getPopulation()[i].setFitness(newFitness);
            }
        }

        log.debug("[Evaluator] Evaluator stops");
    }
}
