package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PieceTestHelper {

    public static void testPotentialLegalMoves(final ChessPiece<?> piece, final String initialSquare, final String... expectedMovesNotations) {
        final Collection<ChessSquare> moves = new HashSet<>(expectedMovesNotations.length);

        piece.initializePotentialLegalMoves(ChessSquare.fromChessNotation(initialSquare).orElseThrow(), moves);

        for (final String expectedMoveNotation : expectedMovesNotations) {
            final var move = ChessSquare.fromChessNotation(expectedMoveNotation).orElseThrow();

            assertThat(
                    piece.getType() + " was expected to have a potential move from " + initialSquare + " to " + expectedMoveNotation,
                    moves,
                    hasItem(move)
            );

            moves.remove(move);
        }

        assertThat("Too many potential legal moves", moves, is(empty()));
    }
}
