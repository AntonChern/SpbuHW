package com.AntonChernikov.g144;

import java.util.EmptyStackException;

/** Class describing stack functionality using linked list */
public class StackOnLinkedList implements Stack {
    private StackElement first = null;

    /** {@inheritDoc} */
    public void push(int value) {
        first = new StackElement(value, first);
    }

    /** {@inheritDoc} */
    public int pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int result = first.value;
        first = first.next;
        return result;
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return first == null;
    }

    /** Class describing the stack element */
    private class StackElement {
        private int value;
        private StackElement next;

        private StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }
    }
}