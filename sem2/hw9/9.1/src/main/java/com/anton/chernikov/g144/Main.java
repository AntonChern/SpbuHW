package com.anton.chernikov.g144;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(200, 200);

        Button btnServer = new Button();
        btnServer.setText("Create server");
        btnServer.setAlignment(Pos.CENTER);
        btnServer.setPrefSize(100, 50);
        btnServer.setLayoutX(50);
        btnServer.setLayoutY(35);
        btnServer.setOnAction(event -> {
            primaryStage.close();
            new Server();
        });

        Button btnClient = new Button();
        btnClient.setText("Join to server");
        btnClient.setAlignment(Pos.CENTER);
        btnClient.setPrefSize(100, 50);
        btnClient.setLayoutX(50);
        btnClient.setLayoutY(115);
        btnClient.setOnAction(event -> {
            primaryStage.close();
            new Client();
        });

        pane.getChildren().add(btnClient);
        pane.getChildren().add(btnServer);

        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(pane, 200, 200));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}