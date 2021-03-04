package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {

    private final SudokuSolver sudokuSolver;

    private final List<SudokuField> board = Arrays.asList(new SudokuField[81]);

    public SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;

        for (int i = 0; i < 81; i++) {
            SudokuField zeroField = new SudokuField();
            zeroField.setFieldValue(0);
            board.set(i, zeroField);
        }
    }

    public int get(int x, int y) {
        return board.get(x + 9 * y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        board.get(x + 9 * y).setFieldValue(value);
    }

    private boolean checkBoard() {
        boolean checkRowAndCol = true;
        boolean checkBox = true;

        for (int i = 0; i < 9; i++) {
            checkRowAndCol = getRow(i).verify() && getColumn(i).verify();
            if (!checkRowAndCol) {
                break;
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                checkBox = getBox(i, j).verify();
                if (!checkBox) {
                    break;
                }
            }
            if (!checkBox) {
                break;
            }
        }

        return checkRowAndCol && checkBox;
    }

    public boolean getCheckBoard() {
        return checkBoard();
    }

    public SudokuRow getRow(int y) {
        SudokuRow row = new SudokuRow();
        SudokuField[] fields = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            fields[i] = board.get(i + y * 9);
        }
        row.setPart(fields);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn column = new SudokuColumn();
        SudokuField[] fields = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            fields[i] = board.get(x + i * 9);
        }
        column.setPart(fields);
        return column;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        SudokuField[] fields = new SudokuField[9];
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, counter++) {
                fields[counter] = board.get((i + y) * 9 + j + x);
            }
        }
        box.setPart(fields);
        return box;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }


    // toString, equals, hashCode

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Sudoku Solver", sudokuSolver)
                .append("List Board", board)
                .toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuBoard rhs = (SudokuBoard) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(sudokuSolver, rhs.sudokuSolver)
                .append(board, rhs.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

    // Cloneable


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void convertStringToSudokuBoard(String string) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                set(i, j, Character.getNumericValue(string.charAt(j * 9 + i)));
            }
        }
    }


    public String convertSudokuBoardToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(get(j, i));
            }
        }

        return builder.toString();
    }

}


