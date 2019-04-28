package com.AntonChernikov.g144;

import java.util.EmptyStackException;
import java.util.Scanner;

public class Program {
    /**
     * Program receiving an arithmetic expression as input and outputting it's result
     * */
    public static void main(String[] args) {
        System.out.println("Enter arithmetic expression");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();

        Calculator calculator = new Calculator();
        try {
            int result = calculator.calculate(expression);
            System.out.print("Value = " + result);
        } catch (EmptyStackException e) {
            System.out.print("There is an error in the entered expression");
        }
    }
}
