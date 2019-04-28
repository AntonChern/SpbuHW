package com.AntonChernikov.g144;

import java.io.*;

/**
 * Class describing output matrix in a spiral to file
 * */
public class OutputToFile extends AbstractOutputer implements Outputer {
    public void output(int[][] matrix) {
        try (FileOutputStream file = new FileOutputStream("File.txt")) {
            out(matrix, file);
        } catch (IOException e) {
            System.out.println("Error while outputting to file: " + e);
        }
    }
}