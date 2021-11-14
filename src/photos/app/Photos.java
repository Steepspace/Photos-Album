package photos.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

/**
 * Photos application launcher
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Photos extends Application {

    /**
     * Overrides start in the Application
     * @param primaryStage main stage of the photos application
     */
    public void start(Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/photos/view/Login.fxml"));

        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Starts the Application
     * @param args launch args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
