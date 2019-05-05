package com.AntonChernikov.g144;

import java.util.Scanner;

public class Program {
    /**
     * Program describing action on the priority queue in three ways
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

        PriorityQueue[] queue = new PriorityQueue[3];
        queue[0] = new PriorityQueue<Integer>();
        queue[1] = new PriorityQueue<String>();
        queue[2] = new PriorityQueue<Double>();

        System.out.println("0 - escape");
        System.out.println("1 - add element");
        System.out.println("2 - get element\n");

        int command = -1;
        while (command != 0) {
            System.out.println("Enter command");
            command = in.nextInt();

            switch (command) {
                case 1: {
                    System.out.println("Enter value");
                    Object value = null;
                    switch (codeOfType) {
                        case 0: {
                            value = in.nextInt();
                            break;
                        }
                        case 1: {
                            value = in.next();
                            break;
                        }
                        case 2: {
                            value = in.nextDouble();
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    System.out.println("Enter priority");
                    int priority = in.nextInt();
                    queue[codeOfType].enqueue(value, priority);
                    System.out.println("Element added\n");
                    break;
                }
                case 2: {
                    try {
                        System.out.println("Returned value is " + queue[codeOfType].dequeue() + "\n");
                    } catch(Exception e) {
                        System.out.println("Queue is empty\n");
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
