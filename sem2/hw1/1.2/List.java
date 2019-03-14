package com.AntonChernikov.g144;

/**
 * Single linked list
 * */
public class List {
    public class ListElement {
        int value;
        ListElement next;

        public ListElement(int value, ListElement next) {
            this.value = value;
            this.next = next;
        }
    }

    ListElement first = null;
    int size = 0;

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
