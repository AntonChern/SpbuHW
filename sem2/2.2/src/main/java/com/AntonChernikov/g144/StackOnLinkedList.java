package com.AntonChernikov.g144;

import java.util.EmptyStackException;

/**
 * Class describing stack functionality using linked list
 * */
public class StackOnLinkedList implements Stack {
    private class StackElement {
        private int value;
        private StackElement next;

        private StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }
    }

    private StackElement first = null;

    public void push(int value) {
        StackElement newElement = new StackElement(value, first);
        first = newElement;
    }

    public int pop() throws EmptyStackException {
        if (first != null) {
            int result = first.value;
            first = first.next;
            return result;
        }
        throw new EmptyStackException();
    }

    public boolean isEmpty() {
        return first == null;
    }
}
