package net.thatapex.chesssite.chess.pieces.types;

import net.thatapex.chesssite.chess.board.ChessBoard;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.move.PotentialMove;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.Collection;

/**
 * Represents a Rook piece.
 */
public class Rook extends ChessPiece<Rook> {

    /**
     * Create a new rook
     *
     * @param color color of the rook
     */
    public Rook(final ChessPieceColor color) {
        super(color);
    }

    @Override
    public ChessPieceType<Rook> getType() {
        return ChessPieceType.ROOK;
    }

    @Override
    public void initializePotentialLegalMoves(final ChessSquare position, final Collection<ChessSquare> squares) {
        squares.addAll(position.makeLine(-1, 0));
        squares.addAll(position.makeLine(1, 0));
        squares.addAll(position.makeLine(0, -1));
        squares.addAll(position.makeLine(0, 1));
    }

    @Override
    public void isMoveLegal(final ChessBoard boardState, final ChessSquare squareFrom, final PotentialMove move) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
