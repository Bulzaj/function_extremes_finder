package com.sadsoft.functionextremesfinder.service.selector;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface Selector {

    void select(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO);
}
