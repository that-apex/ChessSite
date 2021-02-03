package net.thatapex.chesssite.chess.move;

import com.jparams.verifier.tostring.ToStringVerifier;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestPromotion {

    @Test
    public void testValidPromotion() {

        assertThat(new Promotion(ChessPieceType.BISHOP).getTargetPieceType(), is(ChessPieceType.BISHOP));
        assertThat(new Promotion(ChessPieceType.KNIGHT).getTargetPieceType(), is(ChessPieceType.KNIGHT));
        assertThat(new Promotion(ChessPieceType.ROOK).getTargetPieceType(), is(ChessPieceType.ROOK));
        assertThat(new Promotion(ChessPieceType.QUEEN).getTargetPieceType(), is(ChessPieceType.QUEEN));
    }

    @Test
    public void testInvalidPromotion() {
        assertThrows(IllegalArgumentException.class, () -> new Promotion(ChessPieceType.KING));
        assertThrows(IllegalArgumentException.class, () -> new Promotion(ChessPieceType.PAWN));
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Promotion.class).verify();
    }


    @Test
    public void testToString() {
        ToStringVerifier.forClass(Promotion.class).withOnlyTheseFields("targetPieceType").verify();
    }
}
