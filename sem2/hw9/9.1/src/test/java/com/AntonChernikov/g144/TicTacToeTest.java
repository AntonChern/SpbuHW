package com.AntonChernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
//    0 1 2
//    3 4 5
//    6 7 8

    @Test
    void draw() {
//        O X O
//        X O X
//        X O X

        TicTacToe game = new TicTacToe();

        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(3);
        game.makeMove(2);
        game.makeMove(5);
        game.makeMove(4);
        game.makeMove(6);
        game.makeMove(7);
        game.makeMove(8);

        assertFalse(game.isFirstPlayerWin);
        assertFalse(game.isSecondPlayerWin);
        assertTrue(game.isDraw);
    }

    @Test
    void firstPlayerWin() {
//        X X X
//        . O O
//        O . X

        TicTacToe game = new TicTacToe();

        game.makeMove(0);
        game.makeMove(4);
        game.makeMove(8);
        game.makeMove(6);
        game.makeMove(2);
        game.makeMove(5);
        game.makeMove(1);

        assertTrue(game.isFirstPlayerWin);
        assertFalse(game.isSecondPlayerWin);
        assertFalse(game.isDraw);
    }

    @Test
    void secondPlayerWin() {
//        O X O
//        . O X
//        X X O

        TicTacToe game = new TicTacToe();

        game.makeMove(6);
        game.makeMove(0);
        game.makeMove(7);
        game.makeMove(2);
        game.makeMove(1);
        game.makeMove(8);
        game.makeMove(5);
        game.makeMove(4);

        assertFalse(game.isFirstPlayerWin);
        assertTrue(game.isSecondPlayerWin);
        assertFalse(game.isDraw);
    }

    @Test
    void inProcess() {
//        . . .
//        . X X
//        O . O

        TicTacToe game = new TicTacToe();

        game.makeMove(4);
        game.makeMove(8);
        game.makeMove(5);
        game.makeMove(6);

        assertFalse(game.isFirstPlayerWin);
        assertFalse(game.isSecondPlayerWin);
        assertFalse(game.isDraw);
    }

    @Test
    void winningWithoutEmptyCells() {
//        O O X
//        X O X
//        O X X

        TicTacToe game = new TicTacToe();

        game.makeMove(5);
        game.makeMove(0);
        game.makeMove(3);
        game.makeMove(4);
        game.makeMove(7);
        game.makeMove(1);
        game.makeMove(8);
        game.makeMove(6);
        game.makeMove(2);

        assertTrue(game.isFirstPlayerWin);
        assertFalse(game.isSecondPlayerWin);
        assertFalse(game.isDraw);
    }
}