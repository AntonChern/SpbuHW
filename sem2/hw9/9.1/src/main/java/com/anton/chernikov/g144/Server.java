package com.anton.chernikov.g144;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Server {

    public Server() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("server.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("1st player");
            stage.setMinWidth(240);
            stage.setMinHeight(300);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}