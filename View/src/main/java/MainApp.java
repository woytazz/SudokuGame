import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class MainApp extends Application {
    static Logger logger = Logger.getLogger(MainApp.class);

    public static void main(String[] args) {
        logger.info("Program has started");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Locale.setDefault(new Locale("en"));
        ResourceBundle bundle = ResourceBundle.getBundle("language");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"), bundle);

        //StackPane root = new StackPane();
        primaryStage.setTitle(bundle.getString("_windowTitle"));
        primaryStage.setScene(new Scene(root, 1280,720));
        primaryStage.show();
    }
}
