package com.AntonChernikov.g144;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Slider slider;

    /** Method synchronizing the state of the slider with the state of the progress bar */
    public void setValue(MouseEvent mouseEvent) {
        progressBar.setProgress(slider.getValue() / 100);
    }
}
