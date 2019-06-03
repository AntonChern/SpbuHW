package com.AntonChernikov.g144;

import java.util.function.Supplier;

/** Class describing lazy counting for multithreaded operations */
public class MultithreadedLazy<T> implements Lazy<T> {

    private T object;
    private Supplier<T> supplier;

    public MultithreadedLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public synchronized T get() {
        if (supplier != null) {
            object = supplier.get();
            supplier = null;
        }
        return object;
    }
}
