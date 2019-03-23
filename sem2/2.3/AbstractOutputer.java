package com.AntonChernikov.g144;

/**
 * Class describing creating the needed string
 * */
public class AbstractOutputer {
    public String buildString(int[][] matrix) {
        String result = new String();

        int count = 0;
        int block = 1;
        int step = 1;
        int column = matrix.length / 2;
        int line = matrix.length/ 2;
        result += matrix[column][line] + " ";
        count++;
        while (count < matrix.length * matrix.length) {
            for (int i = 0; (i < block) && (count < matrix.length * matrix.length); i++) {
                line += step;
                result += matrix[column][line] + " ";
                count++;
            }
            for (int i = 0; (i < block) && (count < matrix.length * matrix.length); i++) {
                column += step;
                result += matrix[column][line] + " ";
                count++;
            }
            block++;
            step = -step;
        }
        return result;
    }
}
