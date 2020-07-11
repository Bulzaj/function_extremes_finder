package com.sadsoft.functionextremesfinder.service.fitness_evaluator;

import com.sadsoft.functionextremesfinder.model.Individual;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FitnessEvaluatorImpl implements FitnessEvaluator {

    private static final Logger log = LoggerFactory.getLogger(FitnessEvaluatorImpl.class);

    @Override
    public void countFitness(Population population, String functionBody) {
        log.debug("Evaluator start...");
        Individual lestFitIndividual;
        population
                .getPopulation()
                .stream()
                .peek(individual -> individual.setFitness(computeFitness(individual.getX(), functionBody)+0.1f))
                .collect(Collectors.toList());
        lestFitIndividual = Collections
                .min(population.getPopulation(),
                        Comparator.comparing(Individual::getFitness));

        if (lestFitIndividual.getFitness() < 0) {
            log.debug("Recomputing fitness value (Least value lower than zero): {}", lestFitIndividual.getFitness());
            float fitness = Math.abs(lestFitIndividual.getFitness());
            log.debug("Increasing value by {}", fitness);
            population.getPopulation()
                    .stream()
                    .map(individual -> {
                        Individual newIndividual = individual;
                        newIndividual.setFitness(individual.getFitness()+fitness+0.1f);
                        return newIndividual;
                    })
                    .collect(Collectors.toList());
        }
    }

    private float computeFitness(double x, String functionBody) {
        return Util.computeFunctionValueInX(x, functionBody);
    }
}
