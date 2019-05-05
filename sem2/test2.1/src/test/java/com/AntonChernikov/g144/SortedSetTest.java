package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SortedSetTest {

    @Test
    void isEmptyAndAdd() {
        SortedSet set = new SortedSet();
        assertTrue(set.isEmpty());
        set.add(new LinkedList(Arrays.asList("one", "two", "three")));
        assertFalse(set.isEmpty());
    }

    @Test
    void print() {
        SortedSet set = new SortedSet();
        set.add(new LinkedList(Arrays.asList("value", "string")));
        set.add(new LinkedList(Arrays.asList("one")));
        set.add(new LinkedList(Arrays.asList("word1", "word2", "word3", "word4")));
        assertEquals("1. one \n2. value string \n3. word1 word2 word3 word4 \n", set.print());
    }
}