package com.example.classes.Movement.Movements;

import com.example.classes.Board.Board;
import com.example.classes.Movement.MovementStrategy;
import com.example.classes.Position;
import com.example.enums.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class PawnMovementStrategy implements MovementStrategy {
    private final PieceColor pieceColor;

    public PawnMovementStrategy(PieceColor pieceColor) {
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
        int direction = (pieceColor == PieceColor.WHITE) ? -1 : 1; // White moves up (-1), Black moves down (+1)
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // Check if pawn has moved (simplified - assuming it hasn't moved if on starting row)
        boolean hasMoved = (pieceColor == PieceColor.WHITE && currentRow != 6) || 
                          (pieceColor == PieceColor.BLACK && currentRow != 1);

        // Forward moves
        int oneStepRow = currentRow + direction;
        if (Position.isValidPosition(oneStepRow, currentCol)) {
            Position oneStepPos = new Position(oneStepRow, currentCol);
            if (board.getCell(oneStepPos).isEmpty()) {
                possibleMoves.add(oneStepPos);
                
                // Two steps forward on first move
                if (!hasMoved) {
                    int twoStepRow = currentRow + (2 * direction);
                    if (Position.isValidPosition(twoStepRow, currentCol)) {
                        Position twoStepPos = new Position(twoStepRow, currentCol);
                        if (board.getCell(twoStepPos).isEmpty()) {
                            possibleMoves.add(twoStepPos);
                        }
                    }
                }
            }
        }

        // Diagonal captures
        int[] captureCols = {currentCol - 1, currentCol + 1};
        for (int captureCol : captureCols) {
            if (Position.isValidPosition(oneStepRow, captureCol)) {
                Position capturePos = new Position(oneStepRow, captureCol);
                if (board.getCell(capturePos).hasEnemyPiece(pieceColor)) {
                    possibleMoves.add(capturePos);
                }
            }
        }

        return possibleMoves;
    }
} 