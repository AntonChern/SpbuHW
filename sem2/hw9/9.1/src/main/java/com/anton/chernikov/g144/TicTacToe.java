package com.anton.chernikov.g144;

public class TicTacToe {

    private int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private char player = 'X';

    private int lineLength = (int)Math.sqrt(board.length);

    /** Method making move */
    public boolean makeMove(int index) {
        board[index] = player;
        boolean result = wins();
        changeTurn();
        return result;
    }

    /** Method changing turn of player */
    private void changeTurn() {
        player = (player == 'X') ? 'O' : 'X';
    }

    /** Method checking board for winning */
    private boolean wins() {
        return winsVertically() || winsHorizontally() || winsDiagonally();
    }

    /** Auxiliary method checking one line */
    private boolean winsLine(int startIndex, int step) {
        boolean result = true;
        for (int i = 0; i < lineLength; i++) {
            result &= (board[startIndex + step * i] == player);
        }
        return result;
    }

    /** Method checking all vertical lines */
    private boolean winsVertically() {
        boolean result = false;
        for (int i = 0; i < lineLength; i++) {
            result |= winsLine(i, lineLength);
        }
        return result;
    }

    /** Method checking all horizontal lines */
    private boolean winsHorizontally() {
        boolean result = false;
        for (int i = 0; i < lineLength; i++) {
            result |= winsLine(i * lineLength, 1);
        }
        return result;
    }

    /** Method checking all diagonal lines */
    private boolean winsDiagonally() {
        return winsLine(0, lineLength + 1) || winsLine(lineLength - 1, lineLength - 1);
    }
}