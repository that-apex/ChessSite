package net.thatapex.chesssite.chess.board;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.spotify.hamcrest.optional.OptionalMatchers.*;

public class TestChessFile {

    @Test
    public void testFromIndex() {
        assertThat(ChessFile.fromIndex(0).orElseThrow().getIndex(), equalTo(0));
        assertThat(ChessFile.fromIndex(2).orElseThrow().getIndex(), equalTo(2));
        assertThat(ChessFile.fromIndex(7).orElseThrow().getIndex(), equalTo(7));

        assertThat(ChessFile.fromIndex(8), is(emptyOptional()));
        assertThat(ChessFile.fromIndex(9), is(emptyOptional()));
        assertThat(ChessFile.fromIndex(-1), is(emptyOptional()));
        assertThat(ChessFile.fromIndex(Integer.MAX_VALUE), is(emptyOptional()));
        assertThat(ChessFile.fromIndex(Integer.MIN_VALUE), is(emptyOptional()));
    }

    @Test
    public void testFromLetter() {
        assertThat(ChessFile.fromFileLetter('a').orElseThrow().getIndex(), equalTo(0));
        assertThat(ChessFile.fromFileLetter('c').orElseThrow().getIndex(), equalTo(2));
        assertThat(ChessFile.fromFileLetter('h').orElseThrow().getIndex(), equalTo(7));

        assertThat(ChessFile.fromFileLetter('i'), is(emptyOptional()));
        assertThat(ChessFile.fromFileLetter('j'), is(emptyOptional()));
        assertThat(ChessFile.fromFileLetter((char) 236), is(emptyOptional()));
        assertThat(ChessFile.fromFileLetter(Character.MAX_VALUE), is(emptyOptional()));
        assertThat(ChessFile.fromFileLetter(Character.MIN_VALUE), is(emptyOptional()));
    }

    @Test
    public void testGetFileLetter() {
        assertThat(ChessFile.fromIndex(0).orElseThrow().getFileLetter(), equalTo('a'));
        assertThat(ChessFile.fromIndex(2).orElseThrow().getFileLetter(), equalTo('c'));
        assertThat(ChessFile.fromIndex(7).orElseThrow().getFileLetter(), equalTo('h'));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testCopy(final int index) {
        assertThat(ChessFile.fromIndex(index).orElseThrow().copy().getIndex(), is(equalTo(index)));
    }

    private boolean isAdjacent(final int index1, final int index2) {
        return ChessFile.fromIndex(index1).orElseThrow().isAdjacent(ChessFile.fromIndex(index2).orElseThrow());
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

    private Optional<ChessFile> shift(final int index1, final int index2) {
        return ChessFile.fromIndex(index1).orElseThrow().shift(index2);
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
        EqualsVerifier.forClass(ChessFile.class).verify();
    }

    @Test
    public void testToString() {
        ToStringVerifier.forClass(ChessFile.class).withOnlyTheseFields("index").verify();
    }
}
