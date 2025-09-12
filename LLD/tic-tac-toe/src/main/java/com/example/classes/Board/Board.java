package com.example.classes.Board;

import com.example.classes.Game.GameContext;
import com.example.classes.Postion.Position;
import com.example.enums.PiecesEnum;

public class Board {

    private final int rows;
    private final int columns;
    private PiecesEnum[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = PiecesEnum.EMPTY;
            }
        }
    }

    public boolean isValidMove(Position pos) {
        return pos.row >= 0 && pos.row < rows && pos.col >= 0 && pos.col < columns
                && grid[pos.row][pos.col] == PiecesEnum.EMPTY;
    }

    public boolean makeMove(Position pos, PiecesEnum piece) {
        grid[pos.row][pos.col] = piece;
    }

    public void checkGameState(GameContext context) {
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] != PiecesEnum.EMPTY && isWinningLine(grid[i])) {
                context.next();
                return;
            }
        }

        for (int i = 0; i < columns; i++) {
            PiecesEnum[] column = new PiecesEnum[rows];

            for (int j = 0; j < rows; j++) {
                column[j] = grid[j][i];
            }

            if (column[0] != PiecesEnum.EMPTY && isWinningLine(column)) {
                context.next();
                return;
            }
        }
    }

    private boolean isWinningLine(PiecesEnum[] row) {
        // ! TODO
        return true;
    }

}
