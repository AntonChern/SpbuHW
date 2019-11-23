package com.anton.chernikov.g244;

import java.util.Random;

/** The RandomGenerator class describes obtaining random value */
public class RandomGenerator implements ValueGenerator {
    private Random rand;

    /** Initializes the variable rand */
    public RandomGenerator() {
        rand = new Random();
    }

    /** Returns new random double value */
    @Override
    public double getValue() {
        return rand.nextDouble();
    }
}
