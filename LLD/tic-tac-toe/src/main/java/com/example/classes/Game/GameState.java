package com.example.classes.Game;

public interface GameState {
    void next(GameContext context);
    boolean isGameOver();
}
