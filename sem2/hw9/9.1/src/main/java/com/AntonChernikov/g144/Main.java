package com.AntonChernikov.g144;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    private GameConnection serverConnection;
    private GameConnection clientConnection;
    private String ip;
    private int port = 8189;

    /** Method building interface of game for client */
    private Parent buildWindowForClient(Stage primaryStage) {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(200);
        pane.setPrefHeight(80);

        Label label = new Label("Ip-address: ");
        label.setPrefSize(70, 20);
        label.setLayoutX(10);
        label.setLayoutY(10);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(label);

        TextField textField = new TextField();
        textField.setPrefSize(100, 20);
        textField.setLayoutX(85);
        textField.setLayoutY(10);
        pane.getChildren().add(textField);

        Button connectBtn = new Button("Connect");
        connectBtn.setPrefSize(100, 20);
        connectBtn.setLayoutX(50);
        connectBtn.setLayoutY(50);

        connectBtn.setOnAction(event -> {
            ip = textField.getText();
            clientConnection = new Client(ip, port, (Stage)connectBtn.getScene().getWindow());
            try {
                clientConnection.startConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.close();
        });
        pane.getChildren().add(connectBtn);

        return pane;
    }

    /** Method building interface of game for server */
    private Parent buildWindowForServer() {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(200);
        pane.setPrefHeight(100);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressIndicator.setLayoutX(75);
        progressIndicator.setLayoutY(35);
        pane.getChildren().add(progressIndicator);

        Label label = new Label("Waiting to connect");
        label.setPrefSize(200, 20);
        label.setLayoutX(0);
        label.setLayoutY(5);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);

        try {
            serverConnection.startConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pane;
    }

    @Override
    public void start(Stage primaryStage) {

        AnchorPane pane = new AnchorPane();

        pane.setPrefWidth(170);
        pane.setPrefHeight(100);

        Button createServerBtn = new Button("Create server");
        createServerBtn.setPrefSize(100, 20);
        createServerBtn.setLayoutX(35);
        createServerBtn.setLayoutY(25);
        createServerBtn.setOnAction(event -> {
            primaryStage.close();
            Stage stage = new Stage();
            serverConnection = new Server(port, stage);
            stage.setScene(new Scene(buildWindowForServer(), 200, 100));
            stage.setTitle("Waiting");
            stage.show();
        });
        pane.getChildren().add(createServerBtn);

        Button joinServerBtn = new Button("Join to server");
        joinServerBtn.setPrefSize(100, 20);
        joinServerBtn.setLayoutX(35);
        joinServerBtn.setLayoutY(55);
        joinServerBtn.setOnAction(event -> {
            Stage stage = new Stage();
            Scene scene = new Scene(buildWindowForClient(primaryStage), 200, 80);
            stage.setTitle("Joining");
            stage.setScene(scene);
            stage.show();
        });
        pane.getChildren().add(joinServerBtn);
        Scene scene = new Scene(pane, 170, 100);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}