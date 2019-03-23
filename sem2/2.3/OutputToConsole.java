package com.AntonChernikov.g144;

/**
 * Class describing output matrix in a spiral to console
 * */
public class OutputToConsole extends AbstractOutputer implements Outputer {
    public void output(int[][] matrix) {
        System.out.print(buildString(matrix));
    }
}
