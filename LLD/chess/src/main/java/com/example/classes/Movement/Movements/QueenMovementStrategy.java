package com.example.classes.Movement.Movements;

import com.example.classes.Board.Board;
import com.example.classes.Movement.MovementStrategy;
import com.example.classes.Position;
import com.example.enums.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class QueenMovementStrategy implements MovementStrategy {
    private final PieceColor pieceColor;

    public QueenMovementStrategy(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        List<Position> possibleMoves = getPossibleMoves(from, board);
        return possibleMoves.contains(to);
    }

    @Override
    public List<Position> getPossibleMoves(Position currentPosition, Board board) {
        List<Position> possibleMoves = new ArrayList<>();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // Queen moves like both rook and bishop
        // Horizontal, vertical, and diagonal directions
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Rook-like moves
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Bishop-like moves
        };

        for (int[] direction : directions) {
            int rowDelta = direction[0];
            int colDelta = direction[1];

            for (int i = 1; i < 8; i++) {
                int newRow = currentRow + (i * rowDelta);
                int newCol = currentCol + (i * colDelta);

                if (!Position.isValidPosition(newRow, newCol)) {
                    break; // Out of bounds
                }

                Position newPosition = new Position(newRow, newCol);
                
                if (board.getCell(newPosition).isEmpty()) {
                    possibleMoves.add(newPosition);
                } else if (board.getCell(newPosition).hasEnemyPiece(pieceColor)) {
                    possibleMoves.add(newPosition); // Capture
                    break; // Can't continue past a piece
                } else {
                    break; // Friendly piece blocking
                }
            }
        }

        return possibleMoves;
    }
}
