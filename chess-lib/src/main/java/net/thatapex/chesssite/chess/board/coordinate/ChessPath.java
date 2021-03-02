package net.thatapex.chesssite.chess.board.coordinate;

import net.thatapex.chesssite.chess.board.ChessBoard;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Represents a path on a chess board, from one coordinate to another.
 */
public class ChessPath {
    private final ChessSquare       startingPoint;
    private final List<ChessSquare> middlePoints;
    private final ChessSquare       endingPoint;

    /**
     * Constructs a new ChessPath from starting at startingPoint, going through all middlePoints, in order, and ending in endingPoint
     *
     * @param startingPoint the starting point of the path
     * @param middlePoints  all middle points of the path, in order, may be empty if starting point is next to ending point
     * @param endingPoint   the ending point of the path
     */
    public ChessPath(final ChessSquare startingPoint, final List<ChessSquare> middlePoints, final ChessSquare endingPoint) {
        Validate.isTrue(!startingPoint.equals(endingPoint), "starting point and ending point cannot be the same");
        Validate.isTrue(!middlePoints.contains(startingPoint) && !middlePoints.contains(endingPoint),
                "middle points must not contain neither starting nor ending point");

        this.startingPoint = startingPoint;
        this.middlePoints  = middlePoints;
        this.endingPoint   = endingPoint;
    }

    /**
     * Returns the starting point of the path
     *
     * @return the starting point of the path
     */
    public ChessSquare getStartingPoint() {
        return this.startingPoint;
    }

    /**
     * Returns all middle points of the path, in order, may be empty if starting point is next to ending point
     *
     * @return all middle points of the path
     */
    public List<ChessSquare> getMiddlePoints() {
        return this.middlePoints;
    }

    /**
     * Returns the ending point of the path
     *
     * @return the ending point of the path
     */
    public ChessSquare getEndingPoint() {
        return this.endingPoint;
    }

    /**
     * Validates that all the middle points on this path are empty on the given chess board.
     *
     * @param board board that the path should be checked on
     * @return true if there are no pieces on the middle points, or there are no middle points in this path, false if otherwise
     */
    public boolean validatePathIsNotObstructed(final ChessBoard board) {
        for (final ChessSquare middlePoint : this.middlePoints) {
            if (board.getPiece(middlePoint).isPresent()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ChessPath)) {
            return false;
        }

        final ChessPath chessPath = (ChessPath) o;

        return new EqualsBuilder().append(this.startingPoint, chessPath.startingPoint).append(this.middlePoints, chessPath.middlePoints).append(this.endingPoint, chessPath.endingPoint).isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.startingPoint).append(this.middlePoints).append(this.endingPoint).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("startingPoint", this.startingPoint)
                .append("middlePoints", this.middlePoints)
                .append("endingPoint", this.endingPoint)
                .toString();
    }
}
