package com.sadsoft.functionextremesfinder.service.genetic_operation;

import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface GeneticOperator {

    void doOperation(Population population);
}
