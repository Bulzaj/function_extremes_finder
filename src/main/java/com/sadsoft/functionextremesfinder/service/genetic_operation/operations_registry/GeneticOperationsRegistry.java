package com.sadsoft.functionextremesfinder.service.genetic_operation.operations_registry;

import com.sadsoft.functionextremesfinder.service.genetic_operation.GeneticOperator;

public interface GeneticOperationsRegistry {

    void addOperation(String operationName, GeneticOperator operator);
    void removeOperation(String operationName);
    void runOperations();
}
