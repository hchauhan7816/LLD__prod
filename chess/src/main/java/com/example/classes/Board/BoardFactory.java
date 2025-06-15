package com.example.classes.Board;

import com.example.classes.Piece.Pieces.*;
import com.example.classes.Position;
import com.example.enums.PieceColor;

public class BoardFactory {
    
    public static Board createStandardBoard() {
        Board board = new Board();
        setupPieces(board);
        return board;
    }
    
    public static Board createEmptyBoard() {
        return new Board();
    }
    
    private static void setupPieces(Board board) {
        // Setup white pieces (bottom rows)
        setupBackRank(board, 7, PieceColor.WHITE);
        setupPawns(board, 6, PieceColor.WHITE);
        
        // Setup black pieces (top rows)
        setupBackRank(board, 0, PieceColor.BLACK);
        setupPawns(board, 1, PieceColor.BLACK);
    }
    
    private static void setupBackRank(Board board, int row, PieceColor color) {
        // Rooks
        board.setPiece(new Position(row, 0), new Rook(color));
        board.setPiece(new Position(row, 7), new Rook(color));
        
        // Knights
        board.setPiece(new Position(row, 1), new Knight(color));
        board.setPiece(new Position(row, 6), new Knight(color));
        
        // Bishops
        board.setPiece(new Position(row, 2), new Bishop(color));
        board.setPiece(new Position(row, 5), new Bishop(color));
        
        // Queen and King
        board.setPiece(new Position(row, 3), new Queen(color));
        board.setPiece(new Position(row, 4), new King(color));
    }
    
    private static void setupPawns(Board board, int row, PieceColor color) {
        for (int col = 0; col < 8; col++) {
            board.setPiece(new Position(row, col), new Pawn(color));
        }
    }
    
    // For testing purposes
    public static Board createSimpleBoard() {
        Board board = new Board();
        
        // Place just kings and queens for simple testing
        board.setPiece(new Position(7, 4), new King(PieceColor.WHITE));
        board.setPiece(new Position(7, 3), new Queen(PieceColor.WHITE));
        
        board.setPiece(new Position(0, 4), new King(PieceColor.BLACK));
        board.setPiece(new Position(0, 3), new Queen(PieceColor.BLACK));
        
        return board;
    }
}
