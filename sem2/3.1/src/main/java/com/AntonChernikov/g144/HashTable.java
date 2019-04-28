package com.AntonChernikov.g144;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Class describing the functionality of the hash table
 * */
public class HashTable {
    private ArrayList<Integer>[] buckets;
    private int size;
    private Function<Integer, Integer> hashFunction = n -> {
        n += ~(n << 16);
        n ^=  (n >>  5);
        n +=  (n <<  3);
        n ^=  (n >> 13);
        n += ~(n <<  9);
        n ^=  (n >> 17);
        return n;
    };

    /**
     * Method returning new array of lists by the number of cells equal to size
     * */
    private ArrayList<Integer>[] createArrayOfLists(int size) {
        ArrayList<Integer>[] result = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            result[i] = new ArrayList<Integer>();
        }
        return result;
    }

    public HashTable(int size) {
        buckets = createArrayOfLists(size);
        this.size = size;
    }

    /**
     * Method creating the table again
     * */
    private ArrayList<Integer>[] update() {
        ArrayList<Integer>[] newBuckets = createArrayOfLists(size);
        for (ArrayList<Integer> current : buckets) {
            for (Integer value : current) {
                newBuckets[hash(value)].add(value);
            }
        }
        return newBuckets;
    }

    /**
     * Method changing hash function and recreating the hash table
     * */
    public void changeHash(Function<Integer, Integer> function) {
        hashFunction = function;
        buckets = update();
    }

    /**
     * Method returning hash value
     * */
    private int hash(int value) {
        value = hashFunction.apply(value);
        return value % size;
    }

    /**
     * Method expanding hash table two times
     * */
    private void expand() {
        size *= 2;
        buckets = update();
    }

    /**
     * Method adding value to the table
     * If the table is full, it is replaced by a more extended
     * */
    public void add(int value) {
        if (getLoadFactor() >= 1) {
            expand();
        }
        buckets[hash(value)].add(value);
    }

    /**
     * Method removing value from the table
     * */
    public void remove(int value) {
        for (int i = 0; i < buckets[hash(value)].size(); i++) {
            if (buckets[hash(value)].get(i) == value) {
                buckets[hash(value)].remove(i);
                break;
            }
        }
    }

    /**
     * Method checking value for existence
     * */
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

    private double getLoadFactor() {
        return (double) getFilledCellsNumber() / size;
    }

    /**
     * Method printing statistics
     * */
    public void printStatistics() {
        System.out.println("Amount of cells: " + size);
        System.out.println("Load factor: " + getLoadFactor());
        System.out.println("Amount of conflicting cells: " + getConflictCellsNumber());
        if (getConflictCellsNumber() != 0) {
            System.out.println("Maximum length in a conflict cell: " + getMaximumConflictLength());
        }
    }
}