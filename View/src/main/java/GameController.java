import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import sudoku.*;

import java.io.IOException;
import java.util.ResourceBundle;


public class GameController {
    static Logger logger = Logger.getLogger(GameController.class);

    @FXML
    private GridPane sudokuBoardGrid;
    private SudokuBoard actualSudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard generatedSudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private DoubleSudokuBoard storeBoards = new DoubleSudokuBoard();
    private final ResourceBundle bundle = ResourceBundle.getBundle("language");


    public GameController() {
        this.setLevel();
    }

    @FXML
    public void initialize() {
        fillSudokuBoardGrid();
    }

    private void fillSudokuBoardGrid() {
        if (sudokuBoardGrid.getChildren() != null) {
            sudokuBoardGrid.getChildren().clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(60, 60);
                textField.setAlignment(Pos.CENTER);
                textField.setFont(Font.font(25));

                if (actualSudokuBoard.get(i, j) != 0) {
                    textField.setEditable(false);
                    textField.setText(String.valueOf(actualSudokuBoard.get(i, j)));
                }

                if (actualSudokuBoard.get(i, j) != generatedSudokuBoard.get(i, j)) {
                    textField.setEditable(true);
                }

                if (actualSudokuBoard.get(i, j) == generatedSudokuBoard.get(i, j) && generatedSudokuBoard.get(i, j) != 0) {
                    textField.setStyle("-fx-text-fill: red;");
                }

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (textField.getText().matches("[1-9]") || textField.getText().matches("")) {
                        updateBoard();
                    } else {
                        this.errorWindow();
                        Platform.runLater(textField::clear);
                    }
                });
                sudokuBoardGrid.add(textField, i, j);
            }
        }
        sudokuBoardGrid.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 2;");
    }


    public void setLevel() {
        this.generatedSudokuBoard.solveGame();
        if (MenuController.getLvl() == ChosenLvl.EASY) {
            Level.easy(this.generatedSudokuBoard);

        } else if (MenuController.getLvl() == ChosenLvl.MEDIUM) {
            Level.medium(this.generatedSudokuBoard);
        } else {
            Level.hard(this.generatedSudokuBoard);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                actualSudokuBoard.set(i, j, generatedSudokuBoard.get(i, j));
            }
        }

    }

    private void updateBoard() {
        ObservableList<Node> children = sudokuBoardGrid.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (children.get(i + 9 * j) instanceof TextField) {
                    TextField textField = (TextField) children.get(i + 9 * j);
                    if (NumberUtils.isParsable(textField.getText())) {
                        this.actualSudokuBoard.set(j, i, Integer.parseInt(textField.getText()));
                    }
                }
            }
        }
    }

    @FXML
    private void clickSaveButton() {
        storeBoards.saveBoards(generatedSudokuBoard, actualSudokuBoard);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<DoubleSudokuBoard> file;
        file = factory.getDoubleBoardFileDao("savedGame");
        this.updateBoard();

        try {
            file.write(storeBoards);
            logger.info("Game was saved");
        } catch (RuntimeException e) {
            errorSaveWindow();
        }

    }


    @FXML
    private void clickLoadButton() throws Throwable {
        try {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<DoubleSudokuBoard> file;
            file = factory.getDoubleBoardFileDao("savedGame");
            this.storeBoards = file.read();
            logger.info("Game was loaded");
            this.generatedSudokuBoard = this.storeBoards.getGeneratedBoard();
            this.actualSudokuBoard = this.storeBoards.getActualBoard();
            this.fillSudokuBoardGrid();
        } catch (IOException | RuntimeException e) {
            errorLoadWindow();
        }

    }

    private void errorWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sudoku Error");
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("_validWindow"));
        alert.showAndWait();
    }

    private void winWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("_wonWindow"));
        alert.showAndWait();
    }

    private void lostWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("_lostWindow"));
        alert.showAndWait();
    }

    private void errorLoadWindow() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("_errorLoadWindow"));
        alert.showAndWait();
    }

    private void errorSaveWindow() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString("_errorSaveWindow"));
        alert.showAndWait();
    }


    @FXML
    private void clickCheckButton() {
        if (actualSudokuBoard.getCheckBoard()) {
            logger.info("User won the game!");
            winWindow();
        } else {
            logger.info("User lost the game!");
            lostWindow();
        }

    }

    @FXML
    private void clickLoadDB() {
        try {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<DoubleSudokuBoard> file;
            file = factory.getDataBaseFileDao("DataBase");
            this.storeBoards = file.read();
            logger.info("Game was loaded from DB");
            this.generatedSudokuBoard = this.storeBoards.getGeneratedBoard();
            this.actualSudokuBoard = this.storeBoards.getActualBoard();
            this.fillSudokuBoardGrid();
        } catch (Throwable throwable) {
            logger.error("Cannot load from DB");
            errorLoadWindow();
            throwable.printStackTrace();
        }
    }



    @FXML
    private void clickSaveDB() {
        storeBoards.saveBoards(generatedSudokuBoard, actualSudokuBoard);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<DoubleSudokuBoard> file;

        file = factory.getDataBaseFileDao("DataBase");
        this.updateBoard();

        try {
            file.write(storeBoards);
            logger.info("Game was saved to DB");
        } catch (RuntimeException e) {
            logger.error("Cannot save to DB");
            errorSaveWindow();
        }
    }

}
