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
 * Represents a King piece.
 */
public class King extends ChessPiece<King> {

    /**
     * The file that kings start on, on a default chess board.
     */
    public static final ChessFile KING_STARTING_FILE = ChessFile.fromFileLetter('e').orElseThrow();

    /**
     * Create a new king
     *
     * @param color color of the king
     */
    public King(final ChessPieceColor color) {
        super(color);
    }

    @Override
    public ChessPieceType<King> getType() {
        return ChessPieceType.KING;
    }

    @Override
    public void initializePotentialLegalMoves(final ChessSquare position, final Collection<ChessSquare> squares) {
       position.getShifted(-1, -1).ifPresent(squares::add);
       position.getShifted(-1, 0).ifPresent(squares::add);
       position.getShifted(-1, 1).ifPresent(squares::add);
       position.getShifted(0, -1).ifPresent(squares::add);
       position.getShifted(0, 1).ifPresent(squares::add);
       position.getShifted(1, -1).ifPresent(squares::add);
       position.getShifted(1, 0).ifPresent(squares::add);
       position.getShifted(1, 1).ifPresent(squares::add);

        if (position.getFile().equals(KING_STARTING_FILE) && position.getRank().equals(getColor().getPieceStartingRank())) {
            squares.add(position.getShifted(-2, 0).orElseThrow());
            squares.add(position.getShifted(2, 0).orElseThrow());
        }
    }

    @Override
    public void isMoveLegal(final ChessBoard boardState, final ChessSquare squareFrom, final PotentialMove move) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
