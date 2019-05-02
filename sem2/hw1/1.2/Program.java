package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("0 - escape");
        System.out.println("1 - add element");
        System.out.println("2 - delete element");
        System.out.println("3 - print list");
        System.out.println("4 - print size of list");
        System.out.println();

        int command = 1;
        List list = new List();
        Scanner in = new Scanner(System.in);
        while (command != 0) {
            System.out.println("Enter command");
            command = in.nextInt();
            switch (command) {
                case 1: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    list.addElement(value);
                    System.out.println("Element added");
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    list.deleteElement(value);
                    System.out.println("Element deleted");
                    System.out.println();
                    break;
                }
                case 3: {
                    list.print();
                    System.out.println();
                    break;
                }
                case 4: {
                    System.out.println("Size of list - " + list.size());
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
