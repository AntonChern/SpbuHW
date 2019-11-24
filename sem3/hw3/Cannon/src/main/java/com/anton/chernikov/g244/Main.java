package com.anton.chernikov.g244;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main extends Application {

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane window = new Pane();
    private Cannon cannon;
    private ArrayList<Ball> balls = new ArrayList<>();
    private LandscapeGenerator generator = new LandscapeGenerator();

    /** Updates the information */
    public void update() {
        for (Iterator<Ball> iterator = balls.iterator(); iterator.hasNext(); ) {
            Ball ball = iterator.next();
            ball.push();
            if ((ball.getTranslateY() + Const.ballLandShiftY) >= generator.getCoordinate(ball.getTranslateX() + Const.ballLandShiftX)) {
                window.getChildren().removeAll(ball);
                iterator.remove();
            }
        }

        if (isPressed(KeyCode.RIGHT)) {
            cannon.push(Const.motionSpeed, generator);
        }
        if (isPressed(KeyCode.LEFT)) {
            cannon.push(- Const.motionSpeed, generator);
        }
        if (isPressed(KeyCode.UP)) {
            cannon.rotate(- Const.rotationSpeed);
        }
        if (isPressed(KeyCode.DOWN)) {
            cannon.rotate(Const.rotationSpeed);
        }
        if (isPressed(KeyCode.ENTER)) {
            keys.put(KeyCode.ENTER, false);
            Ball newBall = new Ball(cannon.getRotation(), (int)(cannon.getTranslateX() + Const.ballShiftX - Const.ballExtraShiftX * cannon.getScaleX()), (int)cannon.getTranslateY(), (int)cannon.getScaleX());
            window.getChildren().add(newBall);
            balls.add(newBall);
        }
    }

    /** Checks key for pressing */
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window.setPrefSize(Const.windowWidth, Const.windowHeight);
        ImageView skyTexture = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("sky.png")));
        window.getChildren().add(skyTexture);

        Canvas canvas = new Canvas(Const.windowWidth, Const.windowHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
        for (int i = 0; i < Const.windowWidth; i++) {
            gc.strokeLine(i + 1, generator.getCoordinate(i + 1), i + 1, Const.windowHeight);
        }

        window.getChildren().add(canvas);
        cannon = new Cannon();
        cannon.setTranslateX(Const.windowWidth / 2 - Const.cannonShiftX - Const.cannonExtraShiftX * cannon.getScaleX());
        cannon.setTranslateY(generator.getCoordinate(Const.windowWidth / 2) - Const.cannonShiftY);

        window.getChildren().add(cannon);
        Scene scene = new Scene(window);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        timer.start();

        primaryStage.setTitle("Cannon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
