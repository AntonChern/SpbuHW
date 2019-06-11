package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    /** Program describing action on the AVL tree */
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        Scanner in = new Scanner(System.in);

        System.out.println("0 - escape");
        System.out.println("1 - add element");
        System.out.println("2 - remove element");
        System.out.println("3 - find element");
        System.out.println("4 - get size of tree");
        System.out.println("5 - check for emptiness\n");

        int command = -1;
        while (command != 0) {
            System.out.println("Enter command");
            command = in.nextInt();

            switch (command) {
                case 1: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    if (tree.add(value)) {
                        System.out.println("Element added\n");
                    }
                    else {
                        System.out.println("Element not added\n");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    if (tree.remove(value)) {
                        System.out.println("Element removed\n");
                    }
                    else {
                        System.out.println("Element not removed\n");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    if (tree.contains(value)) {
                        System.out.println("This element exists\n");
                    }
                    else {
                        System.out.println("This element doesn't exist\n");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Size = " + tree.size() + "\n");
                    break;
                }
                case 5: {
                    if (tree.isEmpty()) {
                        System.out.println("List is empty\n");
                    }
                    else {
                        System.out.println("List isn't empty\n");
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }

    }
}