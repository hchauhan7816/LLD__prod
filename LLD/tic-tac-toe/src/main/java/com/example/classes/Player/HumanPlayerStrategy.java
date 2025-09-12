package com.example.classes.Player;

import java.util.Scanner;

import com.example.classes.Board.Board;
import com.example.classes.Postion.Position;

public class HumanPlayerStrategy implements PlayerStrategy {

    private Scanner scanner;
    private String playerName;

    public HumanPlayerStrategy(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public Position makeMove(Board board) {
        while (true) {
            System.out.printf("%s, enter your move (row [0-2] and column[0-2])", playerName);

            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Position move = new Position(row, col);

                if (board.isValidMove(move)) {
                    return move;
                }

                System.out.println("Invalid move. Try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter row and column as numbers");
                scanner.nextLine();
            }
        }
    }

}
