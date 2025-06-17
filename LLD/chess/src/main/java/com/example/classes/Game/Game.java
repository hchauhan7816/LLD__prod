package com.example.classes.Game;

import java.util.ArrayList;
import java.util.List;

import com.example.classes.Board.Board;
import com.example.classes.Move.Move;
import com.example.classes.Player.Player;
import com.example.enums.GameStatus;
import com.example.enums.PieceColor;

public class Game {
    private final List<Move> moves;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final Board board;
    private Player currentPlayer;
    private GameStatus gameStatus;

    public Game(Player whitePlayer, Player blackPlayer, Board board) {
        if (whitePlayer.getColor() != PieceColor.WHITE || blackPlayer.getColor() != PieceColor.BLACK) {
            throw new IllegalArgumentException("Invalid player colors");
        }
        
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.moves = new ArrayList<>();
        this.currentPlayer = whitePlayer; // White moves first
        this.gameStatus = GameStatus.ACTIVE;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<Move> getMoves() {
        return new ArrayList<>(moves); // Return defensive copy
    }

    public boolean makeMove(Move move) {
        if (gameStatus != GameStatus.ACTIVE) {
            return false; // Game is not active
        }

        if (move.getPlayer() != currentPlayer) {
            return false; // Not current player's turn
        }

        if (move.execute(board)) {
            moves.add(move);
            switchTurn();
            
            // Check for game end conditions (simplified)
            // In a complete implementation, you'd check for checkmate, stalemate, etc.
            
            return true;
        }
        
        return false;
    }

    public boolean undoLastMove() {
        if (moves.isEmpty()) {
            return false;
        }

        Move lastMove = moves.get(moves.size() - 1);
        if (lastMove.undo(board)) {
            moves.remove(moves.size() - 1);
            switchTurn();
            return true;
        }
        
        return false;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public void resign(Player player) {
        if (player == whitePlayer) {
            gameStatus = GameStatus.BLACK_WIN;
        } else {
            gameStatus = GameStatus.WHITE_WIN;
        }
    }

    public void forfeit(Player player) {
        gameStatus = GameStatus.FORFEIT;
    }

    public int getMoveCount() {
        return moves.size();
    }

    public boolean isPlayerTurn(Player player) {
        return currentPlayer == player && gameStatus == GameStatus.ACTIVE;
    }
}
