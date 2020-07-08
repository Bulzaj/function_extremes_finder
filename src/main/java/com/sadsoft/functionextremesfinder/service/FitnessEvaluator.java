package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface FitnessEvaluator {

    void countFitness(Population population, String functionBody);
}
