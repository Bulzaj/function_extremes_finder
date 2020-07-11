package com.sadsoft.functionextremesfinder.service.genetic_algorithm.selector;

import com.sadsoft.functionextremesfinder.model.Population;
import org.springframework.stereotype.Service;

@Service
public interface Selector {

    void select(Population population);
}
