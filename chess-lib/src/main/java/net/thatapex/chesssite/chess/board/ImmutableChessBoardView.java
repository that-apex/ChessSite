package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A ChessBoard implementation that wraps around another ChessBoard object.
 * <p>
 * Can be used to wrap a {@link MutableChessBoard} to disallow modifying it.
 */
public class ImmutableChessBoardView implements ChessBoard {
    private final ChessBoard wrappedObject;

    /**
     * Constructs a new ImmutableChessBoardView that wraps around the given object
     *
     * @param wrappedObject ChessBoard to be wrapped
     */
    public ImmutableChessBoardView(final ChessBoard wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    @Override
    public int getBoardSize() {
        return this.wrappedObject.getBoardSize();
    }

    @Override
    public List<ChessPiece<?>> asPieceList() {
        return Collections.unmodifiableList(this.wrappedObject.asPieceList());
    }

    @Override
    public CastlingRights getCastlingRightsFor(final ChessPieceColor color) {
        return this.wrappedObject.getCastlingRightsFor(color);
    }

    @Override
    public Optional<ChessSquare> getEnPassantSquare() {
        return this.wrappedObject.getEnPassantSquare();
    }

    @Override
    public Optional<ChessPiece<?>> getPiece(final ChessSquare square) {
        return this.wrappedObject.getPiece(square);
    }

    @Override
    public <T extends ChessPiece<T>> Optional<ChessPiece<T>> getPiece(final ChessSquare square, final ChessPieceType<T> type, final ChessPieceColor color) {
        return this.wrappedObject.getPiece(square, type, color);
    }

    @Override
    public ChessBoard cloneAsImmutable() {
        return this;
    }

    @Override
    public MutableChessBoard cloneAsMutable() {
        return new MutableChessBoardImpl(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChessBoard)) {
            return false;
        }

        return o.equals(this.wrappedObject);
    }

    @Override
    public int hashCode() {
        return this.wrappedObject.hashCode();
    }
}
