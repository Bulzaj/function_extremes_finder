package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmResponseDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.properties.GeneticAlgorithmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeneticAlgorithmServiceImpl implements GeneticAlgorithmService {

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmServiceImpl.class);

    private final GeneticAlgorithmProperties properties;
    private final Population population;
    private final PopulationInitializer populationInitializer;

    public GeneticAlgorithmServiceImpl(GeneticAlgorithmProperties properties, PopulationInitializer populationInitializer) {
        this.properties = properties;
        this.populationInitializer = populationInitializer;
        population = populationInitializer.initialize();
        log.debug("Algorithm properties: {}", this.properties);
    }

    @Override
    public ResponseEntity<GeneticAlgorithmResponseDTO> run(String functionBody) {
        log.info("Algorithm starts...");
        return null;
    }
}
