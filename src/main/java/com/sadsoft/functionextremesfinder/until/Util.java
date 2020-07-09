package com.sadsoft.functionextremesfinder.until;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Util {

    public static int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max+1 - min)) + min);
    }

    public static double computeFunctionValueInX(double x, String functionBody) {
        String newFunctionBody = functionBody.replace("ln", "2.303*log10");
        Expression expression = new ExpressionBuilder(newFunctionBody)
                .variable("x")
                .build()
                .setVariable("x", x);
        return expression.evaluate();
    }
}
