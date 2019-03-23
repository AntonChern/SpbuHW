package com.AntonChernikov.g144;

/**
 * Class describing stack functionality using array
 * */
public class StackOnArray implements Stack {
    private int[] Elements = {};
    private int lastIndex = 0;

    public StackOnArray(int size) {
        Elements = new int[size];
    }

    public void push(int value) {
        Elements[lastIndex++] = value;
    }

    public int pop() {
        return Elements[--lastIndex];
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }
}
