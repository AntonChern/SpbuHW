package com.AntonChernikov.g144;

/**
 * Interface describing the functionality of the stack
 * */
public interface Stack {

    /**
     * Method that adds a value to the top of the stack
     * */
    void push(int value);

    /**
     * Method that returns the value at the top of the stack
     * */
    int pop();

    /**
     * Method checking stack for emptiness
     * */
    boolean isEmpty();
}
