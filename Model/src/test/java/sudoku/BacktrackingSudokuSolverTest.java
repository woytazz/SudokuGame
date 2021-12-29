package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {
    public BacktrackingSudokuSolverTest() {

    }

    @Test
    public void constructorTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        assertNotEquals(null, sudokuSolver);
    }

    @Test
    public void solveTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        SudokuBoard checkBoard = new SudokuBoard(solver);
        solver.solve(board);

        assertNotEquals(board, checkBoard);

    }

}
