package net.thatapex.chesssite.chess.board.setup;

import net.thatapex.chesssite.chess.board.MutableChessBoard;

/**
 * Represents a way to setup a chess board.
 */
public interface BoardSetup {

    /**
     * Resets the board state and sets up new state
     *
     * @param board board to be set up
     */
    void setup(MutableChessBoard board);

}
