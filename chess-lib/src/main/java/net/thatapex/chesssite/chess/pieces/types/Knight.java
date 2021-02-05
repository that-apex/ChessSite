package net.thatapex.chesssite.chess.pieces.types;

import net.thatapex.chesssite.chess.board.ChessBoard;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.move.PotentialMove;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.Collection;

/**
 * Represents a Knight piece.
 */
public class Knight extends ChessPiece<Knight> {

    /**
     * Create a new knight
     *
     * @param color color of the knight
     */
    public Knight(final ChessPieceColor color) {
        super(color);
    }

    @Override
    public ChessPieceType<Knight> getType() {
        return ChessPieceType.KNIGHT;
    }

    @Override
    public void initializePotentialLegalMoves(final ChessSquare position, final Collection<ChessSquare> squares) {
        position.getShifted(-2, -1).ifPresent(squares::add);
        position.getShifted(-2, 1).ifPresent(squares::add);
        position.getShifted(2, -1).ifPresent(squares::add);
        position.getShifted(2, 1).ifPresent(squares::add);
        position.getShifted(-1, -2).ifPresent(squares::add);
        position.getShifted(1, -2).ifPresent(squares::add);
        position.getShifted(-1, 2).ifPresent(squares::add);
        position.getShifted(1, 2).ifPresent(squares::add);
    }

    @Override
    public void isMoveLegal(final ChessBoard boardState, final ChessSquare squareFrom, final PotentialMove move) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
