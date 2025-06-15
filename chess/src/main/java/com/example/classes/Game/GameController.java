package com.example.classes.Game;

import com.example.classes.Move.Move;
import com.example.classes.Position;
import com.example.enums.GameStatus;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean playMove(String fromNotation, String toNotation) {
        try {
            Position from = Position.fromChessNotation(fromNotation);
            Position to = Position.fromChessNotation(toNotation);
            
            Move move = new Move(from, to, game.getCurrentPlayer());
            return game.makeMove(move);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid move notation: " + e.getMessage());
            return false;
        }
    }

    public boolean playMove(Move move) {
        return game.makeMove(move);
    }

    public boolean undoLastMove() {
        return game.undoLastMove();
    }

    public void displayBoard() {
        game.getBoard().printBoard();
    }

    public void displayGameStatus() {
        System.out.println("Current player: " + game.getCurrentPlayer());
        System.out.println("Game status: " + game.getGameStatus());
        System.out.println("Move count: " + game.getMoveCount());
    }

    public boolean isGameActive() {
        return game.getGameStatus() == GameStatus.ACTIVE;
    }

    public void resignCurrentPlayer() {
        game.resign(game.getCurrentPlayer());
        System.out.println(game.getCurrentPlayer().getName() + " has resigned!");
    }

    public void startGame() {
        System.out.println("=== Chess Game Started ===");
        System.out.println("White: " + game.getWhitePlayer().getName());
        System.out.println("Black: " + game.getBlackPlayer().getName());
        displayBoard();
        displayGameStatus();
    }

    public void endGame() {
        System.out.println("=== Game Ended ===");
        displayGameStatus();
        
        switch (game.getGameStatus()) {
            case WHITE_WIN:
                System.out.println("White wins!");
                break;
            case BLACK_WIN:
                System.out.println("Black wins!");
                break;
            case STALEMATE:
                System.out.println("Game ended in stalemate!");
                break;
            case FORFEIT:
                System.out.println("Game forfeited!");
                break;
            case RESIGNATION:
                System.out.println("Game ended by resignation!");
                break;
            default:
                System.out.println("Game ended!");
        }
    }
}
