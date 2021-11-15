package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.User;

/**
 * Controller class to create a album
 * @author Apurva Narde
 * @author Max Geiger
 */
public class CreateAlbumController extends BaseController {

    /**
     * name of album
     */
	@FXML TextField albumName;

    /**
     * Current User
     */
	User user;

    /**
     * Set the user to the provided username
     * @param username name of the user
     */
	public void setField(String username){
		try {
			user = User.readUser(username);
		} catch (Exception e) {}
	}

    /**
     * Switch back to the user scene
	 * @param e user presses button
     */
	public void switchToUser(final ActionEvent e) throws IOException {
		switchToUser(e, user.getName());
	}

	/**
	 * Create the album based on the provided album name
	 * @param e user presses button
	 */
	public void addAlbum(final ActionEvent e) throws IOException{
		final String name = albumName.getText().strip();

		if(name.isEmpty()){
			getAlert("Error", "Name of Album cannot be empty", "Please enter valid album name");
			return;
		}

		if(!user.addAlbum(name)){
			getAlert("Error", "Album already exists", "Please enter valid album name");
			return;
		}
		user.writeUser();
		switchToUser(e, user.getName());
	}
}
