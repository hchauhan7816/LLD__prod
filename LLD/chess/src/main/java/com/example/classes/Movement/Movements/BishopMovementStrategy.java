package com.example.classes.Movement.Movements;

import com.example.classes.Board.Board;
import com.example.classes.Movement.MovementStrategy;
import com.example.classes.Position;
import com.example.enums.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class BishopMovementStrategy implements MovementStrategy {
    private final PieceColor pieceColor;

    public BishopMovementStrategy(PieceColor pieceColor) {
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

        // Bishop moves diagonally
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

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