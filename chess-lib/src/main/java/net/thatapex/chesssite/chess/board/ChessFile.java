package net.thatapex.chesssite.chess.board;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

/**
 * Represents a file (a vertical line) on a chess board.
 *
 * @see ChessCoordinate
 */
public final class ChessFile extends ChessCoordinate<ChessFile> {

    /**
     * Minimum index value for a valid {@link ChessFile}
     */
    public static final int MINIMUM_INDEX = 0;

    /**
     * Maximum index value for a valid {@link ChessFile}
     */
    public static final int MAXIMUM_INDEX = 7;

    /**
     * Construct a new file.
     *
     * @param index the internal index for this file, should be in the range from {@link #MINIMUM_INDEX} to {@link #MAXIMUM_INDEX}
     */
    protected ChessFile(final int index) {
        super(index);
    }

    /**
     * Gets a letter representation of this file.
     * 
     * @return a letter representation of this file, a character from 'a' to 'h'.
     */
    public char getFileLetter() {
        return (char) ('a' + this.getIndex());
    }

    @Override
    protected Optional<ChessFile> validate() {
        if (getIndex() < MINIMUM_INDEX || getIndex() > MAXIMUM_INDEX) {
            return Optional.empty();
        }

        return Optional.of(this);
    }

    @Override
    protected ChessFile cloneCoordinate(final int index) {
        return new ChessFile(index);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChessFile)) {
            return false;
        }

        return ((ChessFile) o).getIndex() == this.getIndex();
    }

    @Override
    public int hashCode() {
        return 13 + 7 * this.getIndex();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("index", this.getIndex())
                .toString();
    }

    /**
     * Converts a numeric index to a {@link ChessFile} 
     * 
     * @param index index from {@link #MINIMUM_INDEX} to {@link #MAXIMUM_INDEX}
     *              
     * @return {@link Optional} containing a {@link ChessFile} with the given index if it's valid, empty optional otherwise.
     */
    public static Optional<ChessFile> fromIndex(final int index) {
        return new ChessFile(index).validate();
    }

    /**
     * Converts a file letter to a {@link ChessFile}
     *
     * @param letter letter, from 'a' to 'h'
     *
     * @return {@link Optional} containing a {@link ChessFile} with the given letter if it's valid, empty optional otherwise.
     */
    public static Optional<ChessFile> fromFileLetter(final char letter) {
        return new ChessFile(letter - 'a').validate();
    }
}
