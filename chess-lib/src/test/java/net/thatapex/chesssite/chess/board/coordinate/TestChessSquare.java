package net.thatapex.chesssite.chess.board.coordinate;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static com.spotify.hamcrest.optional.OptionalMatchers.emptyOptional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
    public void testFindPathTo() {
        final ChessSquare originPoint = newSquareOrThrow(4, 4);

        assertThat(originPoint.findPathTo(originPoint), is(emptyOptional()));

        assertThat(originPoint.findPathTo(newSquareOrThrow(5, 6)), is(emptyOptional()));
        assertThat(originPoint.findPathTo(newSquareOrThrow(7, 6)), is(emptyOptional()));
        assertThat(originPoint.findPathTo(newSquareOrThrow(2, 0)), is(emptyOptional()));
        assertThat(originPoint.findPathTo(newSquareOrThrow(3, 2)), is(emptyOptional()));
        assertThat(originPoint.findPathTo(newSquareOrThrow(7, 3)), is(emptyOptional()));

        final ChessPath diagonalPath1   = originPoint.findPathTo(newSquareOrThrow(5, 5)).orElseThrow();
        final ChessPath diagonalPath2   = originPoint.findPathTo(newSquareOrThrow(2, 2)).orElseThrow();
        final ChessPath diagonalPath3   = originPoint.findPathTo(newSquareOrThrow(1, 7)).orElseThrow();
        final ChessPath diagonalPath4   = originPoint.findPathTo(newSquareOrThrow(7, 1)).orElseThrow();
        final ChessPath verticalPath1   = originPoint.findPathTo(newSquareOrThrow(4, 1)).orElseThrow();
        final ChessPath verticalPath2   = originPoint.findPathTo(newSquareOrThrow(4, 5)).orElseThrow();
        final ChessPath horizontalPath1 = originPoint.findPathTo(newSquareOrThrow(7, 4)).orElseThrow();
        final ChessPath horizontalPath2 = originPoint.findPathTo(newSquareOrThrow(3, 4)).orElseThrow();

        assertThat(diagonalPath1.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(diagonalPath2.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(diagonalPath3.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(diagonalPath4.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(verticalPath1.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(verticalPath2.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(horizontalPath1.getStartingPoint(), is(equalTo(originPoint)));
        assertThat(horizontalPath2.getStartingPoint(), is(equalTo(originPoint)));

        assertThat(diagonalPath1.getMiddlePoints(), is(empty()));
        assertThat(diagonalPath2.getMiddlePoints(), contains(newSquareOrThrow(3, 3)));
        assertThat(diagonalPath3.getMiddlePoints(), contains(newSquareOrThrow(3, 5), newSquareOrThrow(2, 6)));
        assertThat(diagonalPath4.getMiddlePoints(), contains(newSquareOrThrow(5, 3), newSquareOrThrow(6, 2)));
        assertThat(verticalPath1.getMiddlePoints(), contains(newSquareOrThrow(4, 3), newSquareOrThrow(4, 2)));
        assertThat(verticalPath2.getMiddlePoints(), is(empty()));
        assertThat(horizontalPath1.getMiddlePoints(), contains(newSquareOrThrow(5, 4), newSquareOrThrow(6, 4)));
        assertThat(horizontalPath2.getMiddlePoints(), is(empty()));

        assertThat(diagonalPath1.getEndingPoint(), is(equalTo(newSquareOrThrow(5, 5))));
        assertThat(diagonalPath2.getEndingPoint(), is(equalTo(newSquareOrThrow(2, 2))));
        assertThat(diagonalPath3.getEndingPoint(), is(equalTo(newSquareOrThrow(1, 7))));
        assertThat(diagonalPath4.getEndingPoint(), is(equalTo(newSquareOrThrow(7, 1))));
        assertThat(verticalPath1.getEndingPoint(), is(equalTo(newSquareOrThrow(4, 1))));
        assertThat(verticalPath2.getEndingPoint(), is(equalTo(newSquareOrThrow(4, 5))));
        assertThat(horizontalPath1.getEndingPoint(), is(equalTo(newSquareOrThrow(7, 4))));
        assertThat(horizontalPath2.getEndingPoint(), is(equalTo(newSquareOrThrow(3, 4))));

        assertThat(diagonalPath1.getPathType(), is(equalTo(ChessPath.PathType.DIAGONAL)));
        assertThat(diagonalPath2.getPathType(), is(equalTo(ChessPath.PathType.DIAGONAL)));
        assertThat(diagonalPath3.getPathType(), is(equalTo(ChessPath.PathType.DIAGONAL)));
        assertThat(diagonalPath4.getPathType(), is(equalTo(ChessPath.PathType.DIAGONAL)));
        assertThat(verticalPath1.getPathType(), is(equalTo(ChessPath.PathType.VERTICAL)));
        assertThat(verticalPath2.getPathType(), is(equalTo(ChessPath.PathType.VERTICAL)));
        assertThat(horizontalPath1.getPathType(), is(equalTo(ChessPath.PathType.HORIZONTAL)));
        assertThat(horizontalPath2.getPathType(), is(equalTo(ChessPath.PathType.HORIZONTAL)));
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
