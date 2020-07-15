package com.sadsoft.functionextremesfinder.service.genetic_algorithm;

import com.sadsoft.functionextremesfinder.model.*;
import com.sadsoft.functionextremesfinder.service.fitness_evaluator.FitnessEvaluator;
import com.sadsoft.functionextremesfinder.service.genetic_operation.Crossover;
import com.sadsoft.functionextremesfinder.service.genetic_operation.Mutation;
import com.sadsoft.functionextremesfinder.service.genetic_operation.operations_registry.GeneticOperationsRegistry;
import com.sadsoft.functionextremesfinder.service.genetic_operation.operations_registry.GeneticOperationsRegistryImpl;
import com.sadsoft.functionextremesfinder.service.population_initializer.PopulationInitializer;
import com.sadsoft.functionextremesfinder.service.selector.Selector;
import com.sadsoft.functionextremesfinder.service.selector.SelectorFactory;
import com.sadsoft.functionextremesfinder.until.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class GeneticAlgorithmServiceImpl implements GeneticAlgorithmService {

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmServiceImpl.class);
    private final DecimalFormat df;
    private final PopulationInitializer populationInitializer;
    private final FitnessEvaluator fitnessEvaluator;
    private final SelectorFactory factory;
    private Selector selector;
    private GeneticOperationsRegistry registry;

    private Population population;
    private Individual fittest = null;
    private Individual prevFittest = null;
    private StopReason stopReason = StopReason.MAX_ITERATIONS;

    public GeneticAlgorithmServiceImpl(PopulationInitializer populationInitializer,
                                       FitnessEvaluator fitnessEvaluator) {
        population = new Population();
        df = new DecimalFormat("0.00");
        this.populationInitializer = populationInitializer;
        this.fitnessEvaluator = fitnessEvaluator;
        factory = new SelectorFactory();
    }

    @Override
    public GeneticAlgorithmResponseDTO run(GeneticAlgorithmPropertiesRequestDTO requestDTO) {

        validateAlgorithmProperties(requestDTO);

        log.debug("[Genetic algorithm] Algorithm starts...");

        populationInitializer.initialize(population, requestDTO);

        selector = factory.build(requestDTO.getSelectorType()).get();
        registry = new GeneticOperationsRegistryImpl(population, requestDTO);

        registry.addOperation("2mutation", new Mutation());
        registry.addOperation("1crossover", new Crossover());

        int i = 0;
        int withoutChanges = 0;
        while (i < requestDTO.getMaxIterations() && withoutChanges < requestDTO.getMaxWithoutChanges()) {
            validatePopulation(population, requestDTO);

            fitnessEvaluator.countFitness(population, requestDTO);

            if (fittest == prevFittest) withoutChanges++;
            else withoutChanges = 0;

            this.fittest = Util.getFittest(population);
            this.prevFittest = fittest;

            i++;
            if (i == requestDTO.getMaxWithoutChanges()) stopReason=StopReason.MAX_WITHOUT_CHANGES;

            Util.logPopulation(population, log);

            selector.select(population, requestDTO);

            Util.logPopulation(population, log);

            registry.runOperations();

            Util.logPopulation(population, log);

            log.debug("[Genetic algorithm] Fittest individual: {} appears {} times", fittest.toString(), withoutChanges);
            log.debug("[Genetic algorithm] Iterations: {}", i);
            log.debug("====================================================================================");
            log.debug("");
        }
        if (i == requestDTO.getMaxIterations()) {
            stopReason = StopReason.MAX_ITERATIONS;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return new GeneticAlgorithmResponseDTO(
                this.fittest.getX(),
                df.format(this.fittest.getValue()),
                i,
                stopReason);
    }
    void validatePopulation(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        if (population.getPopulation() == null) {
            throw new IllegalStateException("Population can't be null");
        }
        if (population.getPopulation().length != requestDTO.getPopulationSize()) {
            throw new IllegalStateException("Wrong population size");
        }
    }

    void validateAlgorithmProperties(GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        if (requestDTO.getMaxIterations() < requestDTO.getMaxWithoutChanges()) {
            throw new IllegalStateException("Iterations count can not be lower than max without changes count");
        }
    }
}
