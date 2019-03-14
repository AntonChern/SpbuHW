package com.AntonChernikov.g144;

/**
 * Class describing bubble sort
 * */
public class BubbleSort implements Sorter {
    public void ascending(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    numbers[j] = numbers[j] + numbers[j + 1] - (numbers[j + 1] = numbers[j]);
                }
            }
        }
    }
}
