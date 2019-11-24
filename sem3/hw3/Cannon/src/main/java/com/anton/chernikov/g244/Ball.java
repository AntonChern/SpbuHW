package com.anton.chernikov.g244;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/** The Ball class describes the behavior of the ball */
public class Ball extends Pane {

    private double rotation;
    private double time;
    private int x;
    private int y;
    private int scale;

    /** Initializes all necessary parameters */
    public Ball(double rotation, int x, int y, int scale) {
        ImageView ballTexture = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("ball.png")));
        getChildren().add(ballTexture);
        setPrefSize(18, 18);
        this.rotation = rotation - Const.barrelShift;
        this.x = x;
        this.y = y;
        this.scale = scale;
        time = 0;
        setTranslateX(x);
        setTranslateY(y);
    }

    /** Moves the ball to the required coordinate */
    public void push() {
        time += 0.25;
        setTranslateX(x + scale * time * Const.shootSpeed * Math.cos(Math.toRadians(rotation)));
        setTranslateY(y + time * Const.shootSpeed * Math.sin(Math.toRadians(rotation)) + 5 * time * time);
    }
}
