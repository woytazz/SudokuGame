package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {
    public SudokuFieldTest () {

    }

    @Test
    public void equalsTest () {
        SudokuField field_1 = new SudokuField();
        SudokuField field_2 = new SudokuField();
        SudokuSolver solver = new BacktrackingSudokuSolver();

        assertFalse(field_1.equals(null));
        assertTrue(field_1.equals(field_1));
        assertFalse(field_1.equals(field_2));
        assertFalse(field_1.equals(solver));
    }

    @Test
    public void cloneTest () throws CloneNotSupportedException {
        SudokuField field = new SudokuField();
        SudokuField field1 = (SudokuField) field.clone();
        Assertions.assertEquals(field.hashCode(), field1.hashCode());


    }

    @Test
    public void compareTest () {
        SudokuField field = new SudokuField();
        SudokuField field1 = new SudokuField();
        field.setFieldValue(1);
        field.setFieldValue(2);
        Assertions.assertEquals(1, field.compareTo(field1));


    }
}
