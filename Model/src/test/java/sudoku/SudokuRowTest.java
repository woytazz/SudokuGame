package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuRowTest extends SudokuPartsTest {
    public SudokuRowTest() {

    }

    @Test
    public void constructorTest() {
        SudokuRow rowTest = new SudokuRow();
        assertNotEquals(null, rowTest);
    }

}
