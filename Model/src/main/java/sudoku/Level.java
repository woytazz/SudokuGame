package sudoku;

public class Level {

    public static void easy(final SudokuBoard board) {
        for (int i = 0; i < 15; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);
            if (board.get(x, y) != 0) {
                board.set(x, y, 0);
            } else {
                i--;
            }
        }
    }

    public static void medium(final SudokuBoard board) {
        for (int i = 0; i < 30; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);
            if (board.get(x, y) != 0) {
                board.set(x, y, 0);
            } else {
                i--;
            }
        }
    }

    public static void hard(final SudokuBoard board) {
        for (int i = 0; i < 60; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);
            if (board.get(x, y) != 0) {
                board.set(x, y, 0);
            } else {
                i--;
            }
        }
    }

}
