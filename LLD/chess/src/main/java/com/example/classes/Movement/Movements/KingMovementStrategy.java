package com.example.classes.Movement.Movements;

import com.example.classes.Board.Board;
import com.example.classes.Movement.MovementStrategy;
import com.example.classes.Position;
import com.example.enums.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class KingMovementStrategy implements MovementStrategy {
    private final PieceColor pieceColor;

    public KingMovementStrategy(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        // Regular king move (one square in any direction)
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        
        if (rowDiff <= 1 && colDiff <= 1 && (rowDiff != 0 || colDiff != 0)) {
            // Check if destination is empty or has enemy piece
            return board.getCell(to).isEmpty() || board.getCell(to).hasEnemyPiece(pieceColor);
        }
        
        // Check for castling
        if (rowDiff == 0 && colDiff == 2) {
            return canCastle(board, from, to);
        }
        
        return false;
    }

    @Override
    public List<Position> getPossibleMoves(Position currentPosition, Board board) {
        List<Position> possibleMoves = new ArrayList<>();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        // King moves one square in any direction
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] direction : directions) {
            int newRow = currentRow + direction[0];
            int newCol = currentCol + direction[1];

            if (Position.isValidPosition(newRow, newCol)) {
                Position newPosition = new Position(newRow, newCol);
                
                if (board.getCell(newPosition).isEmpty() || 
                    board.getCell(newPosition).hasEnemyPiece(pieceColor)) {
                    possibleMoves.add(newPosition);
                }
            }
        }

        // Add castling moves
        addCastlingMoves(possibleMoves, currentPosition, board);

        return possibleMoves;
    }

    private boolean canCastle(Board board, Position from, Position to) {
        // Basic castling checks
        int row = from.getRow();
        
        // Check if king hasn't moved (simplified - assume king is on starting square)
        boolean kingOnStartingSquare = (pieceColor == PieceColor.WHITE && row == 7 && from.getCol() == 4) ||
                                      (pieceColor == PieceColor.BLACK && row == 0 && from.getCol() == 4);
        
        if (!kingOnStartingSquare) {
            return false;
        }

        // Determine if it's kingside or queenside castling
        boolean isKingside = to.getCol() == 6;
        boolean isQueenside = to.getCol() == 2;

        if (!isKingside && !isQueenside) {
            return false;
        }

        // Check if rook is in position and path is clear
        int rookCol = isKingside ? 7 : 0;
        Position rookPos = new Position(row, rookCol);
        
        // Check if rook exists and is correct color
        if (board.getPiece(rookPos) == null || 
            board.getPiece(rookPos).getType().name() != "ROOK" ||
            board.getPiece(rookPos).getColor() != pieceColor) {
            return false;
        }

        // Check if path is clear
        int startCol = Math.min(from.getCol(), rookCol) + 1;
        int endCol = Math.max(from.getCol(), rookCol) - 1;
        
        for (int col = startCol; col <= endCol; col++) {
            if (!board.isEmpty(new Position(row, col))) {
                return false;
            }
        }

        return true;
    }

    private void addCastlingMoves(List<Position> moves, Position currentPos, Board board) {
        int row = currentPos.getRow();
        int col = currentPos.getCol();

        // Only check castling if king is on starting square
        if ((pieceColor == PieceColor.WHITE && row == 7 && col == 4) ||
            (pieceColor == PieceColor.BLACK && row == 0 && col == 4)) {
            
            // Kingside castling
            Position kingsideCastle = new Position(row, 6);
            if (canCastle(board, currentPos, kingsideCastle)) {
                moves.add(kingsideCastle);
            }

            // Queenside castling
            Position queensideCastle = new Position(row, 2);
            if (canCastle(board, currentPos, queensideCastle)) {
                moves.add(queensideCastle);
            }
        }
    }
}
