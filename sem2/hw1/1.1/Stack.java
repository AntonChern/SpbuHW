package com.AntonChernikov.g144;

public class Stack {
    StackElement first;

    public Stack() {
        first = null;
    }

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

    public int size() {
        int result = 0;
        StackElement current = first;
        while (current != null) {
            result++;
            current = current.next;
        }
        return result;
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
