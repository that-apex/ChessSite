package net.thatapex.chesssite.chess.board;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents castling rights of a player.
 */
public class CastlingRights {
    private final boolean shortCastleRights;
    private final boolean longCastleRights;

    /**
     * Constructs new CastlingRights
     *
     * @param shortCastleRights whether the player can castle short
     * @param longCastleRights  whether the player can castle long
     */
    public CastlingRights(final boolean shortCastleRights, final boolean longCastleRights) {
        this.shortCastleRights = shortCastleRights;
        this.longCastleRights  = longCastleRights;
    }

    /**
     * Returns whether the player can castle short (castle kingside)
     *
     * @return whether the player can castle short
     */
    public boolean hasShortCastleRights() {
        return this.shortCastleRights;
    }

    /**
     * Returns whether the player can castle long (castle queenside)
     *
     * @return whether the player can castle long
     */
    public boolean hasLongCastleRights() {
        return this.longCastleRights;
    }

    /**
     * Creates a copy of this CastlingRights object with the same long castle rights but without the short castle rights.
     *
     * @return a copy of this CastlingRights without short castle rights
     */
    public CastlingRights withoutShortCastleRights() {
        return new CastlingRights(false, this.longCastleRights);
    }

    /**
     * Creates a copy of this CastlingRights object with the same short castle rights but without the long castle rights.
     *
     * @return a copy of this CastlingRights without long castle rights
     */
    public CastlingRights withoutLongCastleRights() {
        return new CastlingRights(this.shortCastleRights, false);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CastlingRights)) {
            return false;
        }

        final CastlingRights that = (CastlingRights) o;

        return new EqualsBuilder().append(this.shortCastleRights, that.shortCastleRights).append(this.longCastleRights, that.longCastleRights).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.shortCastleRights).append(this.longCastleRights).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("shortCastleRights", this.shortCastleRights)
                .append("longCastleRights", this.longCastleRights)
                .toString();
    }
}
