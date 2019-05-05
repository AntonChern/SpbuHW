package com.AntonChernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    private int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private int player = 'X';
    private byte move = 0;

    public Label information;

    @FXML
    public Button a1;

    @FXML
    public Button a2;

    @FXML
    public Button a3;

    @FXML
    public Button b1;

    @FXML
    public Button b2;

    @FXML
    public Button b3;

    @FXML
    public Button c1;

    @FXML
    public Button c2;

    @FXML
    public Button c3;

    /**
     * Method that processes the player's turn
     * Sets a symbol on a pressed button
     * Marks the move on the board
     * Checks board for winnings of the corresponding player (after the fourth move)
     * If there are 9 moves, then a draw
     * Changes turn
     * */
    public void makeMove(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();

        pressedButton.setText(toString(player));
        pressedButton.setDisable(true);
        board[index(pressedButton)] = player;

        move++;
        if (move > 4) {
            if (wins(board, player)) {
                information.setText(turn(player) + " player win!");
                disableAll();
                return;
            }

            if (move == 9) {
                information.setText("Draw");
                disableAll();
                return;
            }
        }

        player = rival(player);
        information.setText(turn(player) + " player's turn");
    }

    private String turn(int player) {
        return player == 'X' ? "1st" : "2nd";
    }


    private String toString(int player) {
        return player == 'X' ? "X" : "O";
    }

    private void disableAll() {
        for (int i = 0; i < 9; i++) {
            if (cell(i).getText().equals("")) {
                cell(i).setDisable(true);
            }
        }
    }

    private int index(Button button) {
        switch (button.getId()) {
            case "a1": {
                return 0;
            }
            case "a2": {
                return 1;
            }
            case "a3": {
                return 2;
            }
            case "b1": {
                return 3;
            }
            case "b2": {
                return 4;
            }
            case "b3": {
                return 5;
            }
            case "c1": {
                return 6;
            }
            case "c2": {
                return 7;
            }
            case "c3": {
                return 8;
            }
            default: {
                return -1;
            }
        }
    }

    private Button cell(int index) {
        switch (index) {
            case 0: {
                return a1;
            }
            case 1: {
                return a2;
            }
            case 2: {
                return a3;
            }
            case 3: {
                return b1;
            }
            case 4: {
                return b2;
            }
            case 5: {
                return b3;
            }
            case 6: {
                return c1;
            }
            case 7: {
                return c2;
            }
            case 8: {
                return c3;
            }
            default: {
                return new Button();
            }
        }
    }

    private boolean wins(int[] board, int player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
               (board[3] == player && board[4] == player && board[5] == player) ||
               (board[6] == player && board[7] == player && board[8] == player) ||
               (board[0] == player && board[3] == player && board[6] == player) ||
               (board[1] == player && board[4] == player && board[7] == player) ||
               (board[2] == player && board[5] == player && board[8] == player) ||
               (board[0] == player && board[4] == player && board[8] == player) ||
               (board[2] == player && board[4] == player && board[6] == player);
    }

    private int rival(int player) {
        return player == 'X' ? 'O' : 'X';
    }
}
