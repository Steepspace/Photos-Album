package photos.view;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.Admin;

/**
 * Controller class to handle Admin Functions
 * @author Apurva Narde
 * @author Max Geiger
 */
public class AdminController extends BaseController{

	/**
	 * Contains the list of usernames
	 */
	@FXML ListView<String> listView;

	/**
	 * Used to selecte a specific element in the list
	 */
	private SelectionModel<String> model;

	/**
	 * Initializes the list view and populates the usernames
	 */
	public void initialize(){
		//insert items into listview
		listView.setItems(Admin.getUsers());

		model = listView.getSelectionModel();
		//selection handling
		model.selectFirst();
	}

	/**
	 * Deleted the selected user
	 * @param e user presses button
	 */
	public void delete(final ActionEvent e){
		String name = model.getSelectedItem();
		Admin.deleteUser(name);
		initialize();
	}
}
