package com.AntonChernikov.g144;

import java.util.EmptyStackException;

/**
 * Class describing stack functionality using array
 * */
public class StackOnArray implements Stack {
    private int[] Elements = {};
    private int lastIndex = 0;

    public StackOnArray(int size) {
        Elements = new int[size];
    }

    public void push(int value) throws StackOverflowError {
        try {
            Elements[lastIndex++] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new StackOverflowError();
        }
    }

    public int pop() throws EmptyStackException {
        try {
            return Elements[--lastIndex];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }
}
