package net.thatapex.chesssite.chess.board.coordinate;

import com.jparams.verifier.tostring.ToStringVerifier;
import net.thatapex.chesssite.chess.board.coordinate.ChessRank;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.spotify.hamcrest.optional.OptionalMatchers.*;

public class TestChessRank {

    @Test
    public void testFromIndex() {
        assertThat(ChessRank.fromIndex(0).orElseThrow().getIndex(), is(equalTo(0)));
        assertThat(ChessRank.fromIndex(2).orElseThrow().getIndex(), is(equalTo(2)));
        assertThat(ChessRank.fromIndex(7).orElseThrow().getIndex(), is(equalTo(7)));

        assertThat(ChessRank.fromIndex(8), is(emptyOptional()));
        assertThat(ChessRank.fromIndex(9), is(emptyOptional()));
        assertThat(ChessRank.fromIndex(-1), is(emptyOptional()));
        assertThat(ChessRank.fromIndex(Integer.MAX_VALUE), is(emptyOptional()));
        assertThat(ChessRank.fromIndex(Integer.MIN_VALUE), is(emptyOptional()));
    }


    @Test
    public void testFromRankNumber() {
        assertThat(ChessRank.fromRankNumber(1).orElseThrow().getIndex(), is(equalTo(0)));
        assertThat(ChessRank.fromRankNumber(3).orElseThrow().getIndex(), is(equalTo(2)));
        assertThat(ChessRank.fromRankNumber(8).orElseThrow().getIndex(), is(equalTo(7)));

        assertThat(ChessRank.fromRankNumber(9), is(emptyOptional()));
        assertThat(ChessRank.fromRankNumber(10), is(emptyOptional()));
        assertThat(ChessRank.fromRankNumber(236), is(emptyOptional()));
        assertThat(ChessRank.fromRankNumber(Integer.MAX_VALUE), is(emptyOptional()));
        assertThat(ChessRank.fromRankNumber(Integer.MIN_VALUE), is(emptyOptional()));
    }

    @Test
    public void testGetRankNumber() {
        assertThat(ChessRank.fromIndex(0).orElseThrow().getRankNumber(), is(equalTo(1)));
        assertThat(ChessRank.fromIndex(2).orElseThrow().getRankNumber(), is(equalTo(3)));
        assertThat(ChessRank.fromIndex(7).orElseThrow().getRankNumber(), is(equalTo(8)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testCopy(final int index) {
        assertThat(ChessRank.fromIndex(index).orElseThrow().copy().getIndex(), is(equalTo(index)));
    }

    private boolean isAdjacent(final int index1, final int index2) {
        return ChessRank.fromIndex(index1).orElseThrow().isAdjacent(ChessRank.fromIndex(index2).orElseThrow());
    }

    @Test
    public void testIsAdjacent() {
        assertThat(isAdjacent(0, 1), is(true));
        assertThat(isAdjacent(1, 2), is(true));
        assertThat(isAdjacent(2, 1), is(true));
        assertThat(isAdjacent(1, 0), is(true));

        assertThat(isAdjacent(0, 2), is(false));
        assertThat(isAdjacent(0, 3), is(false));
        assertThat(isAdjacent(4, 6), is(false));
        assertThat(isAdjacent(6, 4), is(false));
        assertThat(isAdjacent(0, 7), is(false));
        assertThat(isAdjacent(7, 0), is(false));
    }

    private Optional<ChessRank> shift(final int index1, final int index2) {
        return ChessRank.fromIndex(index1).orElseThrow().shift(index2);
    }

    @Test
    public void testShift() {
        assertThat(shift(0, 1).orElseThrow().getIndex(), is(1));
        assertThat(shift(0, 0).orElseThrow().getIndex(), is(0));
        assertThat(shift(1, -1).orElseThrow().getIndex(), is(0));
        assertThat(shift(0, 5).orElseThrow().getIndex(), is(5));
        assertThat(shift(1, 5).orElseThrow().getIndex(), is(6));
        assertThat(shift(5, 1).orElseThrow().getIndex(), is(6));
        assertThat(shift(7, -1).orElseThrow().getIndex(), is(6));
        assertThat(shift(7, 0).orElseThrow().getIndex(), is(7));

        assertThat(shift(0, -1), is(emptyOptional()));
        assertThat(shift(0, 8), is(emptyOptional()));
        assertThat(shift(7, -9), is(emptyOptional()));
        assertThat(shift(6, 12), is(emptyOptional()));
        assertThat(shift(1, 7), is(emptyOptional()));
        assertThat(shift(7, 1), is(emptyOptional()));
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(ChessRank.class).verify();
    }

    @Test
    public void testToString() {
        ToStringVerifier.forClass(ChessRank.class).withOnlyTheseFields("index").verify();
    }
}
