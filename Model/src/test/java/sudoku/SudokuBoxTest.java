package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoxTest extends SudokuPartsTest {
    public SudokuBoxTest() {

    }

    @Test
    public void constructorTest() {
        SudokuBox boxTest = new SudokuBox();
        assertNotEquals(null, boxTest);
    }

}
