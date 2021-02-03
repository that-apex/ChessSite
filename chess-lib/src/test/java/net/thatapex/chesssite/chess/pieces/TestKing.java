package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.pieces.types.King;
import org.junit.jupiter.api.Test;

public class TestKing {

    @Test
    public void testLegalMoves() {
        final var whiteKing = new King(ChessPieceColor.WHITE);
        final var blackKing = new King(ChessPieceColor.BLACK);

        // Middle of the board
        PieceTestHelper.testPotentialLegalMoves(
                whiteKing, "e4",
                "d3", "d4", "d5", "e3", "e5", "f3", "f4", "f5"
        );

        // Corner of the board
        PieceTestHelper.testPotentialLegalMoves(
                whiteKing, "a1",
                "a2", "b1", "b2"
        );

        PieceTestHelper.testPotentialLegalMoves(
                whiteKing, "h8",
                "h7", "g8", "g7"
        );

        // With castling rights
        PieceTestHelper.testPotentialLegalMoves(
                whiteKing, "e1",
                "d2", "e2", "f2", "d1", "f1", "g1", "c1"
        );

        PieceTestHelper.testPotentialLegalMoves(
                blackKing, "e1",
                "d2", "e2", "f2", "d1", "f1"
        );

        PieceTestHelper.testPotentialLegalMoves(
                blackKing, "e8",
                "d7", "e7", "f7", "d8", "f8", "g8", "c8"
        );

        PieceTestHelper.testPotentialLegalMoves(
                whiteKing, "e8",
                "d7", "e7", "f7", "d8", "f8"
        );
    }
}
