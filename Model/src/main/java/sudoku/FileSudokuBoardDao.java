package sudoku;

import expections.WrongFileActionException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    static Logger logger = Logger.getLogger(FileSudokuBoardDao.class);

    private final String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }

    @Override
    public SudokuBoard read() throws Throwable {
        SudokuBoard board;

        // try read file or throw exception try-with-resources
        try (FileInputStream fileInput = new FileInputStream(filename);
             ObjectInputStream objIS = new ObjectInputStream(fileInput)) {
             board = (SudokuBoard) objIS.readObject();
        } catch (IOException | RuntimeException e) {
            logger.warn("Cannot read the file");
            throw new WrongFileActionException(e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOutput = new FileOutputStream(filename);
             ObjectOutputStream objOS = new ObjectOutputStream(fileOutput)) {
             objOS.writeObject(obj);

        } catch (NullPointerException | IOException e) {
            logger.warn("Cannot save the file");
            throw new RuntimeException(e);
        }
    }

}
