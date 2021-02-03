package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.pieces.types.Knight;
import org.junit.jupiter.api.Test;

public class TestKnight {

    @Test
    public void testLegalMoves() {
        final var knight = new Knight(ChessPieceColor.WHITE);

        // Middle of the board
        PieceTestHelper.testPotentialLegalMoves(
                knight, "e4",
                "d6", "f6", "c5", "g5", "c3", "g3", "d2", "f2"
        );

        // Side of the board
        PieceTestHelper.testPotentialLegalMoves(
                knight, "h8",
                "f7", "g6"
        );

        // Corner of the board
        PieceTestHelper.testPotentialLegalMoves(
                knight, "h6",
                "g8", "f7", "f5", "g4"
        );
    }

}
