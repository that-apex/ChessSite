package net.thatapex.chesssite.chess.move;

import net.thatapex.chesssite.chess.pieces.ChessPieceType;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a pawn promotion in chess.
 */
public final class Promotion {

    private final ChessPieceType<?> targetPieceType;

    /**
     * Constructs a new promotion that results in the pawn promoting to the given piece type.
     *
     * @param targetPieceType piece type that the pawn promotes to.
     */
    public Promotion(final ChessPieceType<?> targetPieceType) {
        Validate.isTrue(targetPieceType.isValidPromotionTarget(), "cannot promote to " + targetPieceType.getName());

        this.targetPieceType = targetPieceType;
    }

    /**
     * Returns the piece type that the pawn promotes to.
     *
     * @return the piece type that the pawn promotes to.
     */
    public ChessPieceType<?> getTargetPieceType() {
        return this.targetPieceType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Promotion)) {
            return false;
        }

        final Promotion promotion = (Promotion) o;

        return new EqualsBuilder().append(this.targetPieceType, promotion.targetPieceType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.targetPieceType).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("targetPieceType", this.targetPieceType)
                .toString();
    }
}
