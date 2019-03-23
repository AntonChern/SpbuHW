package com.AntonChernikov.g144;

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

    public int pop() {
        if (first != null) {
            int result = first.value;
            first = first.next;
            return result;
        }
        return 0;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
