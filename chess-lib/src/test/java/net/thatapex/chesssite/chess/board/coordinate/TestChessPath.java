package net.thatapex.chesssite.chess.board.coordinate;

import com.jparams.verifier.tostring.ToStringVerifier;
import net.thatapex.chesssite.chess.board.MutableChessBoard;
import net.thatapex.chesssite.chess.board.MutableChessBoardImpl;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.types.Knight;
import net.thatapex.chesssite.chess.pieces.types.Queen;
import net.thatapex.chesssite.chess.pieces.types.Rook;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestChessPath {

    @Test
    public void testValidatePathIsNotObstructed() {
        final ChessPath path = ChessSquare.fromChessNotation("f6").orElseThrow().findPathTo(ChessSquare.fromChessNotation("b2").orElseThrow()).orElseThrow();

        final MutableChessBoard board = new MutableChessBoardImpl();
        assertThat("path obstructed on an empty board", path.validatePathIsNotObstructed(board));

        board.setPiece(path.getStartingPoint(), new Knight(ChessPieceColor.WHITE));
        assertThat("path obstructed with a piece on the starting point", path.validatePathIsNotObstructed(board));

        board.setPiece(path.getEndingPoint(), new Queen(ChessPieceColor.BLACK));
        assertThat("path obstructed with a piece on the ending point", path.validatePathIsNotObstructed(board));

        for (final ChessSquare middlePoint : path.getMiddlePoints()) {
            board.setPiece(middlePoint, new Rook(ChessPieceColor.WHITE));
            assertThat("path not obstructed with pieces on middle points", !path.validatePathIsNotObstructed(board));
            board.setPiece(middlePoint, null);

            assertThat("path obstructed on an empty board", path.validatePathIsNotObstructed(board));
        }
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(ChessPath.class).verify();
    }

    @Test
    public void testToString() {
        ToStringVerifier.forClass(ChessPath.class).verify();
    }
}
