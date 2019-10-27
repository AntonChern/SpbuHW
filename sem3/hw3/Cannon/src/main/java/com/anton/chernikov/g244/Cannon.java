package com.anton.chernikov.g244;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/** Class describing the behavior of the cannon */
public class Cannon extends Pane {

    private Pane barrel = new Pane();
    private Pane backWheel = new Pane();

    /** Constructor adding cannon elements */
    public Cannon() {
        setPrefSize(42, 42);

        ImageView wheelTexture = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("backWheel.png")));
        backWheel.getChildren().add(wheelTexture);
        backWheel.setTranslateX(- Const.backWheelShiftX);
        backWheel.setTranslateY(- Const.backWheelShiftY);
        getChildren().add(backWheel);

        ImageView carcassTexture = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("barrel.png")));
        barrel.getChildren().add(carcassTexture);
        barrel.setTranslateX(- Const.barrelShiftX);
        barrel.setTranslateY(- Const.barrelShiftY);
        barrel.setRotate(Const.barrelShift);
        getChildren().add(barrel);

        ImageView cannonTexture = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("frontWheel.png")));
        getChildren().add(cannonTexture);
    }

    /** Method moving the cannon according to the entered data */
    public void push(double delta, LandscapeGenerator generator) {
        double result = getTranslateX() + Const.cannonShiftX + Const.cannonExtraShiftX * getScaleX() + delta;
        setScaleX(Math.signum(delta));
        if (result >= 0 && result <= Const.windowWidth) {
            setTranslateX(result - Const.cannonShiftX - Const.cannonExtraShiftX * getScaleX());
            setTranslateY(generator.getCoordinate(result) - Const.cannonShiftY);
        }
    }

    /** Method changing barrel tilt */
    public void rotate(double delta) {
        double result = barrel.getRotate() + delta;
        if (result >= Const.minAngle && result <= Const.maxAngle) {
            barrel.setRotate(result);
        }
    }

    /** Method returning rotation */
    public double getRotation() {
        return barrel.getRotate();
    }
}
