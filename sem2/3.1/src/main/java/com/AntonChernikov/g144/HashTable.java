package com.AntonChernikov.g144;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class describing the functionality of the hash table
 * */
public class HashTable {
    private ArrayList<Integer>[] buckets;
    private int hashCode = 1;

    public HashTable(int size) {
        buckets = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    /**
     * Method changing hash function and recreating the hash table
     * */
    public void changeHash(int index) {
        hashCode = index;
        ArrayList<Integer>[] newBuckets = new ArrayList[buckets.length];
        for (int i = 0; i < buckets.length; i++) {
            newBuckets[i] = new ArrayList<>();
        }
        for (ArrayList<Integer> current : buckets) {
            for (Integer value : current) {
                newBuckets[hash(value)].add(value);
            }
        }
        buckets = newBuckets;
    }

    /**
     * Method returning hash value
     * */
    private int hash(int value) {
        switch (hashCode) {
            case 1: {
                value += ~(value << 16);
                value ^=  (value >>  5);
                value +=  (value <<  3);
                value ^=  (value >> 13);
                value += ~(value <<  9);
                value ^=  (value >> 17);
                break;
            }
            case 0: {
                value %= buckets.length;
                break;
            }
            default: {
                break;
            }
        }
        return value % buckets.length;
    }

    public void add(int value) {
        buckets[hash(value)].add(value);
    }

    public void remove(int value) {
        for (int i = 0; i < buckets[hash(value)].size(); i++) {
            if (buckets[hash(value)].get(i) == value) {
                buckets[hash(value)].remove(i);
                break;
            }
        }
    }

    public boolean exists(int value) {
        for (Integer current : buckets[hash(value)]) {
            if (current == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method filling a hash table with data from a file
     * */
    public void fill(String fileName) {
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
                add(value);
            }
        } catch (IOException e) {
            System.out.println("Input/output error: " + e);
        }
    }

    private int getFilledCellsNumber() {
        int result = 0;
        for (ArrayList current : buckets) {
            if (!current.isEmpty()) {
                result++;
            }
        }
        return result;
    }

    private int getConflictCellsNumber() {
        int result = 0;
        for (ArrayList current : buckets) {
            if (current.size() > 1) {
                result++;
            }
        }
        return result;
    }

    private int getMaximumConflictLength() {
        int result = 0;
        for (ArrayList current : buckets) {
            if (current.size() > result) {
                result = current.size();
            }
        }
        return result;
    }

    public void printStatistics() {
        System.out.println("Amount of cells: " + buckets.length);
        System.out.println("Load factor: " + (double) getFilledCellsNumber() / buckets.length);
        System.out.println("Amount of conflicting cells: " + getConflictCellsNumber());
        if (getConflictCellsNumber() != 0) {
            System.out.println("Maximum length in a conflict cell: " + getMaximumConflictLength());
        }
    }
}
