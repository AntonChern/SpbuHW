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
        primaryStage.setTitle("MainWindow");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
