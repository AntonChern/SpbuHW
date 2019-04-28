package com.AntonChernikov.g144;

import java.util.*;

/**
 * Class realizing the functionality of AVL tree
 * */
class AVLTree<T> implements Collection<T> {
    /**
     * Method returning the number of elements in the tree
     * */
    @Override
    public int size() {
        int result = 0;
        for (T value : this) {
            result++;
        }
        return result;
    }

    /**
     * Method checking the tree for emptiness
     * */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method checking the existence of an element in the tree
     * */
    @Override
    public boolean contains(Object value) {
        Node foundElement = root;
        while (foundElement != null)
        {
            if (value == foundElement.value)
            {
                return true;
            }
            else
            {
                if (((Comparable)value).compareTo(foundElement.value) > 0)
                {
                    foundElement = foundElement.rightChild;
                }
                else
                {
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

    /**
     * Method returning an array converted from a tree
     * */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        Iterator<T> iterator = iterator();
        for (int i = 0; i < size(); i++) {
            result[i] = iterator.next();
        }
        return result;
    }

    /**
     * Method adding tree elements to an array
     * */
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

    private Node removeElement(Node node) {
        if (node.leftChild != null) {
            if (node.rightChild != null) {
                Node current = node.rightChild;

                while (current.leftChild != null) {
                    current = current.leftChild;
                }

                //swap
                T value = node.value;
                node.value = current.value;
                current.value = value;

                node.rightChild = removeElement(node.rightChild, current.value);
                return balance(node);
            }
            else {
                return node.leftChild;
            }
        }
        else {
            if (node.rightChild != null) {
                return node.rightChild;
            }
            else {
                return null;
            }
        }
    }

    private Node removeElement(Node node, Object value) {
        if (value.equals(node.value)) {
            return removeElement(node);
        }
        if (((Comparable)value).compareTo(node.value) < 0) {
            if (node.leftChild != null) {
                node.leftChild = removeElement(node.leftChild, value);
                return balance(node);
            }
        }
        else {
            if (node.rightChild != null) {
                node.rightChild = removeElement(node.rightChild, value);
                return balance(node);
            }
        }
        return node;
    }

    /**
     * Method removing a value from a tree
     * */
    @Override
    public boolean remove(Object value) {
        if (contains(value)) {
            root = removeElement(root, value);
            return true;
        }
        return false;
    }

    /**
     * Method checking for the existence of elements from the collection
     * */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method adding all elements from the tree that are in the collection
     * */
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


    /**
     * Method removing all elements from the tree that are in the collection
     * */
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

    /**
     * Method of removing all elements from the tree, except those in the collection
     * */
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

    /**
     * Method removing all elements from the tree
     * */
    @Override
    public void clear() {
        root = null;
    }

    private Node root;

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        int heightLeft = height(node.leftChild);
        int heightRight = height(node.rightChild);
        node.height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
    }

    private int balanceFactor(Node node) {
        return height(node.rightChild) - height(node.leftChild);
    }

    private Node rotateLeft(Node root) {
        Node pivot = root.rightChild;
        root.rightChild = pivot.leftChild;
        pivot.leftChild = root;
        updateHeight(root);
        updateHeight(pivot);
        return pivot;
    }

    private Node rotateRight(Node root) {
        Node pivot = root.leftChild;
        root.leftChild = pivot.rightChild;
        pivot.rightChild = root;
        updateHeight(root);
        updateHeight(pivot);
        return pivot;
    }

    private Node balance(Node node) {
        updateHeight(node);
        if (balanceFactor(node) == 2)
        {
            if (balanceFactor(node.rightChild) < 0)
            {
                node.rightChild = rotateRight(node.rightChild);
            }
            return rotateLeft(node);
        }
        if (balanceFactor(node) == -2)
        {
            if (balanceFactor(node.leftChild) > 0)
            {
                node.leftChild = rotateLeft(node.leftChild);
            }
            return rotateRight(node);
        }
        return node;
    }

    private boolean addElement(Node node, T value) {
        if (!value.equals(node.value)) {
            if (((Comparable)node.value).compareTo(value) < 0) {
                if (node.rightChild != null) {
                    if (!addElement(node.rightChild, value)) {
                        return false;
                    }
                    node.rightChild = balance(node.rightChild);
                }
                else {
                    node.rightChild = new Node(value);
                }
            }
            else {
                if (node.leftChild != null) {
                    if (!addElement(node.leftChild, value)) {
                        return false;
                    }
                    node.leftChild = balance(node.leftChild);
                }
                else {
                    node.leftChild = new Node(value);
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    /**
     * Method adding a value from a tree
     * */
    @Override
    public boolean add(T value)
    {
        if (root != null) {
            if (!addElement(root, value)) {
                return false;
            }
            root = balance(root);
        }
        else {
            root = new Node(value);
        }
        return true;
    }

    private class AVLTreeIterator implements Iterator<T> {

        private ArrayDeque<T> elements = null;

        private AVLTreeIterator() {
            elements = new ArrayDeque<>();
            addElement(root);
        }

        private void addElement(Node node) {
            if (node == null) {
                return;
            }
            addElement(node.leftChild);
            elements.addLast(node.value);
            addElement(node.rightChild);
        }

        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        @Override
        public T next() {
            for (T current : elements) {
                if (!contains(current)) {
                    elements.remove(current);
                }
            }
            return elements.pollFirst();
        }

    }

    private class Node {
        private T value;

        private int height = 0;
        private Node leftChild = null;
        private Node rightChild = null;
        private Node(T value) {
            this.value = value;
        }
    }
}





























