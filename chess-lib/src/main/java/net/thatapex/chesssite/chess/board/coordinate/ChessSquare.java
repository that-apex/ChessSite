package net.thatapex.chesssite.chess.board.coordinate;

import net.thatapex.chesssite.chess.board.MutableChessBoardImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a single square on a {@link MutableChessBoardImpl}
 */
public final class ChessSquare {
    private final ChessFile file;
    private final ChessRank rank;

    /**
     * Constructs a new {@link ChessSquare} at the given position.
     *
     * @param file file at which the square is located at.
     * @param rank rank at which the square is located at.
     */
    public ChessSquare(final ChessFile file, final ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    /**
     * Returns the file at which this square is located at.
     *
     * @return the file at which this square is located at.
     */
    public ChessFile getFile() {
        return this.file;
    }

    /**
     * Returns the rank at which this square is located at.
     *
     * @return the rank at which this square is located at.
     */
    public ChessRank getRank() {
        return this.rank;
    }

    /**
     * Gets a unique representation of the current square in chess notation (for example: 'e4' or 'd8').
     * The resulting string is always lowercase.
     *
     * @return a chess notation square name.
     * @see <a href="https://en.wikipedia.org/wiki/Algebraic_notation_(chess)#Naming_the_squares>Chess Notation article on Wikipedia</a>
     */
    public String toChessNotation() {
        return String.valueOf(this.file.getFileLetter()) + this.rank.getRankNumber();
    }

    /**
     * Checks if a square is adjacent to another square.
     *
     * <p>
     * If squares are equal they are not considered adjacent.
     *
     * @param square   square to check the adjacency to.
     * @param diagonal if {@code true} then the squares will be considered adjacent if they are diagonal to each other.
     * @return {@code true} if the squares are adjacent, {@code false} if otherwise.
     */
    public boolean isAdjacent(final ChessSquare square, final boolean diagonal) {
        if (this.equals(square)) {
            return false;
        }

        final int deltaFile = Math.abs(square.file.getIndex() - this.file.getIndex());
        final int deltaRank = Math.abs(square.rank.getIndex() - this.rank.getIndex());

        if (deltaFile > 1 || deltaRank > 1) {
            return false;
        }

        if (diagonal) {
            return true;
        }

        return (deltaFile == 1 && deltaRank == 0) || (deltaFile == 0 && deltaRank == 1);
    }

    /**
     * Creates a new ChessSquare with file equal to {@code this.getFile() + fileDelta} and rank equal to {@code this.getRank() + rankDelta}
     *
     * @param fileDelta file delta
     * @param rankDelta rank delta
     * @return a new ChessSquare or an empty optional if either the new file or the new rank is invalid
     */
    public Optional<ChessSquare> getShifted(final int fileDelta, final int rankDelta) {
        return this.file.shift(fileDelta)
                .flatMap(file -> this.rank.shift(rankDelta).map(rank -> new ChessSquare(file, rank)));
    }

    /**
     * Creates a collection of squares, where each square is shifted (see {@link #getShifted(int, int)}) from the previous one by [fileDelta, rankDelta]
     *
     * <p>
     * The returned collection is equal to:
     * <ol>
     *     <li>{@code [this.getFile() + fileDelta * 1, this.getRank() + rankDelta * 1]}</li>
     *     <li>{@code [this.getFile() + fileDelta * 2, this.getRank() + rankDelta * 2]}</li>
     *     <li>{@code [this.getFile() + fileDelta * 3, this.getRank() + rankDelta * 3]}</li>
     *     <li>...</li>
     * </ol>
     * <p>
     * The starting point (point equal to {@code this}) is never a part of the returned collection.
     *
     * <p>
     * The algorithm stops and returns after either a file or a rank of the next square in the series is invalid.
     * <p>
     *
     * <p>
     * If both file delta and rank delta are 0 the function returns an empty collection.
     *
     * @param fileDelta file delta for each new point
     * @param rankDelta rank delta for each new point
     * @return series of squares, each shifted by [fileDelta, rankDelta] from the previous one
     */
    public Collection<ChessSquare> makeLine(final int fileDelta, final int rankDelta) {
        if (fileDelta == 0 && rankDelta == 0) {
            return Collections.emptyList();
        }

        final var             squares       = new ArrayList<ChessSquare>();
        Optional<ChessSquare> currentSquare = this.getShifted(fileDelta, rankDelta);

        while (currentSquare.isPresent()) {
            squares.add(currentSquare.get());

            currentSquare = currentSquare.get().getShifted(fileDelta, rankDelta);
        }

        return squares;
    }

    /**
     * Finds a {@link ChessPath} starting at {@code this} square and ending at targetSquare.
     * <p>
     * This function only looks for paths that are either diagonal, horizontal or vertical.
     * If there is no direct path that meets these requirements, a null optional will be returned.
     *
     * @param targetSquare square to end the path at
     * @return the found path, or an empty optional if no path was found
     */
    public Optional<ChessPath> findPathTo(final ChessSquare targetSquare) {
        final int deltaFile = targetSquare.getFile().getIndex() - this.getFile().getIndex();
        final int deltaRank = targetSquare.getRank().getIndex() - this.getRank().getIndex();

        if (deltaFile == 0 && deltaRank == 0) {
            return Optional.empty();
        }

        final int                deltaFileNormalized;
        final int                deltaRankNormalized;
        final ChessPath.PathType type;

        if (Math.abs(deltaFile) == Math.abs(deltaRank)) {
            // Diagonal move
            deltaFileNormalized = deltaFile >= 0 ? 1 : -1;
            deltaRankNormalized = deltaRank >= 0 ? 1 : -1;
            type = ChessPath.PathType.DIAGONAL;
        } else if (deltaFile == 0) {
            // Vertical move
            deltaFileNormalized = 0;
            deltaRankNormalized = deltaRank >= 0 ? 1 : -1;
            type = ChessPath.PathType.VERTICAL;
        } else if (deltaRank == 0) {
            // Horizontal move
            deltaFileNormalized = deltaFile >= 0 ? 1 : -1;
            deltaRankNormalized = 0;
            type = ChessPath.PathType.HORIZONTAL;
        } else {
            // Invalid move
            return Optional.empty();
        }

        final int               middlePointsCount = Math.max(Math.abs(deltaFile) - 1, Math.abs(deltaRank) - 1);
        final List<ChessSquare> middlePoints      = new ArrayList<>(middlePointsCount);

        for (int i = 1; i <= middlePointsCount; i++) {
            middlePoints.add(this.getShifted(deltaFileNormalized * i, deltaRankNormalized * i).orElseThrow());
        }

        return Optional.of(new ChessPath(this, middlePoints, targetSquare, type));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChessSquare)) {
            return false;
        }

        final var that = (ChessSquare) o;

        return new EqualsBuilder().append(this.file, that.file).append(this.rank, that.rank).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.file).append(this.rank).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("file", this.file)
                .append("rank", this.rank)
                .toString();
    }

    public static Optional<ChessSquare> fromChessNotation(final String notation) {
        final char[] charArray = notation.toCharArray();

        if (charArray.length != 2) {
            return Optional.empty();
        }

        final var file = ChessFile.fromFileLetter(charArray[0]);
        final var rank = ChessRank.fromRankNumber(charArray[1] - '0');

        return file.flatMap(f -> rank.map(r -> new ChessSquare(f, r)));
    }
}
