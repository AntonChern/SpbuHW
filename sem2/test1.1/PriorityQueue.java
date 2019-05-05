package com.AntonChernikov.g144;

/**
 * Class describing priority queue
 * */
public class PriorityQueue<T> {
    private class QueueElement{

        private T value;
        private int priority;
        private QueueElement next = null;
        private QueueElement(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }

    }

    private QueueElement first = null;

    /**
     * Method adding value and numerical priority
     * */
    public void enqueue(T value, int priority) {
        QueueElement newElement = new QueueElement(value, priority);
        newElement.next = first;
        first = newElement;
        if (first.next != null) {
            putOnPlace();
        }
    }

    /**
     * Method putting queue item in descending order
     * */
    private void putOnPlace() {
        if (first.priority < first.next.priority) {
            QueueElement pilot = first;
            first = first.next;
            pilot.next = first.next;
            first.next = pilot;
        }
        QueueElement current = first;
        while (current.next.next != null) {
            if (current.next.priority < current.next.next.priority) {
                QueueElement pilot = current.next;
                current.next = current.next.next;
                pilot.next = current.next.next;
                current.next.next = pilot;
            }
            else {
                return;
            }
            current = current.next;
        }
    }

    /**
     * Method returning the value of the maximum priority
     * */
    public T dequeue() throws Exception {
        try {
            T result = first.value;
            first = first.next;
            return result;
        } catch (Exception e) {
            throw new Exception();
        }
    }
}