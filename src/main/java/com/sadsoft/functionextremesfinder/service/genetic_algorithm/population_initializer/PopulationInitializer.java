package com.sadsoft.functionextremesfinder.service.genetic_algorithm.population_initializer;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface PopulationInitializer {

    Population initialize(GeneticAlgorithmPropertiesRequestDTO requestDTO);
}
