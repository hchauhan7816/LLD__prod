package com.example.classes.Player;

import com.example.classes.Board.Board;
import com.example.classes.Postion.Position;

public interface PlayerStrategy {

    Position makeMove(Board board);
}
