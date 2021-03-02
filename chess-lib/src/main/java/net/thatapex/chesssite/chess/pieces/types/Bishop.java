package net.thatapex.chesssite.chess.pieces.types;

import net.thatapex.chesssite.chess.board.ChessBoard;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.move.PotentialMove;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.Collection;

/**
 * Represents a Bishop piece.
 */
public class Bishop extends ChessPiece<Bishop> {

    /**
     * Create a new bishop
     *
     * @param color color of the bishop
     */
    public Bishop(final ChessPieceColor color) {
        super(color);
    }

    @Override
    public ChessPieceType<Bishop> getType() {
        return ChessPieceType.BISHOP;
    }

    @Override
    public void initializePotentialLegalMoves(final ChessSquare position, final Collection<ChessSquare> squares) {
        squares.addAll(position.makeLine(-1, -1));
        squares.addAll(position.makeLine(-1, 1));
        squares.addAll(position.makeLine(1, -1));
        squares.addAll(position.makeLine(1, 1));
    }

    @Override
    public boolean isMoveLegal(final ChessBoard boardState, final ChessSquare squareFrom, final PotentialMove move) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
