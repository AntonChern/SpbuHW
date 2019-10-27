package com.anton.chernikov.g244;

import java.util.Random;

/** Class generating a landscape based on the Lagrange interpolation polynomial */
public class LandscapeGenerator {

    private int pointsNum;
    private int[] horizontal;
    private int[] vertical;
    private double[] coef;

    /** Constructor randomly choosing points to generate */
    public LandscapeGenerator() {
        Random rand = new Random();
        pointsNum = (int)(rand.nextDouble() * 2) + 7;
        horizontal = new int[pointsNum];
        vertical = new int[pointsNum];
        coef = new double[pointsNum];

        horizontal[0] = 0;
        horizontal[1] = Const.windowWidth;
        vertical[0] = vertical[1] = Const.windowHeight / 2;

        int bound = 0;
        for (int i = 2;  i < pointsNum; i++) {
            horizontal[i] = bound + rand.nextInt(Const.windowWidth / (pointsNum - 2));
            vertical[i] = Const.windowHeight / 9 * 5 + rand.nextInt(Const.windowHeight / 9);
            bound += Const.windowWidth / (pointsNum - 2);
        }
        for (int i = 0; i < pointsNum; i++) {
            coef[i] = 1;
            for (int j = 0; j < pointsNum; j++) {
                if (j != i) {
                    coef[i] /= (horizontal[i] - horizontal[j]);
                }
            }
        }
    }

    /** Function reading the value of a polynomial at a specified point */
    public double getCoordinate(double point) {
        double result = 0;
        for (int i = 0; i < pointsNum; i++) {
            double localValue = 1;
            for (int j = 0; j < pointsNum; j++) {
                if (j != i) {
                    localValue *= point - horizontal[j];
                }
            }
            result += vertical[i] * localValue * coef[i];
        }
        return result;
    }
}
