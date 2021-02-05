package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;
import net.thatapex.chesssite.chess.pieces.types.Knight;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.spotify.hamcrest.optional.OptionalMatchers.emptyOptional;
import static com.spotify.hamcrest.optional.OptionalMatchers.optionalWithValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

public class TestMutableChessBoard {

    @Test
    public void testDefaultState() {
        final ChessBoard board = new MutableChessBoardImpl();

        final CastlingRights whiteCastlingRights = board.getCastlingRightsFor(ChessPieceColor.WHITE);
        final CastlingRights blackCastlingRights = board.getCastlingRightsFor(ChessPieceColor.BLACK);

        assertThat("invalid board size", board.getBoardSize(), is(equalTo(64)));
        assertThat("invalid initial en passant square", board.getEnPassantSquare(), is(emptyOptional()));

        assertThat("invalid initial white's castling rights", whiteCastlingRights.hasShortCastleRights(), is(true));
        assertThat("invalid initial white's castling rights", whiteCastlingRights.hasLongCastleRights(), is(true));
        assertThat("invalid initial black's castling rights", blackCastlingRights.hasShortCastleRights(), is(true));
        assertThat("invalid initial black's castling rights", blackCastlingRights.hasLongCastleRights(), is(true));

        for (final ChessSquare square : BoardTestHelpers.getEveryPossibleSquare()) {
            assertThat("non-empty square on a new board", board.getPiece(square), is(emptyOptional()));
        }
    }

    @Test
    public void testCastlingRights() {
        final MutableChessBoard board = new MutableChessBoardImpl();

        final var whiteRightsBefore = board.getCastlingRightsFor(ChessPieceColor.WHITE);
        board.setCastlingRightsFor(ChessPieceColor.WHITE, whiteRightsBefore.withoutLongCastleRights());
        final var whiteRightsAfter = board.getCastlingRightsFor(ChessPieceColor.WHITE);

        assertThat("same castling rights instance", whiteRightsAfter, is(not(sameInstance(whiteRightsBefore))));

        assertThat("invalid white's castling rights before change", whiteRightsBefore.hasShortCastleRights(), is(true));
        assertThat("invalid white's castling rights before change", whiteRightsBefore.hasLongCastleRights(), is(true));
        assertThat("invalid white's castling rights after change", whiteRightsAfter.hasShortCastleRights(), is(true));
        assertThat("invalid white's castling rights after change", whiteRightsAfter.hasLongCastleRights(), is(false));

        final var blackRightsBefore = board.getCastlingRightsFor(ChessPieceColor.BLACK);
        board.setCastlingRightsFor(ChessPieceColor.BLACK, whiteRightsBefore.withoutShortCastleRights());
        final var blackRightsAfter = board.getCastlingRightsFor(ChessPieceColor.BLACK);

        assertThat("same castling rights instance", whiteRightsAfter, is(not(sameInstance(whiteRightsBefore))));

        assertThat("invalid black's castling rights before change", blackRightsBefore.hasShortCastleRights(), is(true));
        assertThat("invalid black's castling rights before change", blackRightsBefore.hasLongCastleRights(), is(true));
        assertThat("invalid black's castling rights after change", blackRightsAfter.hasShortCastleRights(), is(false));
        assertThat("invalid black's castling rights after change", blackRightsAfter.hasLongCastleRights(), is(true));

        final var whiteFinal = board.getCastlingRightsFor(ChessPieceColor.WHITE);
        assertThat("invalid white's castling rights after black's change", whiteFinal.hasShortCastleRights(), is(true));
        assertThat("invalid white's castling rights after black's change", whiteFinal.hasLongCastleRights(), is(false));
    }

    @Test
    public void testEnPassantSquare() {
        final MutableChessBoard board = new MutableChessBoardImpl();
        assertThat("en passant square on initial board", board.getEnPassantSquare(), is(emptyOptional()));

        final ChessSquare square1 = ChessSquare.fromChessNotation("c3").orElseThrow();
        board.setEnPassantSquare(square1);
        assertThat("cannot set en passant square", board.getEnPassantSquare(), is(optionalWithValue(equalTo(square1))));

        final ChessSquare square2 = ChessSquare.fromChessNotation("e5").orElseThrow();
        board.setEnPassantSquare(square2);
        assertThat("cannot set en passant square", board.getEnPassantSquare(), allOf(
                is(not(optionalWithValue(equalTo(square1)))),
                is(optionalWithValue(equalTo(square2)))
        ));

        board.setEnPassantSquare(null);
        assertThat("cannot reset en passant square", board.getEnPassantSquare(), is(emptyOptional()));
    }

    @Test
    public void testPieceSetting() {
        final MutableChessBoard board = new MutableChessBoardImpl();

        for (final ChessSquare square : BoardTestHelpers.getEveryPossibleSquare()) {
            assertThat("initial board is not empty", board.getPiece(square), is(emptyOptional()));
        }

        // setup random board
        final Map<ChessSquare, ChessPiece<?>> randomPieces = new HashMap<>();
        BoardTestHelpers.setupRandomBoard(board, randomPieces);

        // validate board
        BoardTestHelpers.testBoardStateMatch(board, null, randomPieces);
    }

