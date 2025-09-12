package com.example.classes.Game.States;

import com.example.classes.Game.GameContext;
import com.example.classes.Game.GameState;

public class XWonState implements GameState {
    @Override
    public void next(GameContext gameContext) {
    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}
