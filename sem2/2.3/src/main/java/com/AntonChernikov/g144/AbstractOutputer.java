package com.AntonChernikov.g144;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class describing output matrix in a spiral to stream
 * */
public abstract class AbstractOutputer {
    public String out(int[][] matrix, OutputStream stream) {
        StringBuilder result = new StringBuilder();

        try {
            int count = 0;
            int block = 1;
            int step = 1;
            int column = matrix.length / 2;
            int line = matrix.length / 2;
            stream.write(Integer.toString(matrix[column][line]).getBytes());
            stream.write(' ');
            result.append(matrix[column][line]).append(" ");
            count++;
            while (count < matrix.length * matrix.length) {
                for (int i = 0; (i < block) && (count < matrix.length * matrix.length); i++) {
                    line += step;
                    result.append(matrix[column][line]).append(" ");
                    stream.write(Integer.toString(matrix[column][line]).getBytes());
                    stream.write(' ');
                    count++;
                }
                for (int i = 0; (i < block) && (count < matrix.length * matrix.length); i++) {
                    column += step;
                    result.append(matrix[column][line]).append(" ");
                    stream.write(Integer.toString(matrix[column][line]).getBytes());
                    stream.write(' ');
                    count++;
                }
                block++;
                step = -step;
            }
            return result.toString();
        } catch (IOException e) {
            System.out.print("Output error: " + e);
            return "";
        }
    }
}
