package com.AntonChernikov.g144;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    public TextField display;
    public Label dynamicExpression;
    private String expression = "";
    private String number = "0";

    /** Method updating expression and nulling number */
    private void updateExpression() {
        if (!number.equals("")) {
            expression += updateValue(isNotNumber() ? "0" : display.getText());
        }
        number = "";
    }

    /** Method discarding the extra zeros after the point of the value */
    private String updateValue(String value) {
        if (value.length() > 2) {
            String temp = value.replaceAll("0", "");
            if (temp.charAt(temp.length() - 1) == '.') {
                return value.substring(0, value.indexOf('.'));
            }
        }
        return value;
    }

    /** Method nulling number */
    private void cleanNumber() {
        if (!expression.isEmpty() && Character.isDigit(expression.charAt(expression.length() - 1))) {
            expression = "";
        }
        number = "0";
        display.setText("0");
    }

    /** Method returning true if the result of counting is not number and false if it is*/
    private boolean isNotNumber() {
        return display.getText().equals("Infinity") ||
               display.getText().equals("-Infinity") ||
               display.getText().equals("NaN");
    }

    /** Method reacting for action */
    public void act(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();
        String pressedButtonId = pressedButton.getId();
        String pressedButtonText = pressedButton.getText();

        Calculator calculator = new Calculator();
        switch (pressedButtonId) {
            case "point": {
                if (number.isEmpty()) {
                    number = "0";
                }
                number += pressedButtonText;
                display.setText(number);
                break;
            }
            case "digitOne":
            case "digitTwo":
            case "digitThree":
            case "digitFour":
            case "digitFive":
            case "digitSix":
            case "digitSeven":
            case "digitEight":
            case "digitNine":
            case "digitZero": {
                if (number.equals("0")) {
                    number = pressedButtonText;
                }
                else {
                    number += pressedButtonText;
                }
                display.setText(number);
                break;
            }
            case "addition":
            case "subtraction":
            case "multiplication":
            case "division": {
                if (isNotNumber()) {
                    break;
                }
                updateExpression();

                if (!Character.isDigit(expression.charAt(expression.length() - 1))) {
                    expression = expression.substring(0, expression.length() - 1);
                }
                display.setText(updateValue(calculator.calculate(expression)));
                expression += pressedButtonText;
                dynamicExpression.setText(expression);
                break;
            }
            case "equals": {
                updateExpression();

                String result = updateValue(calculator.calculate(expression));
                display.setText(result);
                dynamicExpression.setText("");
                expression = "";
                number = "0";
                break;
            }
            case "deletion": {
                if (!number.isEmpty()) {
                    number = number.substring(0, number.length() - 1);
                    display.setText(number.isEmpty() ? "0" : number);
                }
                break;
            }
            case "cleanEntry": {
                cleanNumber();
                break;
            }
            case "clean": {
                cleanNumber();
                expression = "";
                dynamicExpression.setText("");
                break;
            }
            default: {
                break;
            }
        }
    }
}
