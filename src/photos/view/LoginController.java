package photos.view;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

import photos.model.Admin;
import photos.model.User;


/**
 * Controller class to handle Login
 * @author Apurva Narde
 * @author Max Geiger
 */
public class LoginController extends BaseController{

    /**
     * Username of the user
     */
	@FXML TextField username;

	/**
	 * Validate the user login
	 * @param e user presses button
	 */
	public void login(final ActionEvent e) throws IOException{
		final String uname = username.getText().strip();

		if(uname.equals("admin")) switchToAdmin(e);
		else if(Admin.getUsers().contains(uname)) System.out.println("Login User: " + uname);
		else getAlert("Error", "User does NOT exist", "Please enter a valid username.");
	}
}
