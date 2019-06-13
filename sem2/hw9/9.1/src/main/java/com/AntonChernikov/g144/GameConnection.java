package com.AntonChernikov.g144;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/** Class describing connection of game */
public abstract class GameConnection {

    public static TicTacToe game = new TicTacToe();

    public static Consumer<Integer> sender;
    public static Consumer<TicTacToe> gameOverChecker;

    private ConnectionThread connectionThread = new ConnectionThread();
    private List<Button> buttons = new LinkedList<>();
    private Label information;
    private Stage stage;

    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();

    public GameConnection(Stage stage) {
        sender = (data -> {
            try {
                send(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        gameOverChecker = (game -> {
            if (game.isFirstPlayerWin || game.isSecondPlayerWin || game.isDraw) {
                String message = "";
                if (game.isDraw) {
                    message = "Draw!";
                }
                if (game.isSecondPlayerWin) {
                    message = "2nd player win!";
                }
                if (game.isFirstPlayerWin) {
                    message = "1st player win!";
                }
                lockAll();
                information.setText(message);
            } else {
                if (information.getText().equals("1st player's turn")) {
                    information.setText("2nd player's turn");
                } else {
                    information.setText("1st player's turn");
                }
            }
        });

        this.stage = stage;
        connectionThread.setDaemon(true);
    }

    /** Method starting connecting with another player */
    public void startConnection() throws Exception {
        connectionThread.start();
    }

    /** Method making inactive the remaining empty cells after move */
    private void lockAll() {
        for (Button btn : buttons) {
            btn.setDisable(true);
        }
    }

    /** Method making active the remaining empty cells after move */
    private void unlockAll() {
        for (Button btn : buttons) {
            if (btn.getText().equals("")) {
                btn.setDisable(false);
            }
        }
    }

    /** Method sending index to opponent */
    private void send(Integer data) throws Exception {
        connectionThread.out.writeObject(data);
    }

    /** Method closing connection */
    public void closeConnection() throws Exception {
        connectionThread.socket.close();
    }

    /** Method building interface of game */
    private void buildWindow() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(isServer() ? "serverGame.fxml" : "clientGame.fxml"));
            for (Node node : root.getChildrenUnmodifiable()) {
                if (node instanceof Button) {
                    buttons.add((Button)node);
                }
                if (node instanceof Label) {
                    information = (Label)node;
                }
            }
            buttons.sort(Comparator.comparing(Node::getId));
            Scene scene = new Scene(root);
            stage.setTitle("Tic-tac-toe");
            stage.setMinWidth(246);
            stage.setMinHeight(318);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!isServer()) {
            lockAll();
        }
    }

    /** Class describing thread for connection with opponent */
    private class ConnectionThread extends Thread {

        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                 Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                Platform.runLater(GameConnection.this::buildWindow);

                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);

                while (true) {
                    Integer index = (Integer)in.readObject();
                    Platform.runLater(() -> {
                        game.makeMove(index);
                        buttons.get(index).setText(isServer() ? "O" : "X");
                        unlockAll();
                        gameOverChecker.accept(game);
                    });
                }

            } catch (Exception exc) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Connection interrupted", ButtonType.CLOSE);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                });
            }
        }

    }
}