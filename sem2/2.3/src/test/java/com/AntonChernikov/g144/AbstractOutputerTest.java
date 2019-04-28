package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class AbstractOutputerTest {

    @Test
    void out() throws FileNotFoundException {
        String expectedString = "5 6 9 8 7 4 1 2 3 ";
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};
        AbstractOutputer outputer = new AbstractOutputer() {};
        String actualString = outputer.out(matrix, new FileOutputStream("File.txt"));
        assertEquals(expectedString, actualString);
    }
}