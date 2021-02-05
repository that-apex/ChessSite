package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;

/**
 * A mutable counterpart for {@link ChessBoard}. Allows for modifying its state.
 */
public interface MutableChessBoard extends ChessBoard {

    /**
     * Clears all pieces from the board and completely resets the board state, resetting the en passant square and the castling rights
     */
    void resetState();

    /**
     * Sets the castling rights of a player with the given color.
     *
     * @param color  color of the player to check the castling rights for
     * @param rights castling rights to be set
     */
    void setCastlingRightsFor(ChessPieceColor color, CastlingRights rights);

    /**
     * Sets the en passant square on the board, replacing the previous one.
     *
     * @param square square to be set, null to remove the en passant square
     */
    void setEnPassantSquare(ChessSquare square);

    /**
     * Clears all pieces from the board.
     * <p>
     * This does not reset the board state other than the piece positions. To reset the entire state use {@link }
     */
    void clearPieces();

    /**
     * Puts a piece on a chess board, making sure only one instance of the piece is on the board.
     * <p>
     * The same piece cannot be on the board in two places, so if the piece is already on the board it will be moved to the new position.
     *
     * @param square square to put the piece on
     * @param piece  piece, null means that the piece will be removed from that square
     */
    void setPiece(ChessSquare square, ChessPiece<?> piece);

}
