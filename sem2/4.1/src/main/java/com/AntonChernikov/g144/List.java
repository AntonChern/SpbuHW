package com.AntonChernikov.g144;

/**
 * Class describing single linked list
 * */
public class List<T> {

    private ListElement first = null;
    private int size = 0;

    /**
     * Method checking list for emptiness
     * */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Method adding value to list
     * */
    public void add(T value) throws ElementExistsException {
        first = new ListElement(value, first);
        size++;
    }

    /**
     * Method removing value from list
     * */
    public void remove(T value) throws NoElementException {
        if (first.value.equals(value)) {
            size--;
            first = first.next;
            return;
        }
        ListElement current = first;
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                size--;
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    /**
     * Method checking the existence of an element
     * */
    public boolean exists(T value) {
        ListElement current = first;
        while (current != null) {
            if (value.equals(current.value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Method returning list size
     * */
    public int size() {
        return size;
    }

    private class ListElement {
        private T value;
        private ListElement next;

        private ListElement(T value, ListElement next) {
            this.value = value;
            this.next = next;
        }
    }
}

