    @Test
    public void testBoardGetFiltered() {
        final MutableChessBoard board       = new MutableChessBoardImpl();
        final ChessSquare       square      = ChessSquare.fromChessNotation("e2").orElseThrow();
        final Knight            whiteKnight = ChessPieceType.KNIGHT.instantiatePiece(ChessPieceColor.WHITE);

        assertThat("initial board is not empty", board.getPiece(square), is(emptyOptional()));

        board.setPiece(square, whiteKnight);
        assertThat("no valid piece present", board.getPiece(square), is(optionalWithValue(equalTo(whiteKnight))));
        assertThat("no white knight present", board.getPiece(square, ChessPieceType.KNIGHT, ChessPieceColor.WHITE), is(optionalWithValue(equalTo(whiteKnight))));

        assertThat("piece color not filtered", board.getPiece(square, ChessPieceType.KNIGHT, ChessPieceColor.BLACK), is(emptyOptional()));
        assertThat("piece type not filtered", board.getPiece(square, ChessPieceType.BISHOP, ChessPieceColor.WHITE), is(emptyOptional()));
        assertThat("piece type not filtered", board.getPiece(square, ChessPieceType.PAWN, ChessPieceColor.WHITE), is(emptyOptional()));
        assertThat("piece type not filtered", board.getPiece(square, ChessPieceType.QUEEN, ChessPieceColor.WHITE), is(emptyOptional()));
        assertThat("piece color and type not filtered", board.getPiece(square, ChessPieceType.BISHOP, ChessPieceColor.BLACK), is(emptyOptional()));
    }

    @Test
    public void testBoardDuplicates() {
        final MutableChessBoard board       = new MutableChessBoardImpl();
        final ChessSquare       square1     = ChessSquare.fromChessNotation("e2").orElseThrow();
        final ChessSquare       square2     = ChessSquare.fromChessNotation("e7").orElseThrow();
        final Knight            whiteKnight = ChessPieceType.KNIGHT.instantiatePiece(ChessPieceColor.WHITE);

        assertThat("initial board is not empty", board.getPiece(square1), is(emptyOptional()));

        board.setPiece(square1, whiteKnight);
        assertThat("no valid piece present", board.getPiece(square1), is(optionalWithValue(equalTo(whiteKnight))));

        board.setPiece(square2, whiteKnight);
        assertThat("no valid piece present", board.getPiece(square2), is(optionalWithValue(equalTo(whiteKnight))));
        assertThat("duplicate piece still present", board.getPiece(square1), is(emptyOptional()));
    }

    @Test
    public void testBoardClone() {
        final MutableChessBoard board = new MutableChessBoardImpl();

        // setup random board
        final Map<ChessSquare, ChessPiece<?>> randomPieces = new HashMap<>();
        BoardTestHelpers.setupRandomBoard(board, randomPieces);

        // clone it
        final MutableChessBoard clonedMutable   = board.cloneAsMutable();
        final ChessBoard        clonedImmutable = board.cloneAsImmutable();

        // check states
        assertThat("cloned mutable board is the same instance as the cloned board", board, is(not(sameInstance(clonedMutable))));
        assertThat("cloned immutable board is the same instance as the cloned board", board, is(not(sameInstance(clonedImmutable))));

        assertThat("en passant square do not match on mutable board", board.getEnPassantSquare(), is(equalTo(clonedMutable.getEnPassantSquare())));
        assertThat("white's castling rights do not match on mutable board", board.getCastlingRightsFor(ChessPieceColor.WHITE), is(equalTo(clonedMutable.getCastlingRightsFor(ChessPieceColor.WHITE))));
        assertThat("black's castling rights do not match on mutable board", board.getCastlingRightsFor(ChessPieceColor.BLACK), is(equalTo(clonedMutable.getCastlingRightsFor(ChessPieceColor.BLACK))));

        assertThat("en passant square do not match on immutable board", board.getEnPassantSquare(), is(equalTo(clonedImmutable.getEnPassantSquare())));
        assertThat("white's castling rights do not match on immutable board", board.getCastlingRightsFor(ChessPieceColor.WHITE), is(equalTo(clonedImmutable.getCastlingRightsFor(ChessPieceColor.WHITE))));
        assertThat("black's castling rights do not match on immutable board", board.getCastlingRightsFor(ChessPieceColor.BLACK), is(equalTo(clonedImmutable.getCastlingRightsFor(ChessPieceColor.BLACK))));

        // validate cloned pieces
        for (final ChessSquare square : BoardTestHelpers.getEveryPossibleSquare()) {
            final ChessPiece<?> expectedPiece = randomPieces.get(square);

            final Optional<ChessPiece<?>> actualMutable   = clonedMutable.getPiece(square);
            final Optional<ChessPiece<?>> actualImmutable = clonedImmutable.getPiece(square);

            if (expectedPiece == null) {
                assertThat("square " + square.toChessNotation() + " should be empty on mutable clone", actualMutable, is(emptyOptional()));
                assertThat("square " + square.toChessNotation() + " should be empty on immutable clone", actualImmutable, is(emptyOptional()));
                continue;
            }

            assertThat("square " + square.toChessNotation() + " has an invalid piece on mutable clone", actualMutable, is(optionalWithValue(equalTo(expectedPiece))));
            assertThat("square " + square.toChessNotation() + " has an invalid piece on immutable clone", actualImmutable, is(optionalWithValue(equalTo(expectedPiece))));
        }
    }

}
