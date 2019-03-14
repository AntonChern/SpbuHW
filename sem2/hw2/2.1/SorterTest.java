package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    @Test
    public void ascending() {
        test(new BubbleSort());
        test(new HeapSort());
        test(new QuickSort());
    }

    private void test(Sorter sort) {
        int[][] actualNumbers = {{9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
        };
        int[] expectedNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < 2; i++) {
            sort.ascending(actualNumbers[i]);

            assertArrayEquals(expectedNumbers, actualNumbers[i]);
        }
    }
}