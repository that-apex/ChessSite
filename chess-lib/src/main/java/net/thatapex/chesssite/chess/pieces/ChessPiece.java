package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.board.ChessBoard;
import net.thatapex.chesssite.chess.board.ChessSquare;
import net.thatapex.chesssite.chess.move.PotentialMove;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;

/**
 * Represents a piece on a chess board.
 *
 * @param <T> the runtime type of the implementor
 */
public abstract class ChessPiece<T extends ChessPiece<T>> {

    private final ChessPieceColor color;

    /**
     * Constructs a piece of a given color
     *
     * @param color color of the piece
     */
    protected ChessPiece(final ChessPieceColor color) {
        this.color = color;
    }

    /**
     * Returns the color of the piece.
     *
     * @return color of the piece
     */
    public ChessPieceColor getColor() {
        return this.color;
    }

    /**
     * Makes a copy of the piece but changes it color
     *
     * @param color color of the cloned piece
     * @return cloned piece
     */
    public T clonePiece(final ChessPieceColor color) {
        return this.getType().instantiatePiece(color);
    }

    /**
     * Makes an exact copy of the piece but changes it color
     *
     * @return cloned piece
     */
    public T clonePiece() {
        return this.clonePiece(this.color);
    }

    /**
     * Get the type of this piece
     *
     * @return type of this piece
     * @see ChessPieceType
     */
    public abstract ChessPieceType<T> getType();

    /**
     * Computes a collection of a moves that are potentially legal.
     * <p>
     * A move is considered potentially legal if a piece with the given type and color could make this move in at least one possible board state.
     * <p>
     * For example a white pawn move from e2 to e3, e2 to e4 or e2 to d3 (a capture) is potentially legal, but e2 to e5 is never legal
     *
     * @param position position of the piece
     * @param squares  output collection, potential moves will be added to this collection
     */
    public abstract void initializePotentialLegalMoves(ChessSquare position, Collection<ChessSquare> squares);

    /**
     * Checks if a piece move is legal in the current board state.
     * <p>
     * This method only checks if a move is legal for the piece, it does not consider whether the move will result in an invalid position (i.e. putting one's own king in check)
     *
     * @param boardState the board state
     * @param squareFrom the square that this piece is located at
     * @param move       move to be checked
     */
    public abstract void isMoveLegal(ChessBoard boardState, ChessSquare squareFrom, PotentialMove move);

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChessPiece)) {
            return false;
        }

        final ChessPiece<?> that = (ChessPiece<?>) o;

        return new EqualsBuilder().append(this.getType(), that.getType()).append(this.color, that.color).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.getType()).append(this.color).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", this.getType())
                .append("color", this.color)
                .toString();
    }
}
