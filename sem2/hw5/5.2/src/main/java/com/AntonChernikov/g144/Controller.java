package com.AntonChernikov.g144;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class Controller {

    public Spinner secondOperand;
    public Spinner firstOperand;
    public ChoiceBox<Character> operator;
    public TextField result;

    /**
     * Method setting operator values
     * */
    @FXML
    private void initialize() {
        operator.setValue('+');
        operator.setItems(FXCollections.observableArrayList('+', '-', '*', '/'));
    }

    /**
     * Method that counts the value of the operands and the operator and returning it
     * */
    public static double calculate(double firstValue, double secondValue, char operation) {
        double answer = 0;
        switch (operation) {
            case '+': {
                answer = firstValue + secondValue;
                break;
            }
            case '-': {
                answer = firstValue - secondValue;
                break;
            }
            case '*': {
                answer = firstValue * secondValue;
                break;
            }
            case '/': {
                answer = firstValue / secondValue;
                break;
            }
        }
        return answer;
    }

    /**
     * Method writing counted answer to the text field
     * */
    public void setAnswer() {
        result.setText(calculate((Integer)firstOperand.getValue(), (Integer)secondOperand.getValue(), operator.getValue()) + "");
    }
}
