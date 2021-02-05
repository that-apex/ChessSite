package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

public class TestImmutableChessBoard {

    @Test
    public void testImmutableBoardView() {
        final MutableChessBoard mutableBoard = new MutableChessBoardImpl();

        // setup random board
        final Map<ChessSquare, ChessPiece<?>> randomPieces = new HashMap<>();
        BoardTestHelpers.setupRandomBoard(mutableBoard, randomPieces);

        // create a view
        final ChessBoard immutableView = new ImmutableChessBoardView(mutableBoard);

        // validate view
        BoardTestHelpers.testBoardStateMatch(immutableView, immutableView, randomPieces);

        // validate view clones
        final MutableChessBoard mutableClone = immutableView.cloneAsMutable();
        BoardTestHelpers.testBoardStateMatch(mutableClone, immutableView, randomPieces);
        assertThat("mutable clone is the same instance as the wrapped object", mutableClone, not(sameInstance(mutableBoard)));

        BoardTestHelpers.testBoardStateMatch(immutableView.cloneAsImmutable(), immutableView, randomPieces);
    }

}
