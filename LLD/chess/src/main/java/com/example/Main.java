package com.example;

import com.example.classes.Board.BoardFactory;
import com.example.classes.Game.Game;
import com.example.classes.Game.GameController;
import com.example.classes.Move.Move;
import com.example.classes.Player.Player;
import com.example.classes.Player.PlayerStrategies.HumanPlayerStrategy;
import com.example.enums.PieceColor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Welcome to Chess Game ===\n");

        // Create players with colors
        Player whitePlayer = new Player("Alice", PieceColor.WHITE, new HumanPlayerStrategy());
        Player blackPlayer = new Player("Bob", PieceColor.BLACK, new HumanPlayerStrategy());

        // Create game with standard chess board
        Game game = new Game(whitePlayer, blackPlayer, BoardFactory.createStandardBoard());
        GameController gameController = new GameController(game);

        // Start the game
        gameController.startGame();

        // Game loop
        while (gameController.isGameActive()) {
            // Get move from current player using their strategy
            Move move = game.getCurrentPlayer().getPlayerStrategy().getNextMove(game);
            
            if (gameController.playMove(move)) {
                System.out.println("Move played: " + move);
                gameController.displayBoard();
                gameController.displayGameStatus();
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }

        // End game
        gameController.endGame();
    }
}