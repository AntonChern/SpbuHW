package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("0 - escape");
        System.out.println("1 - add element");
        System.out.println("2 - take element");
        System.out.println("3 - check for emptiness");
        System.out.println("4 - print stack");
        System.out.println("5 - print size of stack");
        System.out.println();

        int command = 1;
        Stack stack = new Stack();
        Scanner in = new Scanner(System.in);
        while (command != 0) {
            System.out.println("Enter command");
            command = in.nextInt();
            switch (command) {
                case 1: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    stack.push(value);
                    System.out.println("Element added");
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.println("Element taken - " + stack.pop());
                    System.out.println();
                    break;
                }
                case 3: {
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty");
                    }
                    else {
                        System.out.println("Stack isn't empty");
                    }
                    System.out.println();
                    break;
                }
                case 4: {
                    stack.print();
                    System.out.println();
                    break;
                }
                case 5: {
                    System.out.println("Size of stack - " + stack.size());
                    System.out.println();
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}