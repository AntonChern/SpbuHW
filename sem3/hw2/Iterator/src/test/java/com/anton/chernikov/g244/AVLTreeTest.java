package com.anton.chernikov.g244;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void size() {
        int size = 5;
        AVLTree<Integer> tree = new AVLTree<>();

        for (int i = 0; i < size; i++) {
            tree.add(i);
        }
        assertEquals(size, tree.size());
    }

    @Test
    void isEmpty() {
        AVLTree<Integer> tree = new AVLTree<>();
        int value = 0;
        tree.add(value);
        assertFalse(tree.isEmpty());
        tree.remove(value);
        assertTrue(tree.isEmpty());
    }

    @Test
    void iterator() {
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            tree.add(i);
        }
        int i = 0;
        for (int value : tree) {
            assertTrue(tree.contains(i));
            i++;
        }
    }

    @Test
    void toArray() {
        int size = 5;
        Integer[] expectedArray = new Integer[size];
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < size; i++) {
            expectedArray[i] = i;
            tree.add(i);
        }
        Object[] actualArray = null;
        actualArray = tree.toArray();
        for (int i = 0; i < size; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    @Test
    void toArray1() {
        int size = 5;
        Integer[] expectedArray = new Integer[size];
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < size; i++) {
            expectedArray[i] = i;
            tree.add(i);
        }
        Integer[] actualArray = new Integer[size];
        actualArray = tree.toArray(actualArray);
        for (int i = 0; i < size; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    @Test
    void addAllAndContainsAll() {
        Collection<Integer> collection = new LinkedList<>();
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            collection.add(i);
        }
        tree.addAll(collection);
        assertTrue(tree.containsAll(collection));
    }

    @Test
    void removeAll() {
        Collection<Integer> collection = new ArrayList<>();
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            collection.add(i);
            tree.add(i);
        }
        tree.removeAll(collection);
        assertFalse(tree.containsAll(collection));
    }

    @Test
    void retainAll() {
        Collection<Integer> collection = new LinkedList<>();
        AVLTree<Integer> tree = new AVLTree<>();
        int sizeCollection = 5;
        int sizeTree = 10;
        for (int i = 0; i < sizeTree; i++) {
            tree.add(i);
        }
        for (int i = 0; i < sizeCollection; i++) {
            collection.add(i);
        }
        tree.retainAll(collection);
        for (int i = 0; i < sizeTree; i++) {
            if (i < sizeCollection) {
                assertTrue(tree.contains(i));
            }
            else {
                assertFalse(tree.contains(i));
            }
        }
    }

    @Test
    void clear() {
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            tree.add(i);
        }
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    void addAndRemove() {
        AVLTree<Integer> tree = new AVLTree<>();
        int value = 0;
        tree.add(value);
        assertTrue(tree.contains(value));
        tree.remove(value);
        assertFalse(tree.contains(value));
    }

    @Test
    void twoIteratorsTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 10;
        int firstPoint = 5;
        int secondPoint = 8;
        for (int i = 0; i < size; i++) {
            tree.add(i + 1);
        }
        Iterator<Integer> firstIterator = tree.iterator();
        Iterator<Integer> secondIterator = tree.iterator();

        for (int i = 0; i < firstPoint; i++) {
            firstIterator.next();
            secondIterator.next();
        }
        secondIterator.remove();

        for (int i = firstPoint; i < secondPoint; i++) {
            secondIterator.next();
        }
        secondIterator.remove();

        for (int i = firstPoint + 1; i <= size; i++) {
            if (i != secondPoint) {
                assertEquals(Integer.valueOf(i), firstIterator.next());
            }
        }
        assertFalse(firstIterator.hasNext());
    }

    @Test
    void NoSuchElementExceptionIteratorTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            tree.add(i + 1);
        }
        Iterator<Integer> iterator = tree.iterator();
        for (int i = 0; i < size; i++) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void IllegalStateExceptionIteratorTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            tree.add(i + 1);
        }
        Iterator<Integer> iterator = tree.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }
}