package com.AntonChernikov.g144;

/**
 * Class describing quick sort
 * */
public class QuickSort implements Sorter {
    public void sort(int[] numbers) {
        sort(0, numbers.length - 1, numbers);
    }

    private void sort(int first, int last, int[] numbers) {
        if (first < last) {
            int length = last - first + 1;
            int select = first + length / 2;
            boolean isSorted = false;
            while (!isSorted) {
                isSorted = true;
                for (int i = 0; i < select - first; i++) {
                    if (numbers[first + i] > numbers[select]) {
                        numbers[first + i] = numbers[first + i] + numbers[select] - (numbers[select] = numbers[first + i]);
                        select = first + i;
                        isSorted = false;
                        break;
                    }
                }
                for (int i = 0; i < last - select; i++) {
                    if (numbers[last - i] <= numbers[select]) {
                        numbers[last - i] = numbers[last - i] + numbers[select] - (numbers[select] = numbers[last - i]);
                        select = last - i;
                        isSorted = false;
                        break;
                    }
                }
            }
            sort(first, select - 1, numbers);
            sort(select + 1, last, numbers);
        }
    }
}
