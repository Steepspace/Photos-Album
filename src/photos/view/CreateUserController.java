/*
 * @author Apurva Narde
 * @author Max Geiger
 */

package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.Admin;

/**
 * Controller class to create a user
 * @author Apurva Narde
 * @author Max Geiger
 */
public class CreateUserController extends BaseController {

    /**
     * Username of the user
     */
	@FXML TextField username;

	/**
	 * Create the user based on the provided username
	 * @param e user presses button
	 */
	public void addUser(final ActionEvent e) throws IOException{
		final String name = username.getText().strip();

		if(name.isEmpty()){
			getAlert("Error", "Username cannot be empty", "Please enter valid username");
			return;
		}

		if(!Admin.createUser(name)){
			getAlert("Error", "Username already exists", "Please enter valid username");
			return;
		}
		switchToAdmin(e);
	}
}
