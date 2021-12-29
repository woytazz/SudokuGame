package sudoku;

import expections.WrongFileActionException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;

public class FileDoubleSudokuBoardDao implements Dao<DoubleSudokuBoard> {
    static Logger logger = Logger.getLogger(FileDoubleSudokuBoardDao.class);
    private final String filename;

    public FileDoubleSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }

    @Override
    public DoubleSudokuBoard read() throws Throwable {
        DoubleSudokuBoard doubleBoard;

        // try read file or throw exception try-with-resources
        try (FileInputStream fileInput = new FileInputStream(filename);
             ObjectInputStream objIS = new ObjectInputStream(fileInput)) {
            doubleBoard = (DoubleSudokuBoard) objIS.readObject();
        } catch (IOException | RuntimeException e) {
            logger.warn("Cannot read the file");
            throw new WrongFileActionException(e);
        }
        return doubleBoard;
    }

    @Override
    public void write(DoubleSudokuBoard obj) {
        try (FileOutputStream fileOutput = new FileOutputStream(filename);
             ObjectOutputStream objOS = new ObjectOutputStream(fileOutput)) {
             objOS.writeObject(obj);

        } catch (NullPointerException | IOException e) {
            logger.warn("Cannot save the file");
            throw new RuntimeException(e);
        }
    }
}
