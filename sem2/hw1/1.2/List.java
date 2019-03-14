package com.AntonChernikov.g144;

/**
 * Single linked list
 * */
public class List {
    private class ListElement {
        private int value;
        private ListElement next;

        private ListElement(int value, ListElement next) {
            this.value = value;
            this.next = next;
        }
    }

    private ListElement first = null;
    private int size = 0;

    public void addElement(int value) {
        ListElement newElement = new ListElement(value, first);
        first = newElement;
        size++;
    }

    public void deleteElement(int value) {
        if (first.value == value) {
            size--;
            first = first.next;
            return;
        }
        ListElement current = first;
        while (current.next != null) {
            if (current.next.value == value) {
                size--;
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public void print() {
        if (first == null) {
            return;
        }
        ListElement current = first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}
