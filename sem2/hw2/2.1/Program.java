package com.AntonChernikov.g144;

import java.util.Random;

public class Program {
    /**
     * Sorting an array in three ways alternately
     * */
    public static void main(String[] args) {
        Sorter[] sorts = new Sorter[3];
        sorts[0] = new BubbleSort();
        sorts[1] = new HeapSort();
        sorts[2] = new QuickSort();

        Random rand = new Random(System.currentTimeMillis());

        int[] numbers = new int[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = rand.nextInt(10);
            }

            System.out.print("Before: ");
            for (int element : numbers) {
                System.out.print(element + " ");
            }
            System.out.println();

            sorts[i].sort(numbers);

            System.out.print("After:  ");
            for (int element : numbers) {
                System.out.print(element + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
