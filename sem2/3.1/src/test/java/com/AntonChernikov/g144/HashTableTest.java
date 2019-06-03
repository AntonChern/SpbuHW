package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void addAndExists() {
        HashTable table = new HashTable(5);
        int value = 10;
        table.add(value);
        assertTrue(table.exists(value));
    }

    @Test
    void removeAndExists() {
        HashTable table = new HashTable(5);
        int value = 10;
        table.remove(value);
        assertFalse(table.exists(value));
    }

    @Test
    void fill() throws IOException {
        HashTable table = new HashTable(5);
        String fileName = "File.txt";
        table.fill(fileName);
        File file = new File(fileName);
        Scanner fin = new Scanner(file);
        while (fin.hasNextInt()) {
            int value = fin.nextInt();
            table.add(value);
            assertTrue(table.exists(value));
        }
    }
}