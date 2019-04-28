package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void postfixNotation() throws Exception {
        Calculator calculator = new Calculator();
        String actualOutputLine = calculator.postfixNotation("1+2");
        assertEquals("12+", actualOutputLine);
    }

    @Test
    void calculate() throws Exception {
        Calculator calculator = new Calculator();
        int actualValue = calculator.calculate("(9/3-1)*4");
        assertEquals(8, actualValue);
    }
}