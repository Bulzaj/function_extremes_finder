package com.sadsoft.functionextremesfinder.service.fitness_evaluator;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface FitnessEvaluator {

    void countFitness(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO);
}
