package com.example.classes.Game.States;

import com.example.classes.Game.GameContext;
import com.example.classes.Game.GameState;

public class OTurnState implements GameState {

    @Override
    public void next(GameContext context) {
        context.setState(new XTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

}
