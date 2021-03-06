package com.AntonChernikov.g144;

import java.util.function.Supplier;

/** Class describing the functionality of creating classes that implements interface Lazy */
public class LazyFactory {

    /** Method returning object implementing interface Lazy for simple-threaded operations */
    public static <T> Lazy<T> createSimpleThreadedLazy(Supplier<T> supplier) {
        return new SimpleThreadedLazy<T>(supplier);
    }

    /** Method returning object implementing interface Lazy for multithreaded operations */
    public static <T> Lazy<T> createMultithreadedLazy(Supplier<T> supplier) {
        return new MultithreadedLazy<T>(supplier);
    }
}
