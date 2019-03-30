package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void changeHash() {
    }

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
        assertTrue(!table.exists(value));
    }

    @Test
    void fill() {
        HashTable table = new HashTable(5);
        String fileName = "File.txt";
        table.fill(fileName);
        try (FileReader file = new FileReader(fileName)) {
            int current = 0;
            current = file.read();
            while (current != -1) {
                int value = current - '0';
                current = file.read();
                while (current != ' ' && current != -1) {
                    value *= 10;
                    value += current - '0';
                    current = file.read();
                }
                while (current == ' ' && current != -1) {
                    current = file.read();
                }
                assertTrue(table.exists(value));
            }
        } catch (IOException e) {
            System.out.println("Input/output error: " + e);
        }
    }
}