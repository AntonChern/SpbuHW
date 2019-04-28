package com.AntonChernikov.g144;

/**
 * Class describing unique list
 * */
public class UniqueList<T> {
    private class ListElement {
        private T value;
        private ListElement next = null;
        private ListElement(T value, ListElement next) {
            this.value = value;
            this.next = next;
        }
    }
    private ListElement first;

    /**
     * Method checking unique list for emptiness
     * */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Method adding value to unique list
     * */
    public void add(T value) throws ElementExistsException {
        if (exists(value)) {
            throw new ElementExistsException();
        }
        first = new ListElement(value, first);
    }

    /**
     * Method removing value from unique list
     * */
    public void remove(T value) throws NoElementException {
        if (!exists(value)) {
            throw new NoElementException();
        }
        if (value.equals(first.value)) {
            first = first.next;
            return;
        }
        ListElement current = first;
        while (current.next != null) {
            if (value.equals(current.next.value)) {
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
     * Method returning unique list size
     * */
    public int size() {
        int result = 0;
        ListElement current = first;
        while (current != null) {
            result++;
            current = current.next;
        }
        return result;
    }
}

class ElementExistsException extends Exception {
}

class NoElementException extends Exception {
}
