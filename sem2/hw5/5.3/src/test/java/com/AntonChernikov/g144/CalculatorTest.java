package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void postfixNotation() {
        Calculator calculator = new Calculator();
        String actualOutputLine = calculator.postfixNotation("1+2");
        assertEquals("1 2 +", actualOutputLine);
    }

    @Test
    void calculate() {
        Calculator calculator = new Calculator();
        String actualValue = calculator.calculate("(9/3-1)*4");
        assertEquals("8.0", actualValue);
    }
}