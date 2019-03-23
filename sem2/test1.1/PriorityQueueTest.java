package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void enqueueAndDequeue() throws Exception {
        PriorityQueue[] queue = new PriorityQueue[3];
        queue[0] = new PriorityQueue<Integer>();
        queue[1] = new PriorityQueue<String>();
        queue[2] = new PriorityQueue<Double>();

        int[] intValues = {1, 2, 3, 4, 5};
        String[] stringValues = {"one", "two", "three", "four", "five"};
        double[] doubleValues = {1.1, 2.3, 3.5, 4.7, 5.9};

        int[] priorities = {8, 3, 2, 7, 4};

        int[] expectedIntValues = {1, 4, 5, 2, 3};
        String[] expectedStringValues = {"one", "four", "five", "two", "three"};
        double[] expectedDoubleValues = {1.1, 4.7, 5.9, 2.3, 3.5};

        for (int i = 0; i < 5; i++) {
            queue[0].enqueue(intValues[i], priorities[i]);
        }
        for (int i = 0; i < 5; i++) {
            queue[1].enqueue(stringValues[i], priorities[i]);
        }
        for (int i = 0; i < 5; i++) {
            queue[2].enqueue(doubleValues[i], priorities[i]);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(expectedIntValues[i], queue[0].dequeue());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(expectedStringValues[i], queue[1].dequeue());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(expectedDoubleValues[i], queue[2].dequeue());
        }

    }
}