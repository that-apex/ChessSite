package net.thatapex.chesssite.chess.board.coordinate;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents a rank (a horizontal line) on a chess board.
 *
 * @see ChessCoordinate
 */
public final class ChessRank extends ChessCoordinate<ChessRank> {
    
    /**
     * Minimum index value for a valid {@link ChessRank}
     */
    public static final int MINIMUM_INDEX = 0;
    
    /**
     * Maximum index value for a valid {@link ChessRank}
     */
    public static final int MAXIMUM_INDEX = 7;

    /**
     * Construct a new rank.
     *
     * @param index the internal index for this rank, should be in the range from {@link #MINIMUM_INDEX} to {@link #MAXIMUM_INDEX}
     */
    protected ChessRank(final int index) {
        super(index);
    }

    /**
     * Gets a number representation of this rank.
     *
     * @return a number representation of this rank, a number from "1" to "8".
     */
    public int getRankNumber() {
        return this.getIndex() + 1;
    }

    @Override
    protected Optional<ChessRank> validate() {
        if (getIndex() < MINIMUM_INDEX || getIndex() > MAXIMUM_INDEX) {
            return Optional.empty();
        }

        return Optional.of(this);
    }

    @Override
    protected ChessRank cloneCoordinate(final int index) {
        return new ChessRank(index);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChessRank)) {
            return false;
        }

        return ((ChessRank) o).getIndex() == this.getIndex();
    }

    @Override
    public int hashCode() {
        return 37 + 13 * this.getIndex();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("index", this.getIndex())
                .toString();
    }

    /**
     * Converts a numeric index to a {@link ChessRank}
     *
     * @param index index from {@link #MINIMUM_INDEX} to {@link #MAXIMUM_INDEX}
     *
     * @return {@link Optional} containing a {@link ChessRank} with the given index if it's valid, empty optional otherwise.
     */
    public static Optional<ChessRank> fromIndex(final int index) {
        return new ChessRank(index).validate();
    }

    /**
     * Converts a rank number to a {@link ChessRank}
     *
     * @param number number, from "1" to "8"
     *
     * @return {@link Optional} containing a {@link ChessRank} with the given number if it's valid, empty optional otherwise.
     */
    public static Optional<ChessRank> fromRankNumber(final int number) {
        return new ChessRank(number - 1).validate();
    }
}
