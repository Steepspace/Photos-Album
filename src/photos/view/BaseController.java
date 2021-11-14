package photos.view;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Base class which includes all switching functions
 * @author Apurva Narde
 * @author Max Geiger
 */
public abstract class BaseController {
	/**
	 * Quit the Application
	 */
	public void quit(final ActionEvent e){
		System.exit(0);
	}

	/**
	 * Create a pop up to alert the user of an error
	 * @param title title of the pop up
	 * @param header header of the pop up
	 * @param content content of the pop up
	 */
	public void getAlert(final String title, final String header, final String content){
			final Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(null);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			alert.showAndWait();
	}

	/**
	 * Switch to the given scene
	 * @param e user presses button
	 * @param fxml String path to the fxml file
	 */
    private void switchScene(final ActionEvent e, final String fxml) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource(fxml));
		final Scene scene = new Scene(root);
		final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

	/**
	 * Switch to the Admin Scene
	 * @param e user presses button
	 */
	public void switchToAdmin(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/Admin.fxml");
	}

	/**
	 * Switch to the User Scene
	 * @param e user presses button
	 */
	public void switchToUser(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/User.fxml");
	}

	/**
	 * Switch back to the login scene
	 * @param e user presses button
	 */
	public void switchToLogin(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/Login.fxml");
	}

	/**
	 * Switch back to create user scene
	 * @param e user presses button
	 */
	public void switchToCreateUser(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/CreateUser.fxml");
	}
}
