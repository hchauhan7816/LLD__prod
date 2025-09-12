package com.example.classes.Game;

import com.example.classes.Game.States.OTurnState;

public class GameContext {
    private GameState currentState;

    public GameContext() {
        currentState = new OTurnState();
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void next() {
        currentState.next(this);
    }

    public boolean isGameOver() {
        return currentState.isGameOver();
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
