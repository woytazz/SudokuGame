package sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcFileDao implements Dao<DoubleSudokuBoard> {

    public static final String DB_NAME = "SudokuBoard";
    private final String filename;

    public JdbcFileDao(String filename) {
        this.filename = filename;
    }


    private Connection makeConnection(String jdbcUrl) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public DoubleSudokuBoard read() {
        DoubleSudokuBoard doubleSudokuBoard = new DoubleSudokuBoard();
        doubleSudokuBoard.saveBoards(new SudokuBoard(new BacktrackingSudokuSolver()),
                new SudokuBoard(new BacktrackingSudokuSolver()));
        String jdbcUrl = "jdbc:sqlite:" + filename;
        Connection connection = makeConnection(jdbcUrl);
        String receivedDataGeneratedBoard;
        String receivedDataActualBoard;
        ResultSet resultSet;
        String selectData = "select tableName, fields, isEditable from "
                + DB_NAME + " where tableName=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, filename);
            resultSet = preparedStatement.executeQuery();
            receivedDataGeneratedBoard = resultSet.getString(2);
            receivedDataActualBoard = resultSet.getString(3);

            doubleSudokuBoard.getGeneratedBoard()
                    .convertStringToSudokuBoard(receivedDataGeneratedBoard);
            doubleSudokuBoard.getActualBoard()
                    .convertStringToSudokuBoard(receivedDataActualBoard);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doubleSudokuBoard;
    }

    @Override
    public void write(DoubleSudokuBoard obj) {
        String jdbcUrl = "jdbc:sqlite:./" + filename;
        Connection connection = makeConnection(jdbcUrl);

        String createTable = "create table " + DB_NAME
                + "(tableName varchar(20) primary key not null, "
                + "fields varchar(81),isEditable varchar(81))";

        String insertData = "insert into SudokuBoard(tableName,fields,isEditable) values (?,?,?)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            preparedStatement.setString(1, filename);
            preparedStatement.setString(2, obj.getGeneratedBoard().convertSudokuBoardToString());
            preparedStatement.setString(3, obj.getActualBoard().convertSudokuBoardToString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
