package com.sadsoft.functionextremesfinder.service;

import com.sadsoft.functionextremesfinder.model.Population;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FitnessEvaluatorImpl implements FitnessEvaluator {

    @Override
    public void countFitness(Population population, String functionBody) {
        population
            .getPopulation()
            .stream()
            .map(individual -> {
                individual.setFitness(countFitness(individual.getValue(), functionBody));
                return individual;
            })
            .collect(Collectors.toList());
    }

    private double countFitness(double value, String functionBody) {
        String newFunctionBody = functionBody.replace("ln", "2.303*log10");
        Expression expression = new ExpressionBuilder(newFunctionBody)
                .variable("x")
                .build()
                .setVariable("x", value);
        return expression.evaluate();
    }
}
