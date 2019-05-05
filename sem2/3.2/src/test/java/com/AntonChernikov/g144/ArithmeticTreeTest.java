package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTreeTest {

    @Test
    void calculate() {
        int expectedValue = 4;
        String expression = "(* (+ 1 1) 2)";
        String fileName = "File.txt";

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(expression);
        } catch (IOException e) {
            System.out.println("Input/output error: " + e);
        }
        ArithmeticTree tree = new ArithmeticTree();
        tree.fill(fileName);
        assertEquals(expectedValue, tree.calculate());
    }

}