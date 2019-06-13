package com.AntonChernikov.g144;

import java.util.function.Supplier;

/** Class describing lazy counting for simple-threaded operations */
public class SimpleThreadedLazy<T> implements Lazy<T> {

    private T object;
    private Supplier<T> supplier;

    public SimpleThreadedLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (supplier != null) {
            object = supplier.get();
            supplier = null;
        }
        return object;
    }
}
