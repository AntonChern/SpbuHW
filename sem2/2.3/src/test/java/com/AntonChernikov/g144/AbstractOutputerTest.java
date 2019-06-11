package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AbstractOutputerTest {

    @Test
    void outputToConsole() {
        String expectedString = "5 6 9 8 7 4 1 2 3 ";
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};
        AbstractOutputer outputer = new AbstractOutputer() {};

        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        outputer.out(matrix, System.out);

        System.setOut(consoleStream);

        assertEquals(expectedString, outputStream.toString());
    }

    @Test
    void outputToFile() throws FileNotFoundException {
        String fileName = "File.txt";
        String expectedString = "13 14 19 18 17 12 7 8 9 10 15 20 25 24 23 22 21 16 11 6 1 2 3 4 5 ";
        int[][] matrix = {{1, 2, 3, 4, 5},
                          {6, 7, 8, 9, 10},
                          {11, 12, 13, 14, 15},
                          {16, 17, 18, 19, 20},
                          {21, 22, 23, 24, 25}};
        AbstractOutputer outputer = new AbstractOutputer() {};

        FileOutputStream file = new FileOutputStream(fileName);
        outputer.out(matrix, file);

        FileReader in = new FileReader(fileName);
        Scanner scanner = new Scanner(in);

        assertEquals(expectedString, scanner.nextLine());
    }
}