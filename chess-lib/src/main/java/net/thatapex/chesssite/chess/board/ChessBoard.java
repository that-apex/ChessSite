package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.List;
import java.util.Optional;

/**
 * Represents a chess board.
 * <p>
 * A chess board contains all the pieces on the board and also its state, that is both players' castling rights and an 'en passant' square location.
 */
public interface ChessBoard {

    /**
     * Gets the size of the board, the number of all squares on a board.
     *
     * @return size of the board
     */
    int getBoardSize();

    /**
     * Converts the board to a list of pieces. The returned list must be of the same size as {@link #getBoardSize()} and may contain null elements for missing pieces.
     * <p>
     * The returned value may be immutable.
     *
     * @return a list of pieces on the board
     */
    List<ChessPiece<?>> asPieceList();

    /**
     * Gets the castling rights of a player with the given color.
     *
     * @param color color of the player to check the castling rights for
     * @return castling rights of the player
     */
    CastlingRights getCastlingRightsFor(ChessPieceColor color);

    /**
     * Gets the en passant square.
     *
     * @return en passant square, or empty optional if there is none
     */
    Optional<ChessSquare> getEnPassantSquare();

    /**
     * Gets a piece located on a given square.
     *
     * @param square square to get the piece from
     * @return optional containing the piece or null if none found
     */
    Optional<ChessPiece<?>> getPiece(ChessSquare square);

    /**
     * Gets a piece of the given type and the given color located on a given square.
     *
     * @param square square to get the piece from
     * @param type   type of the piece
     * @param color  color of the piece
     * @param <T>    type of the piece
     * @return optional containing the piece or null if none found or its type or color did not match
     */
    <T extends ChessPiece<T>> Optional<ChessPiece<T>> getPiece(ChessSquare square, ChessPieceType<T> type, ChessPieceColor color);

    /**
     * Clone this board as a new, immutable {@link ChessBoard}.
     * <p>
     * The returned object may be equal to {@code this} if this board is already immutable.
     *
     * @return the cloned board with the same exact state as this one.
     */
    ChessBoard cloneAsImmutable();

    /**
     * Clone this board as a new {@link MutableChessBoard}
     *
     * @return the cloned mutable board, with the same exact state as this one.
     */
    MutableChessBoard cloneAsMutable();
}
