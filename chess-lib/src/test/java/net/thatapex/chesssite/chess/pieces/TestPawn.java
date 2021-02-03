package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.pieces.types.King;
import net.thatapex.chesssite.chess.pieces.types.Pawn;
import org.junit.jupiter.api.Test;

public class TestPawn {

    @Test
    public void testLegalMoves() {
        final var whitePawn = new Pawn(ChessPieceColor.WHITE);
        final var blackPawn = new Pawn(ChessPieceColor.BLACK);

        // Middle of the board
        PieceTestHelper.testPotentialLegalMoves(
                whitePawn, "e4",
                "e5", "d5", "f5"
        );

        PieceTestHelper.testPotentialLegalMoves(
                blackPawn, "e4",
                "e3", "d3", "f3"
        );

        // Starting rank
        PieceTestHelper.testPotentialLegalMoves(
                whitePawn, "e2",
                "e3", "d3", "f3", "e4"
        );

        PieceTestHelper.testPotentialLegalMoves(
                blackPawn, "e7",
                "e6", "d6", "f6", "e5"
        );
    }
}
