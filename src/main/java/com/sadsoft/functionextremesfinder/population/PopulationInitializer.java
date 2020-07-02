package com.sadsoft.functionextremesfinder.population;

import com.sadsoft.functionextremesfinder.population.individual.Individual;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PopulationInitializer {

    List<Individual> initialize();
}
