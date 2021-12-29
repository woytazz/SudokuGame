package sudoku;

import java.io.Serializable;

public class DoubleSudokuBoard implements Serializable {
    private SudokuBoard generatedBoard = null;
    private SudokuBoard actualBoard = null;

    public void saveBoards(SudokuBoard generatedBoard, SudokuBoard actualBoard) {
        this.generatedBoard = generatedBoard;
        this.actualBoard = actualBoard;
    }

    public SudokuBoard getGeneratedBoard() {
        return generatedBoard;
    }

    public SudokuBoard getActualBoard() {
        return actualBoard;
    }
}
