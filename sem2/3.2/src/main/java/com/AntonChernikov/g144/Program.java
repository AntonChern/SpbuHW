package com.AntonChernikov.g144;

public class Program {
    public static void main(String[] args) {
        ArithmeticTree tree = new ArithmeticTree();
        tree.fill("File.txt");
        tree.print();
        System.out.print("\nValue = " + tree.calculate());
    }
}
