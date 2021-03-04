package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
    public LevelTest() {

    }
    @Test
    public void easyTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        Level.easy(board);
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if(board.get(i,j)==0) {
                    sum++;
                }
            }
        }
        assertEquals(15, sum);
    }

    @Test
    public void mediumTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        Level lvl = new Level();
        lvl.medium(board);
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if(board.get(i,j)==0) {
                    sum++;
                }
            }
        }
        assertEquals(30, sum);
    }

    @Test
    public void hardTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        Level.hard(board);
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if(board.get(i,j)==0) {
                    sum++;
                }
            }
        }
        assertEquals(60, sum);
    }
}
