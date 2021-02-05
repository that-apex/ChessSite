package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessFile;
import net.thatapex.chesssite.chess.board.coordinate.ChessRank;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.spotify.hamcrest.optional.OptionalMatchers.emptyOptional;
import static com.spotify.hamcrest.optional.OptionalMatchers.optionalWithValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BoardTestHelpers {
    private static final Collection<ChessSquare> everyPossibleSquare = new ArrayList<>(64);

    static {
        for (int fileIndex = ChessFile.MINIMUM_INDEX; fileIndex <= ChessFile.MAXIMUM_INDEX; fileIndex++) {
            final var file = ChessFile.fromIndex(fileIndex).orElseThrow();

            for (int rankIndex = ChessRank.MINIMUM_INDEX; rankIndex <= ChessRank.MAXIMUM_INDEX; rankIndex++) {
                final var rank = ChessRank.fromIndex(rankIndex).orElseThrow();

                everyPossibleSquare.add(new ChessSquare(file, rank));
            }
        }
    }

    public static Collection<ChessSquare> getEveryPossibleSquare() {
        return everyPossibleSquare;
    }

    public static void setupRandomBoard(final MutableChessBoard board, final Map<ChessSquare, ChessPiece<?>> randomPieces) {
        final Random random = new Random();

        for (final ChessSquare square : getEveryPossibleSquare()) {
            if (!random.nextBoolean()) {
                randomPieces.put(square, null);
                continue;
            }

            final ChessPieceType<?> type  = ChessPieceType.values().get(random.nextInt(ChessPieceType.values().size()));
            final ChessPieceColor   color = random.nextBoolean() ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;

            final ChessPiece<?> piece = type.instantiatePiece(color);

            randomPieces.put(square, piece);
            board.setPiece(square, piece);
        }
    }

    public static void testBoardStateMatch(final ChessBoard board, final ChessBoard match, final Map<ChessSquare, ChessPiece<?>> state) {
        if (match != null) {
            assertThat("en passant square do not match", match.getEnPassantSquare(), is(equalTo(match.getEnPassantSquare())));
            assertThat("white's castling rights do not match", match.getCastlingRightsFor(ChessPieceColor.WHITE), is(equalTo(match.getCastlingRightsFor(ChessPieceColor.WHITE))));
            assertThat("black's castling rights do not match", match.getCastlingRightsFor(ChessPieceColor.BLACK), is(equalTo(match.getCastlingRightsFor(ChessPieceColor.BLACK))));
        }

        for (final ChessSquare square : BoardTestHelpers.getEveryPossibleSquare()) {
            final ChessPiece<?>           expectedPiece = state.get(square);
            final Optional<ChessPiece<?>> actual        = board.getPiece(square);

            if (expectedPiece == null) {
                assertThat("square " + square.toChessNotation() + " should be empty", actual, is(emptyOptional()));
                continue;
            }

            assertThat("square " + square.toChessNotation() + " has an invalid piece", actual, is(optionalWithValue(equalTo(expectedPiece))));
        }
    }
}
