package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmResponseDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeneticAlgorithmServiceImpl implements GeneticAlgorithmService {

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmServiceImpl.class);
    private final PopulationInitializer populationInitializer;
    private final FitnessEvaluator fitnessEvaluator;

    public GeneticAlgorithmServiceImpl(PopulationInitializer populationInitializer, FitnessEvaluator fitnessEvaluator) {
        this.populationInitializer = populationInitializer;
        this.fitnessEvaluator = fitnessEvaluator;
    }

    @Override
    public ResponseEntity<GeneticAlgorithmResponseDTO> run(GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        log.debug("Algorithm starts...");
        log.debug("Initializing population...");
        Population population = populationInitializer.initialize(requestDTO.getMaxRange(), requestDTO.getPopulationSize());
        log.debug("Counting fitness of individuals");
        fitnessEvaluator.countFitness(population, requestDTO.getFunctionBody());
        log.debug(population.toString());
        return null;
    }
}
