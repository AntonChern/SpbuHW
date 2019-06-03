package com.AntonChernikov.g144;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Map;

/** Class describing controller */
public class Controller {

    private final Map<String, Integer> buttonNumbers = Map.of(
            "a1", 0,
            "a2", 1,
            "a3", 2,
            "b1", 3,
            "b2", 4,
            "b3", 5,
            "c1", 6,
            "c2", 7,
            "c3", 8
    );

    private List<Button> buttons;

    @FXML
    public Label information;

    @FXML
    public Button a1;
    @FXML
    public Button a2;
    @FXML
    public Button a3;
    @FXML
    public Button b1;
    @FXML
    public Button b2;
    @FXML
    public Button b3;
    @FXML
    public Button c1;
    @FXML
    public Button c2;
    @FXML
    public Button c3;

    public void initialize() {
        buttons = List.of(
                a1, a2, a3,
                b1, b2, b3,
                c1, c2, c3
        );
    }

    /** Method making inactive the remaining empty cells after move */
    private void lockAll() {
        for (Button btn : buttons) {
            btn.setDisable(true);
        }
    }

    /** Method that processes the player's move */
    public void makeMove(ActionEvent actionEvent, String symbol) {
        Button pressedButton = (Button)actionEvent.getSource();
        int index = buttonNumbers.get(pressedButton.getId());
        if (GameConnection.game.makeMove(index)) {
            pressedButton.setText(symbol);

            lockAll();
            GameConnection.sender.accept(index);
            GameConnection.gameOverChecker.accept(GameConnection.game);
        }
    }

}