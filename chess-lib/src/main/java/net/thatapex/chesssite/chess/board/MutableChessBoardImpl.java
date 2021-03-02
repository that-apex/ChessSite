package net.thatapex.chesssite.chess.board;

import net.thatapex.chesssite.chess.board.coordinate.ChessFile;
import net.thatapex.chesssite.chess.board.coordinate.ChessRank;
import net.thatapex.chesssite.chess.board.coordinate.ChessSquare;
import net.thatapex.chesssite.chess.pieces.ChessPiece;
import net.thatapex.chesssite.chess.pieces.ChessPieceColor;
import net.thatapex.chesssite.chess.pieces.ChessPieceType;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A default implementation of the {@link MutableChessBoard}
 */
public class MutableChessBoardImpl implements MutableChessBoard {
    public static final int CHESS_BOARD_SIZE = (ChessFile.MAXIMUM_INDEX - ChessFile.MINIMUM_INDEX + 1) * (ChessRank.MAXIMUM_INDEX - ChessRank.MINIMUM_INDEX + 1);

    private final List<ChessPiece<?>>                  pieces          = new ArrayList<>(CHESS_BOARD_SIZE);
    private final Map<ChessPieceColor, CastlingRights> castlingRights  = new HashMap<>(ChessPieceColor.values().length);
    private       ChessSquare                          enPassantSquare = null;

    /**
     * Constructs a new ChessBoard, initialized with the default board state.
     */
    public MutableChessBoardImpl() {
        this.resetState();
    }

    /**
     * Constructs a new ChessBoard, initialized with a copy of the supplied board.
     *
     * @param board board to copy the state from
     */
    public MutableChessBoardImpl(final ChessBoard board) {
        Validate.isTrue(board.getBoardSize() == CHESS_BOARD_SIZE, "board size is invalid");

        board.asPieceList()
                .stream()
                .map(piece -> piece == null ? null : piece.clonePiece())
                .forEach(this.pieces::add);

        for (final ChessPieceColor color : ChessPieceColor.values()) {
            this.castlingRights.put(color, board.getCastlingRightsFor(color));
        }

        this.enPassantSquare = board.getEnPassantSquare().orElse(null);
    }

    @Override
    public List<ChessPiece<?>> asPieceList() {
        return this.pieces;
    }

    @Override
    public int getBoardSize() {
        return CHESS_BOARD_SIZE;
    }

    @Override
    public void resetState() {
        this.clearPieces();
        this.castlingRights.clear();

        for (final ChessPieceColor color : ChessPieceColor.values()) {
            this.castlingRights.put(color, new CastlingRights(true, true));
        }

        this.enPassantSquare = null;
    }

    @Override
    public void setCastlingRightsFor(final ChessPieceColor color, final CastlingRights rights) {
        this.castlingRights.put(color, rights);
    }

    @Override
    public void setEnPassantSquare(final ChessSquare square) {
        this.enPassantSquare = square;
    }

    @Override
    public void clearPieces() {
        this.pieces.clear();

        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            this.pieces.add(null);
        }
    }

    @Override
    public void setPiece(final ChessSquare square, final ChessPiece<?> piece) {
        // do not duplicate pieces
        for (int i = 0; i < this.pieces.size(); i++) {
            if (this.pieces.get(i) == piece) {
                this.pieces.set(i, null);
            }
        }

        this.pieces.set(this.getIndex(square), piece);
    }

    @Override
    public CastlingRights getCastlingRightsFor(final ChessPieceColor color) {
        return this.castlingRights.get(color);
    }

    @Override
    public Optional<ChessSquare> getEnPassantSquare() {
        return Optional.ofNullable(this.enPassantSquare);
    }

    @Override
    public Optional<ChessPiece<?>> getPiece(final ChessSquare square) {
        return Optional.ofNullable(this.pieces.get(this.getIndex(square)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ChessPiece<T>> Optional<ChessPiece<T>> getPiece(final ChessSquare square, final ChessPieceType<T> type, final ChessPieceColor color) {
        return this.getPiece(square)
                .filter(it -> it.getType().equals(type) && it.getColor().equals(color))
                .map(piece -> ((ChessPiece<T>) piece));
    }

    @Override
    public ChessBoard cloneAsImmutable() {
        return new ImmutableChessBoardView(this.cloneAsMutable());
    }

    @Override
    public MutableChessBoard cloneAsMutable() {
        return new MutableChessBoardImpl(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MutableChessBoardImpl)) {
            return false;
        }

        final MutableChessBoardImpl that = (MutableChessBoardImpl) o;

        return new EqualsBuilder().append(this.pieces, that.pieces).append(this.castlingRights, that.castlingRights).append(this.enPassantSquare, that.enPassantSquare).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.pieces).append(this.castlingRights).append(this.enPassantSquare).toHashCode();
    }

    private int getIndex(final ChessSquare square) {
        return (ChessRank.MAXIMUM_INDEX - ChessRank.MINIMUM_INDEX + 1) * square.getFile().getIndex() + square.getRank().getIndex();
    }
}
