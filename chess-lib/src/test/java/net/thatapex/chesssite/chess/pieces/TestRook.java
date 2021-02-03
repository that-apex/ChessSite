package net.thatapex.chesssite.chess.pieces;


import net.thatapex.chesssite.chess.pieces.types.Rook;
import org.junit.jupiter.api.Test;

public class TestRook {

    @Test
    public void testLegalMoves() {
        final var rook = new Rook(ChessPieceColor.WHITE);

        // Middle of the board
        PieceTestHelper.testPotentialLegalMoves(
                rook, "e4",
                "e1", "e2", "e3", "e5", "e6", "e7", "e8",
                "a4", "b4", "c4", "d4", "f4", "g4", "h4"
        );

        // Side of the board
        PieceTestHelper.testPotentialLegalMoves(
                rook, "a5",
                "a1", "a2", "a3", "a4", "a6", "a7", "a8",
                "b5", "c5", "d5", "e5", "f5", "g5", "h5"
        );

        // Corner of the board
        PieceTestHelper.testPotentialLegalMoves(
                rook, "h8",
                "h7", "h6", "h5", "h4", "h3", "h2", "h1",
                "a8", "b8", "c8", "d8", "e8", "f8", "g8"
        );
    }
}
