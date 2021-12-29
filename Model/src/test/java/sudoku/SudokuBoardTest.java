package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    public SudokuBoardTest() {

    }

    @Test
    public void constructorTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        assertNotEquals(null, board);
    }

    @Test
    public void SetAndGetTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

       for (int i = 0; i < 9; i++) {
           board.set(i, i,i+1);
       }

       for (int i = 0; i < 9; i++) {
           assertEquals(i+1, board.get(i, i));
       }
    }

    @Test
    public void getCheckBoardTrueTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        assertTrue(board.getCheckBoard());
    }

    @Test
    public void getCheckBoardFalseTest () {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.set(i, j, 1);
            }
        }

        assertFalse(board.getCheckBoard());

    }



    @Test
    public void getCheckBoxFalseTest () {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(0,0, 0);
        board.set(2,2,0);

        assertFalse(board.getCheckBoard());

    }

    @Test
    public void getCheckColumnFalseTest () {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(0,0, 0);
        board.set(0,8,0);

        assertFalse(board.getCheckBoard());

    }

    @Test
    public void toStringTest () {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board_1 = new SudokuBoard(sudokuSolver);

        assertNotEquals(null, board_1.toString());
    }

    @Test
    public void equalsTest () {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board_1 = new SudokuBoard(sudokuSolver);

        assertEquals(false, board_1.equals(null));
        assertEquals(true, board_1.equals(board_1));
        assertEquals(false, board_1.equals(sudokuSolver));
    }

    @Test
    public void cloneTest () throws CloneNotSupportedException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board2 = (SudokuBoard) board.clone();
        Assertions.assertEquals(board.hashCode(), board2.hashCode());


    }


}
