package com.AntonChernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Map;

public class Controller {

    private TicTacToe game;
    private Map<Integer, Button> buttons;
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


    public void initialize() {
        game = new TicTacToe();
        buttons = Map.of(
                0, a1,
                1, b1,
                2, c1,
                3, a2,
                4, b2,
                5, c2,
                6, a3,
                7, b3,
                8, c3);
    }

    /**
     * Method that processes the player's move
     * Sets a symbol on a pressed button
     * Makes a move in class TicTacToe
     * Checks game for end
     * */
    public void makeMove(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();

        pressedButton.setText(game.getPlayer());
        pressedButton.setDisable(true);

        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            if (entry.getValue().equals(pressedButton)) {
                game.makeMove(entry.getKey());
                break;
            }
        }

        information.setText((game.getPlayer().equals("X") ? "1st" : "2nd") + " player's turn");

        if (game.isFirstPlayerWin) {
            information.setText("1st player win!");
            disableAll();
        }
        if (game.isSecondPlayerWin) {
            information.setText("2nd player win!");
            disableAll();
        }
        if (game.isDraw) {
            information.setText("Draw");
            disableAll();
        }
    }

    /** Method making inactive the remaining empty cells after the end of the game */
    private void disableAll() {
        for (int i = 0; i < 9; i++) {
            if (buttons.get(i).getText().equals("")) {
                buttons.get(i).setDisable(true);
            }
        }
    }
}