package com.sadsoft.functionextremesfinder.model;

public class GeneticAlgorithmResponseDTO {

    private int x;
    private double xValue;
    private int iterations;
    private StopReason stopReason;

    public GeneticAlgorithmResponseDTO(int x, double xValue, int iterations, StopReason stopReason) {
        this.x = x;
        this.xValue = xValue;
        this.iterations = iterations;
        this.stopReason = stopReason;
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

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public StopReason getStopReason() {
        return stopReason;
    }

    public void setStopReason(StopReason stopReason) {
        this.stopReason = stopReason;
    }
}
