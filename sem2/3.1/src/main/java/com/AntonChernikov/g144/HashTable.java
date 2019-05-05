package com.AntonChernikov.g144;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/** Class describing the functionality of the hash table */
public class HashTable<T> {
    private ArrayList<T>[] buckets;
    private int size;
    private Function<T, Integer> hashFunction = t -> {
        int n = t.hashCode();
        n += ~(n << 16);
        n ^=  (n >>  5);
        n +=  (n <<  3);
        n ^=  (n >> 13);
        n += ~(n <<  9);
        n ^=  (n >> 17);
        return n;
    };

    /** Method returning new array of lists by the number of cells equal to size */
    private ArrayList<T>[] createArrayOfLists(int size) {
        ArrayList<T>[] result = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            result[i] = new ArrayList<T>();
        }
        return result;
    }

    public HashTable(int size) {
        buckets = createArrayOfLists(size);
        this.size = size;
    }

    /** Method creating the table again */
    private ArrayList<T>[] update() {
        ArrayList<T>[] newBuckets = createArrayOfLists(size);
        for (ArrayList<T> current : buckets) {
            for (T value : current) {
                newBuckets[hash(value)].add(value);
            }
        }
        return newBuckets;
    }

    /** Method changing hash function and recreating the hash table */
    public void changeHash(Function<T, Integer> function) {
        hashFunction = function;
        buckets = update();
    }

    /** Method returning hash value */
    private int hash(T value) {
        return hashFunction.apply(value) % size;
    }

    /** Method expanding hash table two times */
    private void expand() {
        size *= 2;
        buckets = update();
    }

    /**
     * Method adding value to the table
     * If the table is full, it is replaced by a more extended
     * */
    public void add(T value) {
        if (getLoadFactor() >= 1) {
            expand();
        }
        buckets[hash(value)].add(value);
    }

    /** Method removing value from the table */
    public void remove(T value) {
        for (int i = 0; i < buckets[hash(value)].size(); i++) {
            if (buckets[hash(value)].get(i) == value) {
                buckets[hash(value)].remove(i);
                break;
            }
        }
    }

    /** Method checking value for existence */
    public boolean exists(T value) {
        for (T current : buckets[hash(value)]) {
            if (current.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /** Method filling a hash table with data from a file */
    public void fill(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fin = new Scanner(file);
            while (fin.hasNextInt()) {
                add((T)fin.next());
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

    /** Method printing statistics */
    public void printStatistics() {
        System.out.println("Amount of cells: " + size);
        System.out.println("Load factor: " + getLoadFactor());
        System.out.println("Amount of conflicting cells: " + getConflictCellsNumber());
        if (getConflictCellsNumber() != 0) {
            System.out.println("Maximum length in a conflict cell: " + getMaximumConflictLength());
        }
    }
}