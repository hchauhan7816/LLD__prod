package com.example.classes.Movement.Movements;

import com.example.classes.Board.Board;
import com.example.classes.Movement.MovementStrategy;
import com.example.classes.Position;
import com.example.enums.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class KnightMovementStrategy implements MovementStrategy {
    private final PieceColor pieceColor;

    public KnightMovementStrategy(PieceColor pieceColor) {
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

        // Knight moves in L-shape: 2 squares in one direction, 1 in perpendicular
        int[][] knightMoves = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newRow = currentRow + move[0];
            int newCol = currentCol + move[1];

            if (Position.isValidPosition(newRow, newCol)) {
                Position newPosition = new Position(newRow, newCol);
                
                if (board.getCell(newPosition).isEmpty() || 
                    board.getCell(newPosition).hasEnemyPiece(pieceColor)) {
                    possibleMoves.add(newPosition);
                }
            }
        }

        return possibleMoves;
    }
} 