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
     * Method that counts the value of the operands and the operator and writes it to the text field
     * */
    public void calculate() {
        double answer = 0;
        double firstValue = (Integer)firstOperand.getValue();
        double secondValue = (Integer)secondOperand.getValue();
        char operation = operator.getValue();
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
        result.setText(answer + "");
    }
}
