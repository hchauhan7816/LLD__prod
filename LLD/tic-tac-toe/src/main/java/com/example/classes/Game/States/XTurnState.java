package com.example.classes.Game.States;

import com.example.classes.Game.GameContext;
import com.example.classes.Game.GameState;

public class XTurnState implements GameState {

    @Override
    public void next(GameContext gameContext) {
        gameContext.setState(new OTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}