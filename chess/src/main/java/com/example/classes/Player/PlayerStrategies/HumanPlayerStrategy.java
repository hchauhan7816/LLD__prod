package com.example.classes.Player.PlayerStrategies;

import com.example.classes.Game.Game;
import com.example.classes.Move.Move;
import com.example.classes.Player.PlayerStrategy;
import com.example.classes.Position;
import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {
    private final Scanner scanner;

    public HumanPlayerStrategy() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Move getNextMove(Game game) {
        System.out.println("\n" + game.getCurrentPlayer().getName() + "'s turn:");
        System.out.print("Enter move (e.g., 'e2 e4'): ");
        
        try {
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
            
            if (parts.length != 2) {
                System.out.println("Invalid input format. Use: from to (e.g., 'e2 e4')");
                return getNextMove(game); // Recursive call for retry
            }
            
            Position from = Position.fromChessNotation(parts[0]);
            Position to = Position.fromChessNotation(parts[1]);
            
            return new Move(from, to, game.getCurrentPlayer());
            
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid move format: " + e.getMessage());
            return getNextMove(game); // Recursive call for retry
        }
    }
}