package com.sadsoft.functionextremesfinder.until;

import com.sadsoft.functionextremesfinder.model.Population;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.slf4j.Logger;

import java.util.Arrays;

public class Util {

    public static int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max+1 - min)) + min);
    }

    public static float generateRandom(float min, float max) {
        return (float) (min + Math.random() * (max - min));
    }

    public static float computeFunctionValueInX(double x, String functionBody) {
        String newFunctionBody = functionBody.replace("ln", "2.303*log10");
        Expression expression = new ExpressionBuilder(newFunctionBody)
                .variable("x")
                .build()
                .setVariable("x", x);
        return (float) expression.evaluate();
    }

    public static void logPopulation(Population population, Logger log) {
        log.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        population.getPopulation().forEach(individual -> log.info("||| [Population] {} |||", individual.toString()));
        log.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static int getRangeLength(int range) {
        return Integer
                .toBinaryString(range)
                .length();
    }

    public static int computeXValue(int[] genes) {
        String tmpGenes = "";
        for (int gene : genes) {
            tmpGenes += String.valueOf(gene);
        }
        return Integer.parseInt(tmpGenes,2);
    }
}
