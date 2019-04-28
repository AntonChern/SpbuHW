package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    /**
     * Program describing action on the unique list in three ways
     * */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("0 - integer");
        System.out.println("1 - string");
        System.out.println("2 - double\n");

        int codeOfType = -1;
        while (codeOfType < 0 || codeOfType > 2) {
            System.out.println("Choose the type");
            codeOfType = in.nextInt();
            if (codeOfType < 0 || codeOfType > 2) {
                System.out.println("Incorrect data\n");
            }
        }

        System.out.println("The type is chosen\n");

        UniqueList[] lists = new UniqueList[3];
        lists[0] = new UniqueList<Integer>();
        lists[1] = new UniqueList<String>();
        lists[2] = new UniqueList<Double>();

        System.out.println("0 - escape");
        System.out.println("1 - add element");
        System.out.println("2 - remove element");
        System.out.println("3 - find element");
        System.out.println("4 - get size of list");
        System.out.println("5 - check for emptiness\n");


        int command = -1;
        while (command != 0) {
            System.out.println("Enter command");
            command = in.nextInt();

            switch (command) {
                case 1: {
                    System.out.println("Enter value");
                    Object value = in.next();
                    try {
                        lists[codeOfType].add(value);
                        System.out.println("Element added\n");
                    } catch (ElementExistsException e) {
                        System.out.println("This element already exists\n");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter value");
                    Object value = in.next();
                    try {
                        lists[codeOfType].remove(value);
                        System.out.println("Element removed\n");
                    } catch(NoElementException e) {
                        System.out.println("There is no such element\n");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter value");
                    Object value = in.next();
                    if (lists[codeOfType].exists(value)) {
                        System.out.println("This element exists\n");
                    }
                    else {
                        System.out.println("This element doesn't exist\n");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Size = " + lists[codeOfType].size() + "\n");
                    break;
                }
                case 5: {
                    if (lists[codeOfType].isEmpty()) {
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
