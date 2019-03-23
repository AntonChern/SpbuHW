package com.AntonChernikov.g144;

import java.io.*;

/**
 * Class describing output matrix in a spiral to file
 * */
public class OutputToFile extends AbstractOutputer implements Outputer {
    public void output(int[][] matrix) {
        try (FileWriter file = new FileWriter("File.txt")) {
            file.write(buildString(matrix));
        } catch (IOException e) {
            System.out.println("Input/output error: " + e);
        }
    }
}