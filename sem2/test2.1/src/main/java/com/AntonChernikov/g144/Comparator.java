package com.AntonChernikov.g144;

import java.util.LinkedList;

public class Comparator implements ListsComparator {

    @Override
    public int compare(LinkedList first, LinkedList second) {
        return first.size() - second.size();
    }
}
