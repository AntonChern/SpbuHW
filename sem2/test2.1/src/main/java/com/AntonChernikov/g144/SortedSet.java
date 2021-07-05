package com.AntonChernikov.g144;

import java.util.LinkedList;

/**
 * Class describing sorted set
 * */
public class SortedSet {

    private LinkedList<LinkedList> set;

    public SortedSet() {
        set = new LinkedList<>();
    }

    /**
     * Method checking set for emptiness
     * */
    public boolean isEmpty() {
        return set.size() == 0;
    }

    /**
     * Method adding list to set
     * */
    public void add(LinkedList list) {
        if (isEmpty()) {
            set.add(list);
        } else {
            Comparator comparator = new Comparator();
            for (int i = 0; i < set.size(); i++) {
                if (comparator.compare(list, set.get(i)) <= 0) {
                    set.add(i, list);
                    return;
                }
            }
            set.add(set.size(), list);
        }
    }

    /**
     * Method checking list for existence
     * */
    public boolean contains(LinkedList list) {
        for (LinkedList current : set) {
            if (current.equals(list)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method printing set
     * */
    public String print() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            result.append(i + 1).append(". ");
            for (Object value : set.get(i)) {
                result.append(value).append(" ");
            }
            result.append("\n");
        }
        System.out.print(result.toString());
        return result.toString();
    }
}
