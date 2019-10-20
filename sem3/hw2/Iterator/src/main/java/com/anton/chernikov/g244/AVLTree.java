package com.anton.chernikov.g244;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;

/** Class realizing the functionality of AVL tree */
public class AVLTree<T> implements Collection<T> {

    private Node root;
    private int size = 0;

    /** Method returning the number of elements in the tree */
    @Override
    public int size() {
        return size;
    }

    /** Method checking the tree for emptiness */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /** Method checking the existence of an element in the tree */
    @Override
    public boolean contains(Object value) {
        Node foundElement = root;
        while (foundElement != null)
        {
            if (value == foundElement.value)
            {
                return true;
            } else {
                if (((Comparable)value).compareTo(foundElement.value) > 0)
                {
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

    /** Method returning an array converted from a tree */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int index = 0;
        for (T value : this) {
            result[index++] = value;
        }
        return result;
    }

    /** Method adding tree elements to an array */
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

    /** Method removing a value from a tree */
    @Override
    public boolean remove(Object value) {
        if (contains(value)) {
            root = root.removeElement(value);
            size--;
            return true;
        }
        return false;
    }

    /** Method checking for the existence of elements from the collection */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    /** Method adding all elements from the tree that are in the collection */
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

    /** Method removing all elements from the tree that are in the collection */
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

    /** Method of removing all elements from the tree, except those in the collection */
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

    /** Method removing all elements from the tree */
    @Override
    public void clear() {
        root = null;
    }

    /** Method adding a value from a tree */
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

    /** Method that returns the maximum number of nodes from this node to the leaf */
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    /** Class describing the functionality of the AVL tree element */
    private class Node {

        private T value;
        private int height = 0;
        private Node leftChild = null;
        private Node rightChild = null;

        private Node(T value) {
            this.value = value;
        }

        /** Method updating height */
        private void updateHeight() {
            int heightLeft = height(this.leftChild);
            int heightRight = height(this.rightChild);
            this.height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
        }

        /** Method returning the difference between the height of the right and left nodes */
        private int balanceFactor() {
            return height(this.rightChild) - height(this.leftChild);
        }

        /** Method performing rotate to left around this node */
        private Node rotateLeft() {
            Node pivot = this.rightChild;
            this.rightChild = pivot.leftChild;
            pivot.leftChild = this;
            this.updateHeight();
            pivot.updateHeight();
            return pivot;
        }

        /** Method performing rotate to right around this node */
        private Node rotateRight() {
            Node pivot = this.leftChild;
            this.leftChild = pivot.rightChild;
            pivot.rightChild = this;
            this.updateHeight();
            pivot.updateHeight();
            return pivot;
        }

        /** Method balancing this node */
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

        /** Method iteratively adding a node to the tree */
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

        /** Method removing this node from the tree */
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

        /** Method finding the node by value and removing it from the tree */
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

    /** Class describing iterator AVL tree */
    private class AVLTreeIterator implements Iterator<T> {

        private ArrayDeque<T> elements;
        private T bufferedValue;

        private AVLTreeIterator() {
            elements = new ArrayDeque<>();
            addElement(root);
        }

        /** Method filling queue with elements from tree by in-order traversal */
        private void addElement(Node node) {
            if (node == null) {
                return;
            }
            addElement(node.leftChild);
            elements.addLast(node.value);
            addElement(node.rightChild);
        }

        /** Auxiliary method removing odd elements */
        private void updateElements() {
            for (T current : elements) {
                if (!contains(current)) {
                    elements.remove(current);
                }
            }
        }

        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            updateElements();
            return !elements.isEmpty();
        }

        /** {@inheritDoc} */
        @Override
        public T next() {
            updateElements();
            bufferedValue = elements.peekFirst();
            return elements.pollFirst();
        }

        /** {@inheritDoc} */
        @Override
        public void remove() {
            updateElements();
            AVLTree.this.remove(bufferedValue);
            next();
        }
    }
}