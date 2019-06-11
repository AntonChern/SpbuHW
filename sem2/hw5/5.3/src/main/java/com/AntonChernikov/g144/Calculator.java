package com.AntonChernikov.g144;

import java.util.Stack;

public class Calculator {
    /** Method returning a postfix arithmetic expression */
    public String postfixNotation(String expression) {
        StringBuilder outputLine = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '.':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        outputLine.append(expression.charAt(i));
                        i++;
                    }
                    i--;
                    outputLine.append(" ");
                    break;
                }
                case '(': {
                    stack.push(expression.charAt(i));
                    break;
                }
                case ')': {
                    char element = stack.pop();
                    while (element != '(') {
                        outputLine.append(element);
                        element = stack.pop();
                    }
                    break;
                }
                case '+':
                case '-':
                case '*':
                case '/': {
                    while (!stack.isEmpty()) {
                        char element = stack.pop();
                        if (priority(element) >= priority(expression.charAt(i))) {
                            outputLine.append(element);
                        } else {
                            stack.push(element);
                            break;
                        }
                    }
                    stack.push(expression.charAt(i));
                    break;
                }
            }
        }
        while (!stack.isEmpty()) {
            outputLine.append(stack.pop());
        }
        return outputLine.toString();
    }

    /** Method calculating the value of arithmetic expression */
    public String calculate(String expression) {
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        char lastSymbol = expression.charAt(expression.length() - 1);
        if (!Character.isDigit(lastSymbol)) {
            expression = expression.substring(0, expression.length() - 1);
            if (lastSymbol == '-') {
                return "0";
            }
        }
        String outputLine = postfixNotation(expression);
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < outputLine.length(); i++) {
            switch (outputLine.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    double value = 0;
                    boolean existsFractional = false;
                    int decimals = 0;
                    while (i < outputLine.length() && (Character.isDigit(outputLine.charAt(i)) || outputLine.charAt(i) == '.')) {
                        if (existsFractional) {
                            decimals++;
                        }

                        if (outputLine.charAt(i) == '.') {
                            existsFractional = true;
                        } else {
                            value = value * 10 + (outputLine.charAt(i) - '0');
                        }
                        i++;
                    }
                    for (int j = 0; j < decimals; j++) {
                        value /= 10;
                    }
                    stack.push(value);
                    break;
                }
                case '+': {
                    double second = stack.pop();
                    stack.push(stack.pop() + second);
                    break;
                }
                case '-': {
                    double second = stack.pop();
                    stack.push(stack.pop() - second);
                    break;
                }
                case '*': {
                    double second = stack.pop();
                    stack.push(stack.pop() * second);
                    break;
                }
                case '/': {
                    double second = stack.pop();
                    stack.push(stack.pop() / second);
                    break;
                }
            }
        }
        double result = stack.pop();
        switch (lastSymbol) {
            case '+': {
                return Double.toString(result + result);
            }
            case '*': {
                return Double.toString(result * result);
            }
            case '/': {
                return Double.toString(result / result);
            }
            default: {
                return Double.toString(result);
            }
        }
    }

    private int priority(char element) {
        switch (element) {
            case '*':
            case '/': {
                return 1;
            }
            case '+':
            case '-': {
                return 0;
            }
            case '(': {
                return -1;
            }
        }
        return -1;
    }
}
