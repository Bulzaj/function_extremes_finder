package com.sadsoft.functionextremesfinder.properties;

import org.springframework.stereotype.Component;

@Component
public class DefaultGeneticAlgorithmPropertiesImpl implements GeneticAlgorithmProperties {

    @Override
    public String getFunctionBody() {
        return "15+x*cos(x+3)-6*(ln(x)-7)/(x^2+3)+x^4";
    }

    @Override
    public int getMinRange() {
        return 0;
    }

    @Override
    public int getMaxRange() {
        return 202;
    }

    @Override
    public int getPopulationSize() {
        return 20;
    }

    @Override
    public int getIterationsCount() {
        return 1000;
    }

    @Override
    public String toString() {
        String result = "";
                result += "[Function] " + getFunctionBody();
                result += " [Min Range] " + getMinRange();
                result += " [Max Range] " + getMaxRange();
                result += " [Population Size] " + getPopulationSize();
                result += " [Max iterations] " + getIterationsCount();
        return  result;
    }
}
