package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.board.coordinate.ChessRank;

public enum ChessPieceColor {
    WHITE(1, ChessRank.fromRankNumber(1).orElseThrow(), ChessRank.fromRankNumber(2).orElseThrow(), ChessRank.fromRankNumber(8).orElseThrow()),
    BLACK(-1, ChessRank.fromRankNumber(8).orElseThrow(), ChessRank.fromRankNumber(7).orElseThrow(), ChessRank.fromRankNumber(1).orElseThrow());

    private final int       rankAdvanceDirection;
    private final ChessRank pieceStartingRank;
    private final ChessRank pawnStartingRank;
    private final ChessRank promotionRank;

    ChessPieceColor(final int rankAdvanceDirection, final ChessRank pieceStartingRank, final ChessRank pawnStartingRank, final ChessRank promotionRank) {
        this.rankAdvanceDirection = rankAdvanceDirection;
        this.pieceStartingRank    = pieceStartingRank;
        this.pawnStartingRank     = pawnStartingRank;
        this.promotionRank        = promotionRank;
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

    public ChessRank getPromotionRank() {
        return this.promotionRank;
    }

    /**
     * Returns the color opposite to this one
     *
     * @return the color opposite to this one
     */
    ChessPieceColor getOpposite() {
        switch (this) {
            case WHITE:
                return ChessPieceColor.BLACK;
            case BLACK:
                return ChessPieceColor.WHITE;
            default:
                throw new IllegalStateException("Illegal color");
        }
    }
}
