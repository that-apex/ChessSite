package net.thatapex.chesssite.chess.pieces.types;

import net.thatapex.chesssite.chess.board.ChessBoard;
import net.thatapex.chesssite.chess.board.coordinate.ChessFile;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.move.PotentialMove;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

import java.util.Collection;

/**
 * Represents a Queen piece.
 */
public class Queen extends ChessPiece<Queen> {

    /**
     * The file that queens start on, on a default chess board.
     */
    public static final ChessFile QUEEN_STARTING_FILE = ChessFile.fromFileLetter('d').orElseThrow();

    /**
     * Create a new queen
     *
     * @param color color of the queen
     */
    public Queen(final ChessPieceColor color) {
        super(color);
    }

    @Override
    public ChessPieceType<Queen> getType() {
        return ChessPieceType.QUEEN;
    }

    @Override
    public void initializePotentialLegalMoves(final ChessSquare position, final Collection<ChessSquare> squares) {
        squares.addAll(position.makeLine(-1, -1));
        squares.addAll(position.makeLine(-1, 1));
        squares.addAll(position.makeLine(1, -1));
        squares.addAll(position.makeLine(1, 1));
        squares.addAll(position.makeLine(-1, 0));
        squares.addAll(position.makeLine(1, 0));
        squares.addAll(position.makeLine(0, -1));
        squares.addAll(position.makeLine(0, 1));
    }

    @Override
    public boolean isMoveLegal(final ChessBoard boardState, final ChessSquare squareFrom, final PotentialMove move) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
