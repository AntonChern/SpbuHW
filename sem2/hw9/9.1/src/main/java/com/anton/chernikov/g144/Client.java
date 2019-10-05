package com.anton.chernikov.g144;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client {

    public Client() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("2nd player");
            stage.setMinWidth(240);
            stage.setMinHeight(300);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}