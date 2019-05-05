package com.AntonChernikov.g144;

import java.io.IOException;
import java.io.OutputStream;

/** Class describing output matrix in a spiral to stream */
public abstract class AbstractOutputer {

    /** Method displaying the matrix in a spiral to the stream */
    public void out(int[][] matrix, OutputStream stream) {
        try {
            int count = 0;
            int block = 1;
            int step = 1;
            int column = matrix.length / 2;
            int line = matrix.length / 2;
            stream.write(Integer.toString(matrix[column][line]).getBytes());
            stream.write(' ');
            count++;
            while (count < matrix.length * matrix.length) {
                for (int i = 0; (i < block) && (count < matrix.length * matrix.length); i++) {
                    line += step;
                    stream.write(Integer.toString(matrix[column][line]).getBytes());
                    stream.write(' ');
                    count++;
                }
                for (int i = 0; (i < block) && (count < matrix.length * matrix.length); i++) {
                    column += step;
                    stream.write(Integer.toString(matrix[column][line]).getBytes());
                    stream.write(' ');
                    count++;
                }
                block++;
                step = -step;
            }
        } catch (IOException e) {
            System.out.print("Output error: " + e);
        }
    }
}