package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.board.coordinate.ChessRank;

public enum ChessPieceColor {
    WHITE(1, ChessRank.fromRankNumber(1).orElseThrow(), ChessRank.fromRankNumber(2).orElseThrow()),
    BLACK(-1, ChessRank.fromRankNumber(8).orElseThrow(), ChessRank.fromRankNumber(7).orElseThrow());

    private final int       rankAdvanceDirection;
    private final ChessRank pieceStartingRank;
    private final ChessRank pawnStartingRank;

    ChessPieceColor(final int rankAdvanceDirection, final ChessRank pieceStartingRank, final ChessRank pawnStartingRank) {
        this.rankAdvanceDirection = rankAdvanceDirection;
        this.pieceStartingRank    = pieceStartingRank;
        this.pawnStartingRank     = pawnStartingRank;
    }

    public int getRankAdvanceDirection() {
        return this.rankAdvanceDirection;
    }

    public ChessRank getPieceStartingRank() {
        return this.pieceStartingRank;
    }

    public ChessRank getPawnStartingRank() {
        return this.pawnStartingRank;
    }
}
