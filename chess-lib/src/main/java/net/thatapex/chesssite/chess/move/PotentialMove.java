package net.thatapex.chesssite.chess.move;

import net.thatapex.chesssite.chess.board.ChessSquare;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a potential move that can be made on a chess board.
 */
public class PotentialMove {
    private final ChessSquare targetSquare;
    private final Promotion   promotion;

    public PotentialMove(final ChessSquare targetSquare, final Promotion promotion) {
        this.targetSquare = targetSquare;
        this.promotion    = promotion;
    }

    public ChessSquare getTargetSquare() {
        return this.targetSquare;
    }

    public Promotion getPromotion() {
        return this.promotion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("targetSquare", this.targetSquare)
                .append("piece", this.targetSquare)
                .append("promotion", this.promotion)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PotentialMove)) {
            return false;
        }

        final var that = (PotentialMove) o;

        return new EqualsBuilder().append(this.targetSquare, that.targetSquare).append(this.promotion, that.promotion).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.targetSquare).append(this.promotion).toHashCode();
    }
}
