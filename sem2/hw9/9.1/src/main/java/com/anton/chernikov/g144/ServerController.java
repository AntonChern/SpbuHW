package com.anton.chernikov.g144;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ServerController {

    private InputStream in;
    private OutputStream out;
    private ServerSocket server;
    private TicTacToe game = new TicTacToe();
    private int move = 0;
    private Socket socket;

    private Map<Integer, Button> buttons;


    @FXML
    private Button a1;

    @FXML
    private Button a2;

    @FXML
    private Button a3;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button c1;

    @FXML
    private Button c2;

    @FXML
    private Button c3;

    @FXML
    private Label info;

    public void initialize() {
        buttons = Map.of(0, a1,
                1, a2,
                2, a3,
                3, b1,
                4, b2,
                5, b3,
                6, c1,
                7, c2,
                8, c3);

        try {
            server = new ServerSocket(Const.Port);
            socket = server.accept();

            in = socket.getInputStream();
            out = socket.getOutputStream();

            info.setText("Your turn");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** Method making move and checking for victory */
    public void makeMove(ActionEvent actionEvent) {
        move++;
        Button pressedButton = (Button) actionEvent.getSource();
        pressedButton.setText("X");
        pressedButton.setDisable(true);
        String name = pressedButton.getId();
        Integer index = 0;
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            if (entry.getValue().getId().equals(name)) {
                index = entry.getKey();
            }
        }
        try {
            out.write(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lockAll();

        if (game.makeMove(index)) {
            info.setText("You win!");
            close();
        } else {
            if (move == 9) {
                info.setText("Draw!");
                close();
            } else {
                info.setText("2nd player's turn");
                Expectant expectant = new Expectant();
                expectant.start();
            }
        }
    }

    /** Method unlocking all cells */
    private void unlockAll() {
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            if (entry.getValue().getText().equals("")) {
                entry.getValue().setDisable(false);
            }
        }
    }

    /** Method locking all cells */
    private void lockAll() {
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            entry.getValue().setDisable(true);
        }
    }

    /** Method closing all streams */
    private void close() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Auxiliary class waiting for the opponent’s move and performing this move */
    private class Expectant extends Thread {

        @Override
        public synchronized void run() {
            try {
                Integer index = in.read();
                move++;

                Platform.runLater(() -> {

                    Button serverButton = null;
                    synchronized (buttons) {
                        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
                            if (entry.getKey() == index) {
                                serverButton = entry.getValue();
                            }
                        }
                    }
                    serverButton.setText("O");

                    if (game.makeMove(index)) {
                        info.setText("You lose");
                        close();
                    } else {
                        unlockAll();
                        info.setText("Your turn");
                    }
                });
            } catch (IOException e) {
                System.out.println("Connection aborted");
            }
        }
    }
}