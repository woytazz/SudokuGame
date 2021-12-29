package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuPartsTest {
    public SudokuPartsTest() {

    }

    @Test
    public void constructorTest() {
        SudokuParts testObj = new SudokuParts();
        Assertions.assertNotEquals(null,testObj);
    }

    @Test
    public void getPartAndSetPartTest() {
        SudokuParts testObj = new SudokuParts();
        SudokuField[] fields = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(i);
        }

        testObj.setPart(fields);

        for (int i = 0; i < 9; i++) {
            Assertions.assertEquals(testObj.getPart()[i].getFieldValue(), i);
        }

    }

    @Test
    public void verifyTest() {
        SudokuParts testObj = new SudokuParts();
        SudokuField[] fields = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(i+1);
        }

        // TrueTest
        testObj.setPart(fields);
        Assertions.assertTrue(testObj.verify());

        // FalseTest
        for (int i = 0; i < 9; i++) {
            testObj.getPart()[i].setFieldValue(0);
        }
        Assertions.assertFalse(testObj.verify());
    }

    @Test
    public void toStringTest () {
        SudokuParts parts = new SudokuParts();

        Assertions.assertNotEquals(null, parts.toString());
    }

    @Test
    public void equalsTest () {
        SudokuParts parts = new SudokuParts();
        SudokuParts parts2 = new SudokuParts();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

        Assertions.assertFalse(parts.equals(null));
        Assertions.assertTrue(parts.equals(parts));
        Assertions.assertFalse(parts.equals(sudokuSolver));
        Assertions.assertFalse(parts.equals(parts2));
    }

    @Test
    public void cloneTest () throws CloneNotSupportedException {
        SudokuParts parts = new SudokuParts();
        SudokuParts parts2 = (SudokuParts) parts.clone();
        Assertions.assertEquals(parts.hashCode(), parts2.hashCode());


    }
}
