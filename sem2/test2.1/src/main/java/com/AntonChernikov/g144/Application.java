package com.AntonChernikov.g144;

import java.util.Arrays;
import java.util.LinkedList;

public class Application {

    private SortedSet set;

    public Application() {
        set = new SortedSet();
    }

    public void addWords(String[] object) {
        for (String current : object) {
            String[] words = current.split(" ");
            set.add(new LinkedList(Arrays.asList(words)));
        }
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public String print() {
        return set.print();
    }
}