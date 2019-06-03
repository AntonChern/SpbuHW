package com.AntonChernikov.g144;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class Server {

    private InputStream in;
    private OutputStream out;

    private TicTacToe game;
    private Map<Integer, Button> buttons;
    private Label information;

    public Server(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void initialize() {

    }

    private void makeMove(ActionEvent actionEvent) {
        Button pressedButton = (Button)actionEvent.getSource();

        pressedButton.setText("X");
        pressedButton.setDisable(true);

        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            if (entry.getValue().equals(pressedButton)) {
                game.makeMove(entry.getKey());
                break;
            }
        }

        information.setText("2nd player's turn");

        if (game.isFirstPlayerWin) {
            information.setText("1st player win!");
        }
        if (game.isDraw) {
            information.setText("Draw");
        }
        disableAll();
        //поставить крестик в поле клиента
        //раздизейблить пустые ячейки в клиенте
    }

    private void disableAll() {
        for (int i = 0; i < 9; i++) {
            if (buttons.get(i).getText().equals("")) {
                buttons.get(i).setDisable(true);
            }
        }
    }
}
