package com.anton.chernikov.g144;

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

        assertFalse(game.makeMove(1));
        assertFalse(game.makeMove(0));
        assertFalse(game.makeMove(3));
        assertFalse(game.makeMove(2));
        assertFalse(game.makeMove(5));
        assertFalse(game.makeMove(4));
        assertFalse(game.makeMove(6));
        assertFalse(game.makeMove(7));
        assertFalse(game.makeMove(8));
    }

    @Test
    void firstPlayerWin() {
//        X X X
//        . O O
//        O . X

        TicTacToe game = new TicTacToe();

        assertFalse(game.makeMove(0));
        assertFalse(game.makeMove(4));
        assertFalse(game.makeMove(8));
        assertFalse(game.makeMove(6));
        assertFalse(game.makeMove(2));
        assertFalse(game.makeMove(5));
        assertTrue(game.makeMove(1));
    }

    @Test
    void secondPlayerWin() {
//        O X O
//        . O X
//        X X O

        TicTacToe game = new TicTacToe();

        assertFalse(game.makeMove(6));
        assertFalse(game.makeMove(0));
        assertFalse(game.makeMove(7));
        assertFalse(game.makeMove(2));
        assertFalse(game.makeMove(1));
        assertFalse(game.makeMove(8));
        assertFalse(game.makeMove(5));
        assertTrue(game.makeMove(4));
    }

    @Test
    void winningWithoutEmptyCells() {
//        O O X
//        X O X
//        O X X

        TicTacToe game = new TicTacToe();

        assertFalse(game.makeMove(5));
        assertFalse(game.makeMove(0));
        assertFalse(game.makeMove(3));
        assertFalse(game.makeMove(4));
        assertFalse(game.makeMove(7));
        assertFalse(game.makeMove(1));
        assertFalse(game.makeMove(8));
        assertFalse(game.makeMove(6));
        assertTrue(game.makeMove(2));
    }

}