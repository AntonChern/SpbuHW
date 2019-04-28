package com.AntonChernikov.g144;

import java.util.EmptyStackException;

public class Calculator {
    /**
     * Method returning a postfix arithmetic expression
     * */
    public String postfixNotation(String expression) throws Exception {
        StringBuilder outputLine = new StringBuilder();
        StackOnLinkedList stack = new StackOnLinkedList();
        try {
            for (int i = 0; i < expression.length(); i++) {
                switch (expression.charAt(i)) {
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
                        outputLine.append(expression.charAt(i));
                        break;
                    }
                    case '(': {
                        stack.push(expression.charAt(i));
                        break;
                    }
                    case ')': {
                        char element = (char)stack.pop();
                        while (element != '(') {
                            outputLine.append(element);
                            element = (char)stack.pop();
                        }
                        break;
                    }
                    case '+':
                    case '-':
                    case '*':
                    case '/': {
                        while (!stack.isEmpty()) {
                            char element = (char)stack.pop();
                            if (priority(element) >= priority(expression.charAt(i))) {
                                outputLine.append(element);
                            }
                            else {
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
                outputLine.append((char) stack.pop());
            }
        } catch (EmptyStackException e) {
            throw new Exception();
        }
        return outputLine.toString();
    }

    /**
     * Method calculating the value of arithmetic expression
     * */
    public int calculate(String expression) throws Exception, Error {
        String outputLine = postfixNotation(expression);
        StackOnArray stack = new StackOnArray(20);
        try {
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
                        stack.push(outputLine.charAt(i) - '0');
                        break;
                    }
                    case '+': {
                        int second = stack.pop();
                        stack.push(stack.pop() + second);
                        break;
                    }
                    case '-': {
                        int second = stack.pop();
                        stack.push(stack.pop() - second);
                        break;
                    }
                    case '*': {
                        int second = stack.pop();
                        stack.push(stack.pop() * second);
                        break;
                    }
                    case '/': {
                        int second = stack.pop();
                        stack.push(stack.pop() / second);
                        break;
                    }
                }
            }
            return stack.pop();
        } catch (StackOverflowError e) {
            throw new Error();
        } catch (EmptyStackException e) {
            throw new Exception();
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
