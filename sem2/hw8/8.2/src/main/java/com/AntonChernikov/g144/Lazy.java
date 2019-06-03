package com.AntonChernikov.g144;

/** Interface describing lazy calculating */
public interface Lazy<T> {

    /** Method returning result of calculating */
    T get();
}
