package com.sadsoft.functionextremesfinder.service.genetic_operation.operations_registry;

import com.sadsoft.functionextremesfinder.model.GeneticAlgorithmPropertiesRequestDTO;
import com.sadsoft.functionextremesfinder.model.Population;
import com.sadsoft.functionextremesfinder.service.genetic_operation.GeneticOperator;

import java.util.HashMap;
import java.util.Map;

public class GeneticOperationsRegistryImpl implements GeneticOperationsRegistry {

    private Map<String, GeneticOperator> operationsRegistry;
    private final Population population;
    private final GeneticAlgorithmPropertiesRequestDTO requestDTO;

    public GeneticOperationsRegistryImpl(Population population, GeneticAlgorithmPropertiesRequestDTO requestDTO) {
        this.population = population;
        operationsRegistry = new HashMap<>();
        this.requestDTO = requestDTO;
    }

    @Override
    public void addOperation(String operationName, GeneticOperator operator) {
        operationsRegistry.put(operationName, operator);
    }

    @Override
    public void removeOperation(String operationName) {
        operationsRegistry.remove(operationName);
    }

    public void runOperation(String operationName) {
        operationsRegistry.get(operationName).doOperation(population, requestDTO);
    }

    @Override
    public void runOperations() {
        for (Map.Entry me : operationsRegistry.entrySet()) {
            GeneticOperator operator = (GeneticOperator)me.getValue();
            operator.doOperation(population, requestDTO);
        }
    }
}
