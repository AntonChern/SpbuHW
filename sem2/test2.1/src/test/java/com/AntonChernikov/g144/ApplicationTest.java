package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void addWords() {
        Application app = new Application();
        String[] words = {"abc", "def", "xyz"};
        assertTrue(app.isEmpty());
        app.addWords(words);
        assertFalse(app.isEmpty());
    }

    @Test
    void print() {
        Application app = new Application();
        String[] words = {"hello world", "good"};
        app.addWords(words);
        assertEquals("1. good \n2. hello world \n", app.print());
    }
}