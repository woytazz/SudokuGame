package sudoku;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {
    public FileSudokuBoardDaoTest() {

    }

    @Test
    public void writeReadTest() throws Throwable {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard board_1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board_2;
        Dao<SudokuBoard> file;

        file = factory.getFileDao("test");
        file.write(board_1);
        board_2 = file.read();
        assertEquals(board_1.hashCode(), board_2.hashCode());

    }



    @Test
    public void writeRuntimeExceptionTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> file;
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        file = factory.getFileDao("/...");

        assertThrows(RuntimeException.class, () -> {
            file.write(board);
        });

    }


}
