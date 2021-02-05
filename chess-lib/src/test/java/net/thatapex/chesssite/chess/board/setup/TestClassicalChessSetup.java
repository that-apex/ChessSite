package net.thatapex.chesssite.chess.board.setup;

import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestClassicalChessSetup {
    private final Collection<ChessSquare> validatedSquares = new ArrayList<>();

    @Test
    public void testClassicalChessSetup() {
        final BoardSetupTester tester = new BoardSetupTester();
        tester.setup(new ClassicalChessGameBoardSetup());

        tester.validate("a1", ChessPieceColor.WHITE, ChessPieceType.ROOK);
        tester.validate("b1", ChessPieceColor.WHITE, ChessPieceType.KNIGHT);
        tester.validate("c1", ChessPieceColor.WHITE, ChessPieceType.BISHOP);
        tester.validate("d1", ChessPieceColor.WHITE, ChessPieceType.QUEEN);
        tester.validate("e1", ChessPieceColor.WHITE, ChessPieceType.KING);
        tester.validate("f1", ChessPieceColor.WHITE, ChessPieceType.BISHOP);
        tester.validate("g1", ChessPieceColor.WHITE, ChessPieceType.KNIGHT);
        tester.validate("h1", ChessPieceColor.WHITE, ChessPieceType.ROOK);

        tester.validate("a2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("b2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("c2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("d2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("e2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("f2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("g2", ChessPieceColor.WHITE, ChessPieceType.PAWN);
        tester.validate("h2", ChessPieceColor.WHITE, ChessPieceType.PAWN);


        tester.validate("a8", ChessPieceColor.BLACK, ChessPieceType.ROOK);
        tester.validate("b8", ChessPieceColor.BLACK, ChessPieceType.KNIGHT);
        tester.validate("c8", ChessPieceColor.BLACK, ChessPieceType.BISHOP);
        tester.validate("d8", ChessPieceColor.BLACK, ChessPieceType.QUEEN);
        tester.validate("e8", ChessPieceColor.BLACK, ChessPieceType.KING);
        tester.validate("f8", ChessPieceColor.BLACK, ChessPieceType.BISHOP);
        tester.validate("g8", ChessPieceColor.BLACK, ChessPieceType.KNIGHT);
        tester.validate("h8", ChessPieceColor.BLACK, ChessPieceType.ROOK);

        tester.validate("a7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("b7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("c7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("d7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("e7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("f7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("g7", ChessPieceColor.BLACK, ChessPieceType.PAWN);
        tester.validate("h7", ChessPieceColor.BLACK, ChessPieceType.PAWN);


        tester.validateRest();
    }
}
