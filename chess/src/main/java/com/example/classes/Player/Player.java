package com.example.classes.Player;

import com.example.enums.PieceColor;

public class Player {
    private final String name;
    private final PieceColor color;
    private final PlayerStrategy playerStrategy;

    public Player(String name, PieceColor color, PlayerStrategy playerStrategy) {
        this.name = name;
        this.color = color;
        this.playerStrategy = playerStrategy;
    }

    public String getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }

    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }

    @Override
    public String toString() {
        return name + " (" + color.name() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return name.equals(player.name) && color == player.color;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + color.hashCode();
    }
}
