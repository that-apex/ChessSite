package net.thatapex.chesssite.chess.board.setup;

import net.thatapex.chesssite.chess.board.BoardTestHelpers;
import net.thatapex.chesssite.chess.board.MutableChessBoard;
import net.thatapex.chesssite.chess.board.MutableChessBoardImpl;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.ArrayList;
import java.util.Collection;

import static com.spotify.hamcrest.optional.OptionalMatchers.emptyOptional;
import static com.spotify.hamcrest.optional.OptionalMatchers.optionalWithValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BoardSetupTester {
    private final MutableChessBoard       board            = new MutableChessBoardImpl();
    private final Collection<ChessSquare> validatesSquares = new ArrayList<>();

    public void setup(final BoardSetup setup) {
        setup.setup(this.board);
    }

    public void validate(final String squareNotation, final ChessPieceColor color, final ChessPieceType<?> type) {
        final var square = ChessSquare.fromChessNotation(squareNotation).orElseThrow();
        assertThat("invalid piece on " + squareNotation, this.board.getPiece(square, type, color), is(optionalWithValue()));

        this.validatesSquares.add(square);
    }

    public void validateRest() {
        for (final ChessSquare square : BoardTestHelpers.getEveryPossibleSquare()) {
            if (this.validatesSquares.contains(square)) {
                continue;
            }

            assertThat("invalid piece on " + square.toChessNotation(), this.board.getPiece(square), is(emptyOptional()));
        }
    }
}
