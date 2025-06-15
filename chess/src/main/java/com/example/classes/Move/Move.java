package com.example.classes.Move;

import com.example.classes.Board.Board;
import com.example.classes.Piece.Piece;
import com.example.classes.Player.Player;
import com.example.classes.Position;

public class Move {
    private final Position from;
    private final Position to;
    private final Player player;
    private Piece movedPiece;        // Track the piece that was moved
    private Piece capturedPiece;
    private boolean executed;
    private boolean isCastling;      // Track if this is a castling move

    public Move(Position from, Position to, Player player) {
        this.from = from;
        this.to = to;
        this.player = player;
        this.executed = false;
        this.isCastling = false;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public boolean isExecuted() {
        return executed;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public boolean execute(Board board) {
        if (executed) {
            return false; // Move already executed
        }

        Piece piece = board.getPiece(from);
        if (piece == null || piece.getColor() != player.getColor()) {
            return false; // No piece or wrong color
        }

        if (!piece.isValidMove(from, to, board)) {
            return false; // Invalid move
        }

        // Store the piece that was moved
        movedPiece = piece;
        
        // Check if this is a castling move
        if (piece.getType().name().equals("KING") && Math.abs(to.getCol() - from.getCol()) == 2) {
            return executeCastling(board);
        }

        // Store captured piece for undo functionality
        capturedPiece = board.getPiece(to);
        
        // Execute the move
        board.movePiece(from, to);
        executed = true;
        
        return true;
    }

    private boolean executeCastling(Board board) {
        isCastling = true;
        int row = from.getRow();
        boolean isKingside = to.getCol() == 6;
        
        // Move the king
        board.movePiece(from, to);
        
        // Move the rook
        if (isKingside) {
            // Kingside castling: rook from h-file to f-file
            Position rookFrom = new Position(row, 7);
            Position rookTo = new Position(row, 5);
            board.movePiece(rookFrom, rookTo);
        } else {
            // Queenside castling: rook from a-file to d-file
            Position rookFrom = new Position(row, 0);
            Position rookTo = new Position(row, 3);
            board.movePiece(rookFrom, rookTo);
        }
        
        executed = true;
        return true;
    }

    public boolean undo(Board board) {
        if (!executed) {
            return false; // Move not executed
        }

        if (isCastling) {
            return undoCastling(board);
        }

        // Move piece back to original position
        board.getCell(to).removePiece();
        board.setPiece(from, movedPiece);
        
        // Restore captured piece if any
        if (capturedPiece != null) {
            board.setPiece(to, capturedPiece);
        }
        
        executed = false;
        return true;
    }

    private boolean undoCastling(Board board) {
        int row = from.getRow();
        boolean isKingside = to.getCol() == 6;
        
        // Move king back
        board.movePiece(to, from);
        
        // Move rook back
        if (isKingside) {
            Position rookFrom = new Position(row, 5);
            Position rookTo = new Position(row, 7);
            board.movePiece(rookFrom, rookTo);
        } else {
            Position rookFrom = new Position(row, 3);
            Position rookTo = new Position(row, 0);
            board.movePiece(rookFrom, rookTo);
        }
        
        executed = false;
        isCastling = false;
        return true;
    }

    @Override
    public String toString() {
        String moveStr = from.toChessNotation() + " -> " + to.toChessNotation();
        if (movedPiece != null) {
            moveStr += " (" + movedPiece.getType().name() + ")";
        }
        if (isCastling) {
            moveStr += " [CASTLING]";
        }
        return moveStr + " by " + player.getName();
    }
}
