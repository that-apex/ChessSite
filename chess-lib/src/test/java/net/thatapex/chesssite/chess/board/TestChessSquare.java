package net.thatapex.chesssite.chess.board;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.spotify.hamcrest.optional.OptionalMatchers.*;

public class TestChessSquare {

    private ChessSquare newSquareOrThrow(final int file, final int rank) {
        return new ChessSquare(ChessFile.fromIndex(file).orElseThrow(), ChessRank.fromIndex(rank).orElseThrow());
    }

    private void testCreateChessSquare(final int file, final int rank) {
        final var chessSquare = newSquareOrThrow(file, rank);

        assertThat(chessSquare.getFile().getIndex(), is(equalTo(file)));
        assertThat(chessSquare.getRank().getIndex(), is(equalTo(rank)));
    }

    @Test
    public void testCreateChessSquares() {
        testCreateChessSquare(0, 0);
        testCreateChessSquare(1, 7);
        testCreateChessSquare(7, 1);
        testCreateChessSquare(5, 5);
        testCreateChessSquare(7, 7);
        testCreateChessSquare(7, 0);
        testCreateChessSquare(0, 7);
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(ChessSquare.class).verify();
    }

    @Test
    public void testToString() {
        ToStringVerifier.forClass(ChessSquare.class).withOnlyTheseFields("rank", "file").verify();
    }

    @Test
    public void testToChessNotation() {
        assertThat(newSquareOrThrow(0, 0).toChessNotation(), equalTo("a1"));
        assertThat(newSquareOrThrow(4, 0).toChessNotation(), equalTo("e1"));
        assertThat(newSquareOrThrow(4, 1).toChessNotation(), equalTo("e2"));
        assertThat(newSquareOrThrow(3, 4).toChessNotation(), equalTo("d5"));
        assertThat(newSquareOrThrow(0, 0).toChessNotation(), equalTo("a1"));
        assertThat(newSquareOrThrow(7, 7).toChessNotation(), equalTo("h8"));
    }

    @Test
    public void testAdjacent() {
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(0, 0), false), is(false));
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(0, 2), true), is(false));
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(2, 0), false), is(false));

        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(1, 0), false), is(true));
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(0, 1), false), is(true));
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(1, 0), true), is(true));
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(0, 1), true), is(true));
        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(1, 1), true), is(true));

        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(2, 4), false), is(true));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(2, 2), false), is(true));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(1, 3), false), is(true));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(3, 3), false), is(true));

        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(1, 4), true), is(true));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(1, 2), true), is(true));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(3, 4), true), is(true));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(3, 2), true), is(true));

        assertThat(newSquareOrThrow(0, 0).isAdjacent(newSquareOrThrow(1, 1), false), is(false));

        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(1, 4), false), is(false));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(1, 2), false), is(false));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(3, 4), false), is(false));
        assertThat(newSquareOrThrow(2, 3).isAdjacent(newSquareOrThrow(3, 2), false), is(false));
    }

    @Test
    public void testMakeLine() {
        assertThat(newSquareOrThrow(0, 0).makeLine(0, 0), is(empty()));
        assertThat(newSquareOrThrow(0, 0).makeLine(0, -1), is(empty()));
        assertThat(newSquareOrThrow(0, 0).makeLine(-1, 0), is(empty()));
        assertThat(newSquareOrThrow(0, 0).makeLine(-1, -1), is(empty()));

        assertThat(newSquareOrThrow(0, 0).makeLine(1, 0), contains(
                newSquareOrThrow(1, 0),
                newSquareOrThrow(2, 0),
                newSquareOrThrow(3, 0),
                newSquareOrThrow(4, 0),
                newSquareOrThrow(5, 0),
                newSquareOrThrow(6, 0),
                newSquareOrThrow(7, 0)
        ));

        assertThat(newSquareOrThrow(0, 0).makeLine(1, 2), contains(
                newSquareOrThrow(1, 2),
                newSquareOrThrow(2, 4),
                newSquareOrThrow(3, 6)
        ));

        assertThat(newSquareOrThrow(3, 4).makeLine(-1, 2), contains(
                newSquareOrThrow(2, 6)
        ));
    }

    @Test
    public void testGetShifted() {
        assertThat(newSquareOrThrow(1, 0).getShifted(0, 0).orElseThrow(), is(equalTo(newSquareOrThrow(1, 0))));
        assertThat(newSquareOrThrow(1, 0).getShifted(0, 1).orElseThrow(), is(equalTo(newSquareOrThrow(1, 1))));
        assertThat(newSquareOrThrow(1, 0).getShifted(1, 0).orElseThrow(), is(equalTo(newSquareOrThrow(2, 0))));
        assertThat(newSquareOrThrow(1, 0).getShifted(1, 1).orElseThrow(), is(equalTo(newSquareOrThrow(2, 1))));
        assertThat(newSquareOrThrow(1, 0).getShifted(0, 2).orElseThrow(), is(equalTo(newSquareOrThrow(1, 2))));
        assertThat(newSquareOrThrow(1, 0).getShifted(3, 1).orElseThrow(), is(equalTo(newSquareOrThrow(4, 1))));

        assertThat(newSquareOrThrow(1, 0).getShifted(-1, 0).orElseThrow(), is(equalTo(newSquareOrThrow(0, 0))));
        assertThat(newSquareOrThrow(1, 0).getShifted(3, 5).orElseThrow(), is(equalTo(newSquareOrThrow(4, 5))));
        assertThat(newSquareOrThrow(6, 0).getShifted(1, 0).orElseThrow(), is(equalTo(newSquareOrThrow(7, 0))));

        assertThat(newSquareOrThrow(1, 0).getShifted(-1, -1), is(emptyOptional()));
        assertThat(newSquareOrThrow(7, 0).getShifted(1, -1), is(emptyOptional()));
    }

    public void testFromChessNotationSingle(final String notation, final int file, final int rank) {
        final var chessSquare = ChessSquare.fromChessNotation(notation).orElseThrow();

        assertThat(chessSquare.getFile().getIndex(), is(equalTo(file)));
        assertThat(chessSquare.getRank().getIndex(), is(equalTo(rank)));
    }

    @Test
    public void testFromChessNotation() {
        assertThat(ChessSquare.fromChessNotation("A1"), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation("a12"), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation("a1b2"), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation("test"), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation(""), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation("a"), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation("1"), is(emptyOptional()));
        assertThat(ChessSquare.fromChessNotation("1a"), is(emptyOptional()));

        testFromChessNotationSingle("a1", 0, 0);
        testFromChessNotationSingle("e1", 4, 0);
        testFromChessNotationSingle("e2", 4, 1);
        testFromChessNotationSingle("d5", 3, 4);
        testFromChessNotationSingle("a1", 0, 0);
        testFromChessNotationSingle("h8", 7, 7);
    }
}
