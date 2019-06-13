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
        pane.setPrefWidth(300);
        pane.setPrefHeight(150);

        Label label = new Label("Ip-address: ");
        label.setPrefSize(150, 40);
        label.setLayoutX(0);
        label.setLayoutY(25);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(label);

        TextField textField = new TextField();
        textField.setPrefSize(140, 40);
        textField.setLayoutX(150);
        textField.setLayoutY(25);
        pane.getChildren().add(textField);

        Button connectBtn = new Button("Connect");
        connectBtn.setPrefSize(200, 40);
        connectBtn.setLayoutX(50);
        connectBtn.setLayoutY(85);

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
        pane.setPrefWidth(250);
        pane.setPrefHeight(150);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressIndicator.setLayoutX(100);
        progressIndicator.setLayoutY(60);
        pane.getChildren().add(progressIndicator);

        Label label = new Label("Waiting to connect");
        label.setPrefSize(250, 30);
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

        pane.setPrefWidth(300);
        pane.setPrefHeight(200);

        Button createServerBtn = new Button("Create server");
        createServerBtn.setPrefSize(200, 40);
        createServerBtn.setLayoutX(50);
        createServerBtn.setLayoutY(40);
        createServerBtn.setOnAction(event -> {
            primaryStage.close();
            Stage stage = new Stage();
            serverConnection = new Server(port, stage);
            stage.setScene(new Scene(buildWindowForServer(), 250, 150));
            stage.setTitle("Waiting");
            stage.show();
        });
        pane.getChildren().add(createServerBtn);

        Button joinServerBtn = new Button("Join to server");
        joinServerBtn.setPrefSize(200, 40);
        joinServerBtn.setLayoutX(50);
        joinServerBtn.setLayoutY(120);
        joinServerBtn.setOnAction(event -> {
            Stage stage = new Stage();
            Scene scene = new Scene(buildWindowForClient(primaryStage), 300, 150);
            stage.setTitle("Joining");
            stage.setScene(scene);
            stage.show();
        });
        pane.getChildren().add(joinServerBtn);
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}