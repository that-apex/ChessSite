package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.pieces.types.Bishop;
import org.junit.jupiter.api.Test;

public class TestBishop {

    @Test
    public void testLegalMoves() {
        final var bishop = new Bishop(ChessPieceColor.WHITE);

        // Middle of the board
        PieceTestHelper.testPotentialLegalMoves(
                bishop, "e4",
                "b1", "c2", "d3", "f5", "g6", "h7",
                "a8", "b7", "c6", "d5", "f3", "g2", "h1"
        );

        // Corner of the board
        PieceTestHelper.testPotentialLegalMoves(
                bishop, "h8",
                "a1", "b2", "c3", "d4", "e5", "f6", "g7"
        );
    }

}
