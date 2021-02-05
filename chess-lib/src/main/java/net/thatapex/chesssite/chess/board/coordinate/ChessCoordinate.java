package net.thatapex.chesssite.chess.board.coordinate;

import java.util.Optional;

/**
 * Represents a single coordinate on a chess board.
 *
 * @param <ThisT> the runtime type of the implementor
 * @see ChessRank
 * @see ChessFile
 */
public abstract class ChessCoordinate<ThisT extends ChessCoordinate<ThisT>> {

    private final int index;

    /**
     * Construct a new coordinate.
     *
     * @param index the internal index for this coordinate, starting from 0
     */
    protected ChessCoordinate(final int index) {
        this.index = index;
    }

    /**
     * Clones this coordinate, replacing the index with a new value.
     *
     * @param index the internal index for this coordinate
     * @return a new coordinate of the type T
     * @see ChessCoordinate#ChessCoordinate(int)
     */
    protected abstract ThisT cloneCoordinate(int index);

    /**
     * Validates if this coordinate is an actual coordinate that is possible to be represented on a chess board.
     *
     * @return {@link Optional} containing {@code this} if the coordinate is valid, empty optional otherwise.
     */
    protected abstract Optional<ThisT> validate();

    /**
     * Returns the internal index for this coordinate
     *
     * @return the internal index for this coordinate
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Makes an exact copy of this coordinate.
     *
     * @return an exact copy of this coordinate.
     */
    public ThisT copy() {
        return cloneCoordinate(this.index);
    }

    /**
     * Shifts this coordinate's index by {@code shiftValue}.
     *
     * @param shiftValue by how many squares should the coordinate be shifted.
     * @return An {@link Optional} containing the shifted coordinate if it is valid, empty optional otherwise.
     */
    public Optional<ThisT> shift(int shiftValue) {
        return cloneCoordinate(this.index + shiftValue).validate();
    }

    /**
     * Checks if this coordinate is adjacent to an other coordinate.
     *
     * @param other other coordinate
     * @return {@code true} if the coordinates are adjacent, {@code false} if otherwise
     */
    public boolean isAdjacent(final ThisT other) {
        return other.getIndex() == this.getIndex() - 1 || other.getIndex() == this.getIndex() + 1;
    }

}
