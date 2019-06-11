package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueListTest {

    @Test
    void addAndExists() {
        UniqueList<Integer> list = new UniqueList<>();
        Integer value = 5;
        try {
            list.add(value);
        } catch (ElementExistsException ignore) {}
        assertThrows(ElementExistsException.class, () -> list.add(value));

        assertTrue(list.exists(value));
    }

    @Test
    void removeAndExists() {
        UniqueList<Integer> list = new UniqueList<>();
        Integer value = 5;
        try {
            list.add(value);
            list.remove(value);
        } catch (ElementExistsException | NoElementException ignore) {}
        assertThrows(NoElementException.class, () -> list.remove(value));

        assertTrue(!list.exists(value));
    }

    @Test
    void sizeAndIsEmpty() {
        UniqueList<Integer> list = new UniqueList<>();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Integer value = 5;
        try {
            list.add(value);
        } catch (ElementExistsException e) {
            System.out.println("This element already exists\n");
        }
        assertEquals(1, list.size());
        assertTrue(!list.isEmpty());
    }
}