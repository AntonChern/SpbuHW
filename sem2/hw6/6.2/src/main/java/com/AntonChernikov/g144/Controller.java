package com.AntonChernikov.g144;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;

public class Controller {

    public Label information;
    private boolean gameOver = false;
    private byte move = 0;
    private byte turn = 1;
    private List<Integer> winCombination = Arrays.asList(7, 56, 73, 84, 146, 273, 292, 448);
    private int[] playingField = {0, 0};

    /**
     * Method that processes the player's turn
     * Sets a symbol on a pressed button
     * Marks the move on the field of the corresponding player (the field is represented by nine bits, 1 if there is a symbol, 0 if not)
     * Checks the player's field for winnings using masks (after the fourth move)
     * If there are 9 moves, then a draw
     * Changes turn
     * */
    public void makeMove(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();
        if (!pressedButton.getText().equals("") || gameOver) {
            return;
        }
        String symbol = (turn == 1) ? "X" : "O";
        pressedButton.setText(symbol);

        switch (pressedButton.getId()) {
            case "topLeft": {
                playingField[turn] |= 256;
                break;
            }
            case "topCenter": {
                playingField[turn] |= 128;
                break;
            }
            case "topRight": {
                playingField[turn] |= 64;
                break;
            }
            case "middleLeft": {
                playingField[turn] |= 32;
                break;
            }
            case "middleCenter": {
                playingField[turn] |= 16;
                break;
            }
            case "middleRight": {
                playingField[turn] |= 8;
                break;
            }
            case "bottomLeft": {
                playingField[turn] |= 4;
                break;
            }
            case "bottomCenter": {
                playingField[turn] |= 2;
                break;
            }
            case "bottomRight": {
                playingField[turn] |= 1;
                break;
            }
        }

        move++;
        if (move > 4) {
            if (winCombination.stream().anyMatch(c -> c == (c & playingField[turn]))) {
                gameOver = true;
                information.setText((turn == 1 ? "1st" : "2nd") + " player won!");
                return;
            }

            if (move == 9) {
                information.setText("Draw");
                return;
            }
        }

        turn = (byte) (turn == 1 ? 0 : 1);
        information.setText((turn == 1 ? "1st" : "2nd") + " player's turn");
    }
}
