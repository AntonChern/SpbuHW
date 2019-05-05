package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.*;

import static com.AntonChernikov.g144.SecondPartTasks.*;
import static org.junit.jupiter.api.Assertions.*;

class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(
                List.of(
                        "firstString",
                        "secondString",
                        "thirdString"
                ),
                findQuotes(
                        List.of(
                                "testFindQuotes1.txt",
                                "testFindQuotes2.txt",
                                "testFindQuotes3.txt"
                        ),
                        "String"
                )
        );

        assertEquals(
                Collections.emptyList(),
                findQuotes(
                        List.of(
                                "testFindQuotes1.txt",
                                "testFindQuotes2.txt",
                                "testFindQuotes3.txt"
                        ),
                        "template"
                )
        );

        assertEquals(
                Collections.emptyList(),
                findQuotes(Collections.emptyList(), ""));
    }

    @Test
    public void testPiDividedBy4() {
        int attempts = 5;
        double epsilon = 0.001;

        for (int i = 0; i < attempts; i++) {
            assertEquals(Math.PI / 4, piDividedBy4(), epsilon);
        }
    }

    @Test
    public void testFindPrinter() {
        assertEquals("Ernest",
                findPrinter(
                        Map.of(
                                "Jerome", List.of("text1", "text2", "text3"),
                                "Stephen", List.of("One", "Two", "Three", "Four"),
                                "Ernest", List.of("hyperthyroidism", "interpretations")
                        )
                )
        );

        assertEquals(
                "",
                findPrinter(Collections.emptyMap()));
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(
                Map.of(
                        "Apple", 1524,
                        "Pear", 255,
                        "Orange", 198,
                        "Potato", 1912,
                        "Tomato", 2037,
                        "Broccoli", 199,
                        "Garlic", 162,
                        "Onion", 144
                ),
                calculateGlobalOrder(
                        List.of(
                                Map.of(
                                        "Apple", 314,
                                        "Pear", 255,
                                        "Orange", 198
                                ),
                                Map.of(
                                        "Potato", 600,
                                        "Tomato", 725
                                ),
                                Map.of(
                                        "Apple", 1210,
                                        "Broccoli", 107,
                                        "Tomato", 936,
                                        "Potato", 899
                                ),
                                Map.of(
                                        "Broccoli", 92,
                                        "Garlic", 60,
                                        "Onion", 56
                                ),
                                Map.of(
                                        "Onion", 88,
                                        "Garlic", 102,
                                        "Tomato", 376,
                                        "Potato", 413
                                )
                        )
                )
        );

        assertEquals(
                Collections.emptyMap(),
                calculateGlobalOrder(Collections.emptyList()));
    }
}