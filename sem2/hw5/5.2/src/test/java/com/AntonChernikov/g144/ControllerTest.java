package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static com.AntonChernikov.g144.Controller.calculate;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void calculateTest() {
        double firstValue = 100.0;
        double secondValue = 5.0;
        assertEquals(105.0, calculate(firstValue, secondValue, '+'));
        assertEquals(95.0, calculate(firstValue, secondValue, '-'));
        assertEquals(500.0, calculate(firstValue, secondValue, '*'));
        assertEquals(20.0, calculate(firstValue, secondValue, '/'));
    }
}