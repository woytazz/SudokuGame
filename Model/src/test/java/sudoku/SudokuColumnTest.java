package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuColumnTest extends SudokuPartsTest {

    public SudokuColumnTest() {

    }

    @Test
    public void constructorTest() {
        SudokuColumn columnTest = new SudokuColumn();
        assertNotEquals(null, columnTest);
    }

}
