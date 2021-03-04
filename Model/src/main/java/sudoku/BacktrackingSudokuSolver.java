package sudoku;

import java.io.Serializable;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    @Override
    public void solve(SudokuBoard board) {
            fill(board);
    }


    private static boolean canWrite(SudokuBoard board, int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (board.get(row, i) == value) {
                return false;
            }
            if (board.get(i, col) == value) {
                return false;
            }
        }


        int miniRow = row - row % 3;
        int miniCol = col - col % 3;

        for (int i = miniRow; i < miniRow + 3; i++) {
            for (int j = miniCol; j < miniCol + 3; j++) {
                if (board.get(i, j) == value) {
                    return false;
                }
            }
        }


        return true;
    }

    private int[] randomSudoku() {
        Random random = new Random();
        int[] randomBoard = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 9; i++) {
            int randomPlace = random.nextInt(9);
            int temp = randomBoard[i];
            randomBoard[i] = randomBoard[randomPlace];
            randomBoard[randomPlace] = temp;
        }
        return randomBoard;
    }


    private boolean fill(SudokuBoard board) {
        int[] randomBoard = randomSudoku();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == 0) {
                    for (int x = 0; x < 9; x++) {
                        if (canWrite(board, row, col, randomBoard[x])) {
                            board.set(row, col, randomBoard[x]);
                            if (fill(board)) {
                                return true;
                            } else {
                                board.set(row, col, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}
