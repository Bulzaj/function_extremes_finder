package com.sadsoft.functionextremesfinder.service.genetic_algorithm.fitness_evaluator;

import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface FitnessEvaluator {

    void countFitness(Population population, String functionBody);
}
