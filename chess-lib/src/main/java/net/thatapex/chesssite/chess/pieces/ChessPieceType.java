package net.thatapex.chesssite.chess.pieces;

import net.thatapex.chesssite.chess.pieces.types.Bishop;
import net.thatapex.chesssite.chess.pieces.types.King;
import net.thatapex.chesssite.chess.pieces.types.Knight;
import net.thatapex.chesssite.chess.pieces.types.Pawn;
import net.thatapex.chesssite.chess.pieces.types.Queen;
import net.thatapex.chesssite.chess.pieces.types.Rook;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ChessPieceType<T extends ChessPiece<T>> {
    public static final ChessPieceType<King>   KING   = new ChessPieceType<>(King.class, King::new, 'K', false, 0);
    public static final ChessPieceType<Queen>  QUEEN  = new ChessPieceType<>(Queen.class, Queen::new, 'Q', true, 9);
    public static final ChessPieceType<Bishop> BISHOP = new ChessPieceType<>(Bishop.class, Bishop::new, 'B', true, 3);
    public static final ChessPieceType<Knight> KNIGHT = new ChessPieceType<>(Knight.class, Knight::new, 'N', true, 3);
    public static final ChessPieceType<Rook>   ROOK   = new ChessPieceType<>(Rook.class, Rook::new, 'R', true, 5);
    public static final ChessPieceType<Pawn>   PAWN   = new ChessPieceType<>(Pawn.class, Pawn::new, (char) 0, false, 1);

    private static final List<ChessPieceType<?>> VALUES = Arrays.asList(KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN);

    private final Class<T>                     baseClass;
    private final Function<ChessPieceColor, T> constructor;
    private final char                         chessNotationCharacter;
    private final boolean                      validPromotionTarget;
    private final int                          symbolicalMaterialValue;

    protected ChessPieceType(final Class<T> baseClass, final Function<ChessPieceColor, T> constructor, final char chessNotationCharacter, final boolean validPromotionTarget, final int symbolicalMaterialValue) {
        this.baseClass               = baseClass;
        this.constructor             = constructor;
        this.chessNotationCharacter  = chessNotationCharacter;
        this.validPromotionTarget    = validPromotionTarget;
        this.symbolicalMaterialValue = symbolicalMaterialValue;
    }

    public Class<T> getBaseClass() {
        return this.baseClass;
    }

    public Function<ChessPieceColor, T> getConstructor() {
        return this.constructor;
    }

    public char getChessNotationCharacter() {
        return this.chessNotationCharacter;
    }

    public boolean isValidPromotionTarget() {
        return this.validPromotionTarget;
    }

    public int getSymbolicalMaterialValue() {
        return this.symbolicalMaterialValue;
    }

    public String getName() {
        return this.baseClass.getSimpleName();
    }

    public T instantiatePiece(final ChessPieceColor color) {
        return this.constructor.apply(color);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("notation", this.getChessNotationCharacter())
                .toString();
    }

    public static List<ChessPieceType<?>> values() {
        return VALUES;
    }

    public static Optional<ChessPieceType<?>> getByNotationCharacter(final char character) {
        for (final ChessPieceType<?> value : values()) {
            if (value.chessNotationCharacter != character) {
                continue;
            }

            return Optional.of(value);
        }

        return Optional.empty();
    }
}
