package com.AntonChernikov.g144;

/**
 * Class describing output matrix in a spiral to console
 * */
public class OutputToConsole extends AbstractOutputer implements Outputer {

    /**
     * @inheritDoc
     * */
    public void output(int[][] matrix) {
        out(matrix, System.out);
    }
}
