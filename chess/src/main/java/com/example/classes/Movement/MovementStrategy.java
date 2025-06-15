package com.example.classes.Movement;

import com.example.classes.Board.Board;
import com.example.classes.Position;
import java.util.List;

public interface MovementStrategy {
    boolean canMove(Board board, Position from, Position to);
    List<Position> getPossibleMoves(Position currentPosition, Board board);
}
