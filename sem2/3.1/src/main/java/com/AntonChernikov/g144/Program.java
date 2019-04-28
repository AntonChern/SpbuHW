package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    /**
     * Program allowing perform actions on a hash table
     * */
    public static void main(String[] args) {
        HashTable table = new HashTable(5);

        System.out.println("0 - escape");
        System.out.println("1 - add value");
        System.out.println("2 - remove value");
        System.out.println("3 - find value");
        System.out.println("4 - show statistics");
        System.out.println("5 - fill the table with data from the file");
        System.out.println("6 - change hash function\n");

        Scanner in = new Scanner(System.in);

        int command = -1;
        while (command != 0) {
            System.out.println("Enter command");
            command = in.nextInt();
            switch (command) {
                case 1: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    table.add(value);
                    System.out.println("Value added\n");
                    break;
                }
                case 2: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    table.remove(value);
                    System.out.println("Command completed\n");
                    break;
                }
                case 3: {
                    System.out.println("Enter value");
                    int value = in.nextInt();
                    if (table.exists(value)) {
                        System.out.println("Value exists");
                    }
                    else {
                        System.out.println("Value doesn't exist");
                    }
                    System.out.println();
                    break;
                }
                case 4: {
                    table.printStatistics();
                    System.out.println();
                    break;
                }
                case 5: {
                    table.fill("File.txt");
                    System.out.println("Table filled\n");
                    break;
                }
                case 6: {
                    System.out.println("Enter index of hash function (0 or 1)");
                    int index = in.nextInt();
                    while (index != 0 && index != 1) {
                        System.out.println("Incorrect data");
                        index = in.nextInt();
                    }
                    table.changeHash(index);
                    System.out.println("Hash function changed\n");
                    break;
                }
            }
        }
    }
}
