package sudoku;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public Dao<DoubleSudokuBoard> getDoubleBoardFileDao(String filename) {
        return new FileDoubleSudokuBoardDao(filename);
    }

    public Dao<DoubleSudokuBoard> getDataBaseFileDao(String filename) {
        return new JdbcFileDao(filename);
    }
}
