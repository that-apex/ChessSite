package net.thatapex.chesssite.chess.pieces;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class TestChessPiece {

    @Test
    public void testPieceCloneWithColor() {
        for (final ChessPieceType<?> pieceType : ChessPieceType.values()) {
            for (final ChessPieceColor color : ChessPieceColor.values()) {
                final ChessPiece<?> originalPiece = pieceType.instantiatePiece(color);

                for (final ChessPieceColor newColor : ChessPieceColor.values()) {
                    final ChessPiece<?> clonedPiece = originalPiece.clonePiece(newColor);

                    assertThat("piece types do not match", clonedPiece.getType(), equalTo(originalPiece.getType()));
                    assertThat("piece colors do not match", clonedPiece.getColor(), equalTo(newColor));

                    assertThat("cloned piece is the same instance as the original piece", clonedPiece, is(not(sameInstance(originalPiece))));
                }
            }
        }
    }

    @Test
    public void testPieceExactClone() {
        for (final ChessPieceType<?> pieceType : ChessPieceType.values()) {
            for (final ChessPieceColor color : ChessPieceColor.values()) {
                final ChessPiece<?> originalPiece = pieceType.instantiatePiece(color);
                final ChessPiece<?> clonedPiece   = originalPiece.clonePiece();

                assertThat("piece types do not match", clonedPiece.getType(), equalTo(originalPiece.getType()));
                assertThat("piece colors do not match", clonedPiece.getColor(), equalTo(originalPiece.getColor()));

                assertThat("cloned piece does not equal to the original", clonedPiece, equalTo(originalPiece));
                assertThat("cloned piece is the same instance as the original piece", clonedPiece, is(not(sameInstance(originalPiece))));
            }
        }
    }
}
