package com.AntonChernikov.g144;

public class Stack {
    private class StackElement {
        private int value;
        private StackElement next;

        private StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }
    }

    private StackElement first = null;
    public int size = 0;

    public void push(int value) {
        StackElement newElement = new StackElement(value, first);
        first = newElement;
        size++;
    }

    public int pop() {
        if (first != null) {
            int result = first.value;
            first = first.next;
            size--;
            return result;
        }
        return 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void print() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
        }
        else {
            StackElement current = first;
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.next;
            }
        }
        System.out.println();
    }
}
