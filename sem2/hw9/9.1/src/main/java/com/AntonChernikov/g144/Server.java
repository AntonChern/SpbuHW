package com.AntonChernikov.g144;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private Connection connection = new Connection();

    private TicTacToe game;
    private Map<Integer, Button> buttons;
    private Label information;

    public Server() {
        game = new TicTacToe();
        buttons = new HashMap<Integer, Button>();
    }

    public void initialize() {
        Stage stage = new Stage();
        stage.setResizable(false);

        int buttonSize = 70;
        int indent = 10;

        AnchorPane pane = new AnchorPane();

        GridPane grid = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button newButton = new Button();
                newButton.setText("");
                newButton.setFont(Font.font(24));
                newButton.setAlignment(Pos.CENTER);
                newButton.setPrefSize(buttonSize, buttonSize);
                newButton.setMnemonicParsing(false);
                newButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                newButton.setOnAction(event -> makeMove(newButton));

                buttons.put(i * 3 + j, newButton);
                grid.add(newButton, i , j);
            }
        }
        grid.setLayoutX(indent);
        grid.setLayoutY(indent * 6);

        pane.getChildren().add(grid);


        information = new Label();
        information.setText("Your turn");
        information.setPrefSize(buttonSize * 3, indent * 4);
        information.setAlignment(Pos.CENTER);
        information.setFont(Font.font(24));
        information.setLayoutX(indent);
        information.setLayoutY(indent);

        pane.getChildren().add(information);
        Scene scene = new Scene(pane, buttonSize * 3 + indent * 2, buttonSize * 3 + indent * 7);
        stage.setScene(scene);
        stage.setTitle("Tic-tac-toe");
        stage.show();
        waitMove();
    }

    public void waitMove() {
        connection.start();
    }

    private void makeMove(Button pressedButton) {
        pressedButton.setText("X");
        pressedButton.setDisable(true);

        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            if (entry.getValue().equals(pressedButton)) {
                game.makeMove(entry.getKey(), 'X');
                try {
                    send(entry.getKey());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        information.setText("2nd player's turn");

        if (game.isFirstPlayerWin) {
            information.setText("You win!");
            lockAll();
            return;
        }
        if (game.isDraw) {
            information.setText("Draw");
            return;
        }
        lockAll();
    }

    private void unlockAll() {
        for (int i = 0; i < 9; i++) {
            if (buttons.get(i).getText().equals("")) {
                buttons.get(i).setDisable(false);
            }
        }
    }

    private void lockAll() {
        for (int i = 0; i < 9; i++) {
            if (buttons.get(i).getText().equals("")) {
                buttons.get(i).setDisable(true);
            }
        }
    }

    private void send(int index) throws IOException {
        connection.out.write(index);
        connection.out.flush();
    }


    private class Connection extends Thread {

        private OutputStream out;

        @Override
        public void run() {
            try(ServerSocket serverSocket = new ServerSocket(8189)) {
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                this.out = out;


                Platform.runLater(() -> {
                    initialize();
                });

                while (true) {
                    int index = in.read();
                    information.setText("Your turn");
                    game.setMove(index, 'O');
                    buttons.get(index).setText("O");
                    if (game.isSecondPlayerWin) {
                        information.setText("2nd player win!");
                    } else {
                        unlockAll();
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
