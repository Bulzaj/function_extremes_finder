package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface PopulationInitializer {

    Population initialize(int maxRange, int populationSize);
}
