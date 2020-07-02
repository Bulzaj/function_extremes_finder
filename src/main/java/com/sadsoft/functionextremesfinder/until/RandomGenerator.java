package com.sadsoft.functionextremesfinder.until;

public class RandomGenerator {

    public static int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max+1 - min)) + min);
    }
}
