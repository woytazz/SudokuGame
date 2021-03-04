import exceptions.CannotStartGameException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class MenuController {
    static Logger logger = Logger.getLogger(MenuController.class);

    private static ChosenLvl lvl;
    private final ResourceBundle bundle = ResourceBundle.getBundle("language");
    private final Authors authors = new Authors();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    Label menuLabel;

    @FXML
    Button easyButton;

    @FXML
    Button mediumButton;

    @FXML
    Button hardButton;

    @FXML
    ChoiceBox<String> languageBox = new ChoiceBox<>();

    @FXML
    Button authorsButton;

    @FXML
    public void initialize() {
        authorsButton.setText(bundle.getString("_btnAuthors"));
        menuLabel.setText(bundle.getString("_choiceLvl"));
        easyButton.setText(bundle.getString("_lvlEasy"));
        mediumButton.setText(bundle.getString("_lvlMedium"));
        hardButton.setText(bundle.getString("_lvlHard"));
        languageBox.setValue(bundle.getString("_comboLang0"));
        languageBox.getItems().addAll(bundle.getString("_comboLang1"), bundle.getString("_comboLang2"));

        languageBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches(bundle.getString("_comboLang1"))) {
                Locale.setDefault(new Locale("en"));
            } else if (newValue.matches(bundle.getString("_comboLang2"))) {
                Locale.setDefault(new Locale("pl"));
            }
            refreshMenu();
        });
    }

    @FXML
    public void clickEasyButton() throws CannotStartGameException {
        lvl = ChosenLvl.EASY;
        startLvl();
    }

    @FXML
    public void clickMediumButton() throws CannotStartGameException {
        lvl = ChosenLvl.MEDIUM;
        startLvl();
    }

    @FXML
    public void clickHardButton() throws CannotStartGameException {
        lvl = ChosenLvl.HARD;
        startLvl();
    }

    public static ChosenLvl getLvl() {
        return lvl;
    }

    private void refreshMenu() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"), bundle);
        } catch (IOException e) {
            logger.warn("Cannot refresh Menu");
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void clickAuthorsButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("_btnAuthors"));
        alert.setHeaderText(null);
        alert.setContentText(authors.getObject("1.") + "\n" + authors.getObject("2."));
        alert.showAndWait();
    }

    private void startLvl() throws CannotStartGameException {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Game.fxml"), bundle);
        } catch (IOException e) {
            logger.warn("Cannot load a game");
            e.printStackTrace();
            throw new CannotStartGameException("EXCEPTION: Cannot start the game!");
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
