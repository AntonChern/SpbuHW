package com.AntonChernikov.g144;

/** Class describing single threaded quick sort */
public class SingleThreadedQuickSort {

    public void sort(int[] numbers, int from, int to) {
        int length = to - from + 1;
        if (length < 1) {
            return;
        }
        int currentIndex = from + length / 2;

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

        sort(numbers, from, currentIndex - 1);
        sort(numbers, currentIndex + 1, to);
    }

}
