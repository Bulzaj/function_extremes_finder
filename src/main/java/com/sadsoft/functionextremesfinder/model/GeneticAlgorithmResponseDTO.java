package com.sadsoft.functionextremesfinder.model;

public class GeneticAlgorithmResponseDTO {

    private int x;
    private double xValue;

    public GeneticAlgorithmResponseDTO(int x, double xValue) {
        this.x = x;
        this.xValue = xValue;
    }

    public GeneticAlgorithmResponseDTO() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }
}
