package com.AntonChernikov.g144;

/**
 Single linked list
 */
public class List {
    ListElement first;

    public List() {
        first = null;
    }

    public void addElement(int value) {
        ListElement newElement = new ListElement(value, first);
        first = newElement;
    }

    public void deleteElement(int value) {
        if (first.value == value) {
            first = first.next;
            return;
        }
        ListElement current = first;
        while (current.next.value != value) {
            current = current.next;
        }
        current.next = current.next.next;
    }

    public boolean exists(int value) {
        ListElement current = first;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        int result = 0;
        ListElement current = first;
        if (first == null) {
            return result;
        }
        result++;
        while (current.next != null) {
            result++;
            current = current.next;
        }
        return result;
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
