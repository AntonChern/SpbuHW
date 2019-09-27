package com.AntonChernikov.g144;

import java.util.concurrent.RecursiveAction;

/** Class describing multithreaded quick sort */
public class MultithreadedQuickSort extends RecursiveAction {

    private int[] numbers;
    private int from;
    private int to;
    private int length;
    private int currentIndex;

    public MultithreadedQuickSort(int[] numbers, int from, int to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
        this.length = to - from + 1;
    }

    @Override
    protected void compute() {
        if (length < 1) {
            return;
        }
        currentIndex = from + length / 2;

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = from; i < currentIndex; i++) {
                if (numbers[i] > numbers[currentIndex]) {
                    //swap
                    int temp = numbers[i];
                    numbers[i] = numbers[currentIndex];
                    numbers[currentIndex] = temp;
                    currentIndex = i;
                    isSorted = false;
                    break;
                }
            }
            for (int i = to; i > currentIndex; i--) {
                if (numbers[i] < numbers[currentIndex]) {
                    //swap
                    int temp = numbers[i];
                    numbers[i] = numbers[currentIndex];
                    numbers[currentIndex] = temp;
                    currentIndex = i;
                    isSorted = false;
                    break;
                }
            }
        }

        invokeAll(new MultithreadedQuickSort(numbers, from, currentIndex - 1));
        invokeAll(new MultithreadedQuickSort(numbers, currentIndex + 1, to));
    }
}
