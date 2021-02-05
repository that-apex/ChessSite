package net.thatapex.chesssite.chess.pieces.types;

import net.thatapex.chesssite.chess.board.ChessBoard;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.move.PotentialMove;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.Collection;

/**
 * Represents a Pawn piece.
 */
public class Pawn extends ChessPiece<Pawn> {

    /**
     * Create a new pawn
     *
     * @param color color of the pawn
     */
    public Pawn(final ChessPieceColor color) {
        super(color);
    }

    @Override
    public ChessPieceType<Pawn> getType() {
        return ChessPieceType.PAWN;
    }

    @Override
    public void initializePotentialLegalMoves(final ChessSquare position, final Collection<ChessSquare> squares) {
        final int rankAdvanceDirection = getColor().getRankAdvanceDirection();

        position.getShifted(-1, rankAdvanceDirection).ifPresent(squares::add);
        position.getShifted(0, rankAdvanceDirection).ifPresent(squares::add);
        position.getShifted(1, rankAdvanceDirection).ifPresent(squares::add);

        if (position.getRank().equals(getColor().getPawnStartingRank())) {
            position.getShifted(0, rankAdvanceDirection * 2).ifPresent(squares::add);
        }
    }

    @Override
    public void isMoveLegal(final ChessBoard boardState, final ChessSquare squareFrom, final PotentialMove move) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
