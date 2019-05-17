package com.AntonChernikov.g144;

import java.util.EmptyStackException;

/** Interface describing the functionality of the stack */
public interface Stack {

    /** Method that adds a value to the top of the stack */
    void push(int value);

    /**
     * Method that returns the value at the top of the stack
     * @throws EmptyStackException if stack is empty
     * */
    int pop() throws EmptyStackException;

    /** Method checking stack for emptiness */
    boolean isEmpty();
}