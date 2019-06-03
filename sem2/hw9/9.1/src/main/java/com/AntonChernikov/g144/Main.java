package com.AntonChernikov.g144;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    String ip = "8189";

    @Override
    public void start(Stage primaryStage) {

        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(300);
        pane.setPrefHeight(300);

        Button createServerBtn = new Button("Create server");
        createServerBtn.setMinSize(20, 10);
        createServerBtn.setLayoutX(5);
        createServerBtn.setLayoutY(5);
        createServerBtn.setOnAction(event -> {
            AnchorPane waitPane = new AnchorPane();
            waitPane.setPrefWidth(200);
            waitPane.setPrefHeight(200);
            ProgressIndicator progressIndicator = new ProgressIndicator();
            progressIndicator.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
            waitPane.getChildren().add(progressIndicator);
            Scene scene = new Scene(waitPane, 200, 200);
            primaryStage.setTitle("Waiting");
            primaryStage.setScene(scene);
            primaryStage.show();

            try (ServerSocket serverSocket = new ServerSocket(8189)) {

                Socket socket = serverSocket.accept();

                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                Server server = new Server(socket.getInputStream(), socket.getOutputStream());

                server.initialize();
                primaryStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        pane.getChildren().add(createServerBtn);

        Button joinServerBtn = new Button("Join to server");
        joinServerBtn.setMinSize(20, 10);
        joinServerBtn.setLayoutX(5);
        joinServerBtn.setLayoutY(30);
        joinServerBtn.setOnAction(event -> {
            AnchorPane connectPane = new AnchorPane();
            connectPane.setPrefWidth(200);
            connectPane.setPrefHeight(200);
            TextField textField = new TextField();
            textField.setPrefSize(150, 20);
            textField.setLayoutX(5);
            textField.setLayoutY(5);
            connectPane.getChildren().add(textField);

            Stage stage = new Stage();

            Button connectBtn = new Button("Connect");
            connectBtn.setPrefSize(80, 10);
            connectBtn.setLayoutX(5);
            connectBtn.setLayoutY(30);
            connectBtn.setOnAction(event1 -> {
                ip = textField.getText();
                stage.close();
            });
            connectPane.getChildren().add(connectBtn);

            Scene scene = new Scene(connectPane, 200, 200);
            stage.setTitle("Waiting");
            stage.setScene(scene);
            stage.showAndWait();

            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(ip, 8189));

                Scanner scanner = new Scanner(System.in);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);


                Client client = new Client(socket.getInputStream(), socket.getOutputStream());
                client.initialize();
                primaryStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        pane.getChildren().add(joinServerBtn);

        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}