package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void postfixNotation() {
        Calculator calculator = new Calculator();
        String actualOutputLine = calculator.postfixNotation("1+2");
        assertEquals("12+", actualOutputLine);
        assertThrows(EmptyStackException.class, () -> calculator.postfixNotation(")3+5"));
    }

    @Test
    void calculate() {
        Calculator calculator = new Calculator();
        int actualValue = calculator.calculate("(9/3-1)*4");
        assertEquals(8, actualValue);
        assertThrows(EmptyStackException.class, () -> calculator.calculate("5++3"));
    }
}