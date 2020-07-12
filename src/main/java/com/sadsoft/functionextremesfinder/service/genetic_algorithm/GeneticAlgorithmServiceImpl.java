package com.sadsoft.functionextremesfinder.service.genetic_algorithm;

import com.sadsoft.functionextremesfinder.model.*;
import com.sadsoft.functionextremesfinder.service.fitness_evaluator.FitnessEvaluator;
import com.sadsoft.functionextremesfinder.service.genetic_operation.Crossover;
import com.sadsoft.functionextremesfinder.service.genetic_operation.operations_registry.GeneticOperationsRegistry;
import com.sadsoft.functionextremesfinder.service.genetic_operation.operations_registry.GeneticOperationsRegistryImpl;
import com.sadsoft.functionextremesfinder.service.population_initializer.PopulationInitializer;
import com.sadsoft.functionextremesfinder.service.selector.Selector;
import com.sadsoft.functionextremesfinder.service.selector.SelectorFactory;
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
    private final SelectorFactory factory;
    private  Selector selector;
    private GeneticOperationsRegistry registry;

    Population population;
    private Individual fittest = null;
    private Individual prevFittest = null;
    private StopReason stopReason = StopReason.MAX_ITERATIONS;

    public GeneticAlgorithmServiceImpl(PopulationInitializer populationInitializer,
                                       FitnessEvaluator fitnessEvaluator) {
        this.populationInitializer = populationInitializer;
        this.fitnessEvaluator = fitnessEvaluator;
        factory = new SelectorFactory();
    }

    @Override
    public GeneticAlgorithmResponseDTO run(GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        validateAlgorithmProperties(requestDTO);
        log.debug("Algorithm starts...");
        log.debug("Initializing population...");
        population = populationInitializer.initialize(requestDTO);
        selector = factory.build(requestDTO.getSelectorType()).get();
        registry = new GeneticOperationsRegistryImpl(population, requestDTO);
        int i = 0;
        int withoutChanges = 0;

        registry.addOperation("crossover", new Crossover());

        while (i <= requestDTO.getMaxIterations()
                && withoutChanges <= requestDTO.getMaxWithoutChanges()) {
            validatePopulation(population);
            fitnessEvaluator.countFitness(population, requestDTO.getFunctionBody());
            this.prevFittest = fittest;
            this.fittest = Collections.max(population.getPopulation(),
                    Comparator.comparing(Individual::getValue));
            selector.select(population);
//            Util.logPopulation(population, log);
            registry.runOperations();

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
