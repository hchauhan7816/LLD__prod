package com.example.classes.TicTacToeGame;

import com.example.classes.Board.Board;
import com.example.classes.BoardGame.BoardGame;
import com.example.classes.Game.GameContext;
import com.example.classes.Player.Player;
import com.example.classes.Player.PlayerStrategy;
import com.example.classes.Postion.Position;
import com.example.enums.PiecesEnum;

public class TicTacToeGame implements BoardGame {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameContext gameContext;

    public TicTacToeGame(PlayerStrategy oStrategy, PlayerStrategy xStrategy, int rows, int cols) {
        board = new Board(rows, cols);
        playerO = new Player(PiecesEnum.O, oStrategy);
        playerX = new Player(PiecesEnum.X, xStrategy);
        currentPlayer = playerO;
        gameContext = new GameContext();
    }

    @Override
    public void play() {
        do {
            board.printBoard();

            Position move = currentPlayer.makeMove(board);

            board.checkGameState(gameContext);
            switchPlayer();
        } while (!gameContext.isGameOver());
    }
}