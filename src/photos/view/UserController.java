package photos.view;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.User;
import photos.model.Album;

/**
 * Controller class to handle User Functions
 * @author Apurva Narde
 * @author Max Geiger
 */
public class UserController extends BaseController{

	/**
	 * Contains the list of albums
	 */
	@FXML ListView<Album> listView;

	/**
	 * Label to display the username
	 */
	@FXML Label userText;

	/**
	 * Current user
	 */
	private User user;

	/**
	 * Used to selecte a specific element in the list
	 */
	private SelectionModel<Album> model;

	public void setField(String username){
		try {
			user = User.readUser(username);
		} catch (Exception e) {}

		userText.setText("User: " + username);
		refresh();
	}

	/**
	 * Initializes the list view and populates the list of albums
	 */
	private void refresh() {
		// insert items into listview
		listView.setItems(user.getAlbums());

		model = listView.getSelectionModel();
		// selection handling
		model.selectFirst();
	}

	/**
	 * Deleted the selected album
	 * @param e user presses button
	 */
	public void delete(final ActionEvent e) {
		Album album = model.getSelectedItem();
		user.removeAlbum(album.getName());
		user.writeUser();
		refresh();
	}

	/**
	 * Switch to the selected album
	 * @param e user presses button
	 */
	public void switchToAlbum(final ActionEvent e) throws IOException {
		final Album album = model.getSelectedItem();
		if (album == null)
			return;
		System.out.println("Open: " + album);
	}

	/**
	 * Switch to create album
	 * @param e user presses button
	 */
	public void switchToCreateAlbum(final ActionEvent e) throws IOException {
		switchToCreateAlbum(e, user.getName());
	}

	/**
	 * Edit the selected album
	 * @param e user presses button
	 */
	public void switchToRenameAlbum(final ActionEvent e) throws IOException {
		final Album album = model.getSelectedItem();
		if (album == null) return;
		switchToRenameAlbum(e, user.getName(), album.getName());
	}
}
