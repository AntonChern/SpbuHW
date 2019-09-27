package com.AntonChernikov.g144;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int quantity = 1;

        long timeMultithreaded = 1;
        long timeSingleThreaded = 0;

        while (timeMultithreaded > timeSingleThreaded) {
            int[] numbers = createArray(quantity);

            long startSingleThreaded = System.nanoTime();
            SingleThreadedQuickSort sorting = new SingleThreadedQuickSort();
            sorting.sort(numbers, 0, numbers.length - 1);
            timeSingleThreaded = System.nanoTime() - startSingleThreaded;

            numbers = createArray(quantity);

            long startMultithreaded = System.nanoTime();
            new ForkJoinPool().invoke(new MultithreadedQuickSort(numbers, 0, numbers.length - 1));
            timeMultithreaded = System.nanoTime() - startMultithreaded;

            quantity++;
        }

        System.out.println("Multithreading faster with array size = " + --quantity + " elements");
    }

    private static int[] createArray(int quantity) {
        int[] result = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            result[i] = -1;
        }
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            int index;
            do {
                index = random.nextInt(quantity);
            } while (result[index] != -1);
            result[index] = i;
        }
        return result;
    }
}
