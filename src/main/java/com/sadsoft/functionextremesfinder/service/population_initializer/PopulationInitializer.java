package com.sadsoft.functionextremesfinder.service.population_initializer;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface PopulationInitializer {

    void initialize(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO);
}
