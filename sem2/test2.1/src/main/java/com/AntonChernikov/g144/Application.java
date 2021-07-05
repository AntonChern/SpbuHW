package com.AntonChernikov.g144;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class describing work with sorted set
 * */
public class Application {

    private SortedSet set;

    public Application() {
        set = new SortedSet();
    }

    /**
     * Method splitting lines into word lists and adding lists to the set
     * */
    public void addWords(String[] object) {
        for (String current : object) {
            String[] words = current.split(" ");
            set.add(new LinkedList(Arrays.asList(words)));
        }
    }

    /**
     * Method printing set
     * */
    public String print() {
        return set.print();
    }

    /**
     * Method checking set for emptiness
     * */
    public boolean isEmpty() {
        return set.isEmpty();
    }
}