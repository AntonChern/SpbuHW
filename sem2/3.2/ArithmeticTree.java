package com.AntonChernikov.g144;

import java.io.FileReader;
import java.io.IOException;

/**
 * Class describing an arithmetic expression parse tree
 * */
public class ArithmeticTree {

    private Node root = null;

    /**
     * Method calculating an expression on a tree
     * */
    public int calculate() {
        return root.get();
    }

    /**
     * Method displaying the tree
     * */
    public void print() {
        root.print();
    }

    /**
     * Method building a tree from a file
     * */
    public void fill(String fileName) {
        try (FileReader file = new FileReader(fileName)) {
            int current = file.read();
            root = buildTree(file);
        } catch (IOException e) {
            System.out.println("Input/output error: " + e);
        }
    }

    private Node buildTree(FileReader file) throws IOException {
        int symbol = file.read();
        Operation operation = new Operation(symbol);
        symbol = file.read();
        while (symbol == ' ') {
            symbol = file.read();
        }

        if (symbol == '(') {
            operation.leftChild = buildTree(file);
        }
        else {
            int value = 0;
            while (symbol != ' ') {
                value *= 10;
                value += symbol - '0';
                symbol = file.read();
            }
            operation.leftChild = new Operand(value);
        }
        symbol = file.read();
        if (symbol == '(') {
            operation.rightChild = buildTree(file);
        }
        else {
            int value = 0;
            while (symbol != ')') {
                value *= 10;
                value += symbol - '0';
                symbol = file.read();
            }
            operation.rightChild = new Operand(value);
        }
        symbol = file.read();
        return operation;
    }

    /**
     * Class describing the operation
     * */
    private class Operation implements Node {
        int value;
        Node leftChild;
        Node rightChild;

        private Operation(int value) {
            this.value = value;
        }

        public void print() {
            System.out.print("(" + (char)value + " ");
            leftChild.print();
            System.out.print(" ");
            rightChild.print();
            System.out.print(")");
        }

        public int get() {
            int result = 0;
            switch (value) {
                case '+': {
                    result = leftChild.get() + rightChild.get();
                    break;
                }
                case '-': {
                    result = leftChild.get() - rightChild.get();
                    break;
                }
                case '*': {
                    result = leftChild.get() * rightChild.get();
                    break;
                }
                case '/': {
                    result = leftChild.get() / rightChild.get();
                    break;
                }
                default: {
                    break;
                }
            }
            return result;
        }
    }

    /**
     * Class describing the operand
     * */
    private class Operand implements Node {
        int value;

        private Operand(int value) {
            this.value = value;
        }

        public void print() {
            System.out.print(value);
        }

        public int get() {
            return value;
        }
    }
}
