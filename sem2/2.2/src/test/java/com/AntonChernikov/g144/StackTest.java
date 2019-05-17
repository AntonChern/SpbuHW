package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void pushAndIsEmpty() {
        int size = 20;
        StackOnArray stackOnArray = new StackOnArray(size);
        StackOnLinkedList stackOnLinkedList = new StackOnLinkedList();

        int value = 5;
        assertTrue(stackOnArray.isEmpty() && stackOnLinkedList.isEmpty());
        stackOnLinkedList.push(value);
        stackOnArray.push(value);
        assertFalse(stackOnArray.isEmpty() || stackOnLinkedList.isEmpty());
    }

    @Test
    void popAndIsEmpty() {
        int size = 20;
        StackOnArray stackOnArray = new StackOnArray(size);
        StackOnLinkedList stackOnLinkedList = new StackOnLinkedList();

        int value = 5;
        stackOnLinkedList.push(value);
        stackOnArray.push(value);
        assertFalse(stackOnArray.isEmpty() || stackOnLinkedList.isEmpty());
        assertEquals(value, stackOnArray.pop());
        assertEquals(value, stackOnLinkedList.pop());
        assertTrue(stackOnArray.isEmpty() && stackOnLinkedList.isEmpty());

        assertThrows(EmptyStackException.class, stackOnArray::pop);
        assertThrows(EmptyStackException.class, stackOnLinkedList::pop);
    }
}