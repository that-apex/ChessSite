package net.thatapex.chesssite.chess.board.setup;

import net.thatapex.chesssite.chess.board.MutableChessBoard;
import net.thatapex.chesssite.chess.board.coordinate.ChessFile;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;

/**
 * A {@link BoardSetup} for a classical variant of chess.
 */
public class ClassicalChessGameBoardSetup implements BoardSetup {

    @Override
    public void setup(final MutableChessBoard board) {
        board.resetState();

        // Setup pieces
        this.setPiece(board, ChessPieceType.ROOK, ChessFile.fromFileLetter('a').orElseThrow());
        this.setPiece(board, ChessPieceType.KNIGHT, ChessFile.fromFileLetter('b').orElseThrow());
        this.setPiece(board, ChessPieceType.BISHOP, ChessFile.fromFileLetter('c').orElseThrow());
        this.setPiece(board, ChessPieceType.QUEEN, ChessFile.fromFileLetter('d').orElseThrow());
        this.setPiece(board, ChessPieceType.KING, ChessFile.fromFileLetter('e').orElseThrow());
        this.setPiece(board, ChessPieceType.BISHOP, ChessFile.fromFileLetter('f').orElseThrow());
        this.setPiece(board, ChessPieceType.KNIGHT, ChessFile.fromFileLetter('g').orElseThrow());
        this.setPiece(board, ChessPieceType.ROOK, ChessFile.fromFileLetter('h').orElseThrow());

        // Setup files
        for (int i = ChessFile.MINIMUM_INDEX; i <= ChessFile.MAXIMUM_INDEX; i++) {
            final ChessFile file = ChessFile.fromIndex(i).orElseThrow();

            board.setPiece(new ChessSquare(file, ChessPieceColor.WHITE.getPawnStartingRank()), ChessPieceType.PAWN.instantiatePiece(ChessPieceColor.WHITE));
            board.setPiece(new ChessSquare(file, ChessPieceColor.BLACK.getPawnStartingRank()), ChessPieceType.PAWN.instantiatePiece(ChessPieceColor.BLACK));
        }
    }

    private void setPiece(final MutableChessBoard board, final ChessPieceType<?> pieceType, final ChessFile file) {
        board.setPiece(new ChessSquare(file, ChessPieceColor.WHITE.getPieceStartingRank()), pieceType.instantiatePiece(ChessPieceColor.WHITE));
        board.setPiece(new ChessSquare(file, ChessPieceColor.BLACK.getPieceStartingRank()), pieceType.instantiatePiece(ChessPieceColor.BLACK));
    }
}
