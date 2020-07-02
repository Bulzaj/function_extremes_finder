package com.sadsoft.functionextremesfinder.population;

import com.sadsoft.functionextremesfinder.GeneticAlgorithm.DefaultGeneticAlgorithmPropertiesImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationInitializerImplTest {

    private PopulationInitializer initializer = new PopulationInitializerImpl(new DefaultGeneticAlgorithmPropertiesImpl());

    @Test
    public void initialize_PopulationShouldHaveProperSize() {
        int expected = 20;
        assertEquals(expected, initializer.initialize().size());
    }


}