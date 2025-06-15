package com.example.classes.Board;

import com.example.classes.Cell.Cell;
import com.example.classes.Piece.Piece;
import com.example.classes.Position;
import com.example.enums.PieceColor;

// public final class Board {
public class Board {

    // private static Board instance;

    private static final int BOARD_SIZE = 8;
    private Cell[][] cells;

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cells[row][col] = new Cell(new Position(row, col));
            }
        }
    }

    public Cell getCell(Position position) {
        return cells[position.getRow()][position.getCol()];
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setPiece(Position position, Piece piece) {
        getCell(position).setPiece(piece);
    }

    public Piece getPiece(Position position) {
        return getCell(position).getPiece();
    }

    public boolean isEmpty(Position position) {
        return getCell(position).isEmpty();
    }

    public boolean hasEnemyPiece(Position position, PieceColor playerColor) {
        return getCell(position).hasEnemyPiece(playerColor);
    }

    public boolean hasFriendlyPiece(Position position, PieceColor playerColor) {
        return getCell(position).hasFriendlyPiece(playerColor);
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        getCell(from).removePiece();
        setPiece(to, piece);
        piece.setMoved();
    }

    public void resetBoard() {
        initializeBoard();
    }

    public void printBoard() {
        System.out.println("  a b c d e f g h");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print((8 - row) + " ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                Cell cell = cells[row][col];
                if (cell.isEmpty()) {
                    System.out.print(". ");
                } else {
                    Piece piece = cell.getPiece();
                    String symbol = piece.getType().getSymbol();
                    if (piece.getColor() == PieceColor.BLACK) {
                        symbol = symbol.toLowerCase();
                    }
                    System.out.print(symbol + " ");
                }
            }
            System.out.println((8 - row));
        }
        System.out.println("  a b c d e f g h");
    }
}
