package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class LazyFactoryTest {

    @Test
    void singleCalculationTest() {
        AtomicInteger numOfCalculation = new AtomicInteger();
        int value = 0;
        Supplier<Integer> supplier = () -> {
            numOfCalculation.getAndIncrement();
            return value;
        };

        Lazy<Integer> lazy = LazyFactory.createMultithreadedLazy(supplier);
        lazy.get();
        lazy.get();
        lazy.get();

        assertEquals(1, numOfCalculation.get());
        assertEquals(Integer.valueOf(value), lazy.get());
        numOfCalculation.set(0);

        lazy = LazyFactory.createSimpleThreadedLazy(supplier);
        lazy.get();
        lazy.get();
        lazy.get();

        assertEquals(1, numOfCalculation.get());
        assertEquals(Integer.valueOf(value), lazy.get());
    }

    @Test
    void getNullPointer() {
        AtomicInteger numOfCalculation = new AtomicInteger();
        Supplier<Integer> supplier = () -> {
            numOfCalculation.getAndIncrement();
            return null;
        };

        Lazy<Integer> lazy = LazyFactory.createMultithreadedLazy(supplier);
        lazy.get();
        lazy.get();
        lazy.get();

        assertEquals(1, numOfCalculation.get());
        assertNull(lazy.get());
        numOfCalculation.set(0);

        lazy = LazyFactory.createSimpleThreadedLazy(supplier);
        lazy.get();
        lazy.get();
        lazy.get();

        assertEquals(1, numOfCalculation.get());
        assertNull(lazy.get());
    }

    @Test
    void absenceOfRacingTest() throws InterruptedException {
        AtomicInteger numOfCalculation = new AtomicInteger();
        int value = 0;
        Supplier<Integer> supplier = () -> {
            numOfCalculation.getAndIncrement();
            return value;
        };
        Lazy<Integer> lazy = LazyFactory.createMultithreadedLazy(supplier);

        ArrayList<Thread> threads = new ArrayList<>();

        int numOfThreads = 999;
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(lazy::get));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(1, numOfCalculation.get());
        assertEquals(Integer.valueOf(value), lazy.get());
    }
}