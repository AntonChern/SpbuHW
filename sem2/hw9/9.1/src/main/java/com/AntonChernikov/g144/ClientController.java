package com.AntonChernikov.g144;

import javafx.event.ActionEvent;

/** Class describing controller of client */
public class ClientController extends Controller {

    private TicTacToe game = new TicTacToe();

    public void initialize() {
        super.initialize();
        game.changeTurn();
    }

    public void makeMove(ActionEvent actionEvent) {
        super.makeMove(actionEvent, "O");
    }

}