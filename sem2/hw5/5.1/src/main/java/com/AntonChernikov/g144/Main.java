package com.AntonChernikov.g144;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Task 5.1");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(316);
        primaryStage.setMinHeight(138);
        primaryStage.show();
    }
}
