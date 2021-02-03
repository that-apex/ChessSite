package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.pieces.types.Queen;
import org.junit.jupiter.api.Test;

public class TestQueen {

    @Test
    public void testLegalMoves() {
        final var queen = new Queen(ChessPieceColor.WHITE);

        // Middle of the board
        PieceTestHelper.testPotentialLegalMoves(
                queen, "e4",
                "e1", "e2", "e3", "e5", "e6", "e7", "e8",
                "a4", "b4", "c4", "d4", "f4", "g4", "h4",
                "b1", "c2", "d3", "f5", "g6", "h7",
                "a8", "b7", "c6", "d5", "f3", "g2", "h1"
        );

        // Corner of the board
        PieceTestHelper.testPotentialLegalMoves(
                queen, "h8",
                "h7", "h6", "h5", "h4", "h3", "h2", "h1",
                "a8", "b8", "c8", "d8", "e8", "f8", "g8",
                "a1", "b2", "c3", "d4", "e5", "f6", "g7"
        );
    }
}
