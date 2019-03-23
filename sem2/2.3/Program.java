package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    /**
     * Initialization and output of the matrix in a spiral in two ways: to console or to file
     * */
    public static void main(String[] args) {
        int length = 5;
        int[][] matrix = new int[length][length];

        int select = 1;
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                matrix[i][j] = select;
                select++;
            }
        }

        Outputer[] outputer = new Outputer[2];
        outputer[0] = new OutputToConsole();
        outputer[1] = new OutputToFile();

        System.out.println("Choose a way to display the matrix");
        System.out.println("0 - to console");
        System.out.println("1 - to file");

        int command = 0;
        Scanner in = new Scanner(System.in);
        command = in.nextInt();
        outputer[command].output(matrix);
    }
}
