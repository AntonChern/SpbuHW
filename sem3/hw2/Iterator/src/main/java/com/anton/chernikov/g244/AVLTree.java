package com.anton.chernikov.g244;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** The AVLTree class realizes the functionality of AVL tree */
public class AVLTree<T> implements Collection<T> {

    private Node root;
    private int size = 0;

    /** Returns the number of elements in the tree */
    @Override
    public int size() {
        return size;
    }

    /** Checks the tree for emptiness */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /** Checks the existence of an element in the tree */
    @Override
    public boolean contains(Object value) {
        Node foundElement = root;
        while (foundElement != null) {
            if (value == foundElement.value) {
                return true;
            } else {
                if (((Comparable)value).compareTo(foundElement.value) > 0) {
                    foundElement = foundElement.rightChild;
                } else {
                    foundElement = foundElement.leftChild;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new AVLTreeIterator();
    }

    /** Returns an array converted from a tree */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int index = 0;
        for (T value : this) {
            result[index++] = value;
        }
        return result;
    }

    /** Adds tree elements to an array */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        Iterator<T> iterator = iterator();
        int startIndex = 0;
        while (a[startIndex] != null) {
            startIndex++;
        }
        try {
            for (int i = startIndex; i < size() + startIndex; i++) {
                a[i] = (T1)iterator.next();
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds array");
        }
        return a;
    }

    /** Removes a value from a tree */
    @Override
    public boolean remove(Object value) {
        if (contains(value)) {
            root = root.removeElement(value);
            size--;
            return true;
        }
        return false;
    }

    /** Checks for the existence of elements from the collection */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    /** Adds all elements from the tree that are in the collection */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T value : c) {
            if (contains(value)) {
                return false;
            }
        }
        for (T value : c) {
            add(value);
        }
        return true;
    }

    /** Removes all elements from the tree that are in the collection */
    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        for (Object value : c) {
            remove(value);
        }
        return true;
    }

    /** Removes all elements from the tree, except those in the collection */
    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        for (T value : this) {
            if (!c.contains(value)) {
                remove(value);
            }
        }
        return true;
    }

    /** Removes all elements from the tree */
    @Override
    public void clear() {
        root = null;
    }

    /** Adds a value from a tree */
    @Override
    public boolean add(T value)
    {
        if (root != null) {
            if (!root.addElement(value)) {
                return false;
            }
            root = root.balance();
        } else {
            root = new Node(value);
        }
        size++;
        return true;
    }

    /** Returns the maximum number of nodes from this node to the leaf */
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    /** The Node class describes the functionality of the AVL tree element */
    private class Node {

        private T value;
        private int height = 0;
        private Node leftChild = null;
        private Node rightChild = null;

        private Node(T value) {
            this.value = value;
        }

        /** Updates height */
        private void updateHeight() {
            int heightLeft = height(this.leftChild);
            int heightRight = height(this.rightChild);
            this.height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
        }

        /** Returns the difference between the height of the right and left nodes */
        private int balanceFactor() {
            return height(this.rightChild) - height(this.leftChild);
        }

        /** Performs rotate to left around this node */
        private Node rotateLeft() {
            Node pivot = this.rightChild;
            this.rightChild = pivot.leftChild;
            pivot.leftChild = this;
            this.updateHeight();
            pivot.updateHeight();
            return pivot;
        }

        /** Performs rotate to right around this node */
        private Node rotateRight() {
            Node pivot = this.leftChild;
            this.leftChild = pivot.rightChild;
            pivot.rightChild = this;
            this.updateHeight();
            pivot.updateHeight();
            return pivot;
        }

        /** Balances this node */
        private Node balance() {
            this.updateHeight();
            if (this.balanceFactor() == 2)
            {
                if (this.rightChild.balanceFactor() < 0)
                {
                    this.rightChild = this.rightChild.rotateRight();
                }
                return this.rotateLeft();
            }
            if (this.balanceFactor() == -2)
            {
                if (this.leftChild.balanceFactor() > 0)
                {
                    this.leftChild = this.leftChild.rotateLeft();
                }
                return this.rotateRight();
            }
            return this;
        }

        /** Iteratively adds a node to the tree */
        private boolean addElement(T value) {
            if (!value.equals(this.value)) {
                if (((Comparable)this.value).compareTo(value) < 0) {
                    if (this.rightChild != null) {
                        if (!this.rightChild.addElement(value)) {
                            return false;
                        }
                        this.rightChild = this.rightChild.balance();
                    } else {
                        this.rightChild = new Node(value);
                    }
                } else {
                    if (this.leftChild != null) {
                        if (!this.leftChild.addElement(value)) {
                            return false;
                        }
                        this.leftChild = this.leftChild.balance();
                    } else {
                        this.leftChild = new Node(value);
                    }
                }
            } else {
                return false;
            }
            return true;
        }

        /** Removes this node from the tree */
        private Node removeElement() {
            if (this.leftChild != null) {
                if (this.rightChild != null) {
                    Node current = this.rightChild;

                    while (current.leftChild != null) {
                        current = current.leftChild;
                    }

                    //swap
                    T value = this.value;
                    this.value = current.value;
                    current.value = value;

                    this.rightChild = this.rightChild.removeElement(current.value);
                    return this.balance();
                }
                return this.leftChild;
            }
            return this.rightChild;
        }

        /** Finds the node by value and removing it from the tree */
        private Node removeElement(Object value) {
            if (value.equals(this.value)) {
                return this.removeElement();
            }
            if (((Comparable)value).compareTo(this.value) < 0) {
                if (this.leftChild != null) {
                    this.leftChild = this.leftChild.removeElement(value);
                    return this.balance();
                }
            } else {
                if (this.rightChild != null) {
                    this.rightChild = this.rightChild.removeElement(value);
                    return this.balance();
                }
            }
            return this;
        }
    }

    /** The AVLTreeIterator class describes iterator AVL tree */
    private class AVLTreeIterator implements Iterator<T> {

        private ArrayDeque<T> elements;
        private T bufferedValue;

        private AVLTreeIterator() {
            elements = new ArrayDeque<>();
            addElement(root);
        }

        /** Fills queue with elements from tree by in-order traversal */
        private void addElement(Node node) {
            if (node == null) {
                return;
            }
            addElement(node.leftChild);
            elements.addLast(node.value);
            addElement(node.rightChild);
        }

        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            if (elements.isEmpty()) {
                return false;
            }
            if (!contains(elements.peekFirst())) {
                elements.pollFirst();
                return hasNext();
            }
            return true;
        }

        /** {@inheritDoc} */
        @Override
        public T next() {
            if (elements.isEmpty()) {
                throw new NoSuchElementException();
            }
            if (!contains(elements.peekFirst())) {
                elements.pollFirst();
                return next();
            }
            return bufferedValue = elements.pollFirst();
        }

        /** {@inheritDoc} */
        @Override
        public void remove() {
            if (bufferedValue != null && contains(bufferedValue)) {
                AVLTree.this.remove(bufferedValue);
                bufferedValue = null;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}