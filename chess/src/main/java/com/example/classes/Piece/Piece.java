package com.example.classes.Piece;

import com.example.classes.Board.Board;
import com.example.classes.Movement.MovementStrategy;
import com.example.classes.Position;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;
import java.util.List;

public class Piece {
    protected final PieceType type;
    protected final PieceColor color;
    protected boolean hasMoved;
    protected MovementStrategy movementStrategy;

    public Piece(PieceType type, PieceColor color, MovementStrategy movementStrategy) {
        this.type = type;
        this.color = color;
        this.hasMoved = false;
        this.movementStrategy = movementStrategy;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved() {
        this.hasMoved = true;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    // Delegate to movement strategy
    public List<Position> getPossibleMoves(Position currentPosition, Board board) {
        return movementStrategy.getPossibleMoves(currentPosition, board);
    }
    
    // Delegate to movement strategy
    public boolean isValidMove(Position from, Position to, Board board) {
        return movementStrategy.canMove(board, from, to);
    }

    @Override
    public String toString() {
        return color.name() + " " + type.name();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece piece = (Piece) obj;
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + color.hashCode();
    }
}

