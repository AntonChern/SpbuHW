package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SingleThreadedQuickSortTest {

    @Test
    void sort() {

        int quantity = 100;
        int[] numbers = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            numbers[i] = -1;
        }
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            int index;
            do {
                index = random.nextInt(quantity);
            } while (numbers[index] != -1);
            numbers[index] = i;
        }

        int[] expectedNumbers = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            expectedNumbers[i] = i;
        }


        long start = System.nanoTime();
        SingleThreadedQuickSort sorting = new SingleThreadedQuickSort();
        sorting.sort(numbers, 0, numbers.length - 1);
        long time = System.nanoTime() - start;
        System.out.println("Time of work single threaded sorting = " + time + " nanoseconds");

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(expectedNumbers[i], numbers[i]);
        }
    }
}