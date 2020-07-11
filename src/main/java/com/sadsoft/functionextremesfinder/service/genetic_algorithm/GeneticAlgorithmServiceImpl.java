package com.sadsoft.functionextremesfinder.service.genetic_algorithm;

import com.sadsoft.functionextremesfinder.model.*;
import com.sadsoft.functionextremesfinder.service.fitness_evaluator.FitnessEvaluator;
import com.sadsoft.functionextremesfinder.service.population_initializer.PopulationInitializer;
import com.sadsoft.functionextremesfinder.service.selector.Selector;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;

@Service
public class GeneticAlgorithmServiceImpl implements GeneticAlgorithmService {

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmServiceImpl.class);
    private final PopulationInitializer populationInitializer;
    private final FitnessEvaluator fitnessEvaluator;
    private final Selector selector;

    private  Individual fittest = null;
    private Individual prevFittest = null;
    private StopReason stopReason = StopReason.MAX_ITERATIONS;

    public GeneticAlgorithmServiceImpl(PopulationInitializer populationInitializer, FitnessEvaluator fitnessEvaluator, Selector selector) {
        this.populationInitializer = populationInitializer;
        this.fitnessEvaluator = fitnessEvaluator;
        this.selector = selector;
    }

    @Override
    public GeneticAlgorithmResponseDTO run(GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        validateAlgorithmProperties(requestDTO);
        log.debug("Algorithm starts...");
        log.debug("Initializing population...");
        Population population = populationInitializer.initialize(requestDTO);
        int i = 0;
        int withoutChanges = 0;

        while (i <= requestDTO.getMaxIterations()
                && withoutChanges <= requestDTO.getMaxWithoutChanges()) {
            validatePopulation(population);
            Util.logPopulation(population, log);
            fitnessEvaluator.countFitness(population, requestDTO.getFunctionBody());
            this.prevFittest = fittest;
            this.fittest = Collections.max(population.getPopulation(),
                    Comparator.comparing(Individual::getValue));
            selector.select(population);
            if (fittest == prevFittest) withoutChanges++;
            else withoutChanges = 0;
            i++;
            if (i == requestDTO.getMaxWithoutChanges()) stopReason=StopReason.MAX_WITHOUT_CHANGES;
            log.debug("[Genetic algorithm] Fittest individual: {} appears {} times", fittest.toString(), withoutChanges);
            log.debug("[Genetic algorithm] Iterations: {}", i);
            log.debug("====================================================================================");
            log.debug("");
        }
        return new GeneticAlgorithmResponseDTO(
                this.fittest.getX(),
                this.fittest.getValue(),
                i,
                stopReason);
    }
    void validatePopulation(Population population) {
        if (population.getPopulation() == null) {
            throw new IllegalStateException("Population can't be null");
        }
        if (population.getPopulation().isEmpty()) {
            throw new IllegalStateException("Population can't be empty");
        }
    }

    void validateAlgorithmProperties(GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        if (requestDTO.getMaxIterations() < requestDTO.getMaxWithoutChanges()) {
            throw new IllegalStateException("Iterations count can not be lower than max without changes count");
        }
    }
}
