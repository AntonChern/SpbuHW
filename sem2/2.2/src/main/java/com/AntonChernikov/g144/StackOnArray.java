package com.AntonChernikov.g144;

import java.util.EmptyStackException;

/** Class describing stack functionality using array */
public class StackOnArray implements Stack {
    private int[] elements;
    private int lastIndex = 0;

    public StackOnArray(int size) {
        elements = new int[size];
    }

    /** Method increasing the size of the array */
    private void increaseSize() {
        int[] newElements = new int[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    /** {@inheritDoc} */
    public void push(int value) {
        if (lastIndex == elements.length) {
            increaseSize();
        }
        elements[lastIndex++] = value;
    }

    /** {@inheritDoc} */
    public int pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[--lastIndex];
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return lastIndex == 0;
    }
}