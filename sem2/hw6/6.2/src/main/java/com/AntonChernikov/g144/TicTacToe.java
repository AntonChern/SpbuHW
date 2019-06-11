package com.AntonChernikov.g144;

/** Class describing game tic-tac-toe */
public class TicTacToe {

    public boolean isFirstPlayerWin = false;
    public boolean isSecondPlayerWin = false;
    public boolean isDraw = false;

    private int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Player player = new Player('X');
    private byte move = 0;

    private int lineLength = (int)Math.sqrt(board.length);

    /**
     * Method processing the player's move
     * Writes a move to the board
     * Checks board for winnings of the corresponding player (after the fourth move) and writes results to the fields
     * If there are 9 moves, then a draw
     * Changes turn
     * */
    public void makeMove(int index) {
        board[index] = player.symbol;

        move++;
        if (move > 4) {
            if (player.wins()) {
                switch (player.symbol) {
                    case 'X': {
                        isFirstPlayerWin = true;
                        break;
                    }
                    case 'O': {
                        isSecondPlayerWin = true;
                    }
                    default: {
                        break;
                    }
                }
                return;
            }

            if (move == 9) {
                isDraw = true;
                return;
            }
        }

        player.changeTurn();
    }

    /** Method returning symbol of current player */
    public String getPlayer() {
        return player.symbol == 'X' ? "X" : "O";
    }

    /** Class describing player functionality */
    private class Player {

        private int symbol;

        private Player(int symbol) {
            this.symbol = symbol;
        }

        /** Method changing turn of player */
        private void changeTurn() {
            symbol = (symbol == 'X') ? 'O' : 'X';
        }

        /** Method checking board for winning */
        private boolean wins() {
            return winsVertically() || winsHorizontally() || winsDiagonally();
        }

        /** Auxiliary method checking one line */
        private boolean winsLine(int startIndex, int step) {
            boolean result = true;
            for (int i = 0; i < lineLength; i++) {
                result &= (board[startIndex + step * i] == symbol);
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
            return winsLine(0, lineLength + 1) ||
                    winsLine(lineLength - 1, lineLength - 1);
        }
    }
}
