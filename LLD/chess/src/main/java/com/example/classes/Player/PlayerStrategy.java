package com.example.classes.Player;

import com.example.classes.Game.Game;
import com.example.classes.Move.Move;

public interface PlayerStrategy {
    Move getNextMove(Game game);
}
