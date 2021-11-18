package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.FileInputStream;
import java.io.IOException;

import photos.model.User;
import photos.model.Album;
import photos.model.Photo;

/**
 * Controller class to handle slide show
 * @author Apurva Narde
 * @author Max Geiger
 */
public class SlideShowController extends BaseController{

	/**
	 * button to proceed to next image
	 */
	@FXML Button nextB;
	
	/**
	 * button to proceed to next image
	 */
	@FXML Button prevB;
	
	/**
	 * ImageView displaying the image
	 */
	@FXML ImageView imageview;
	
	/**
	 * ArrayList of photos being displayed
	 */
	private ObservableList<Photo> album;

	/**
	 * current index in the album
	 */
	private int index;
	
	/**
	 * Context variable to contain current Album
	 */
	private Album a;

	/**
	 * Context variable to contain current User
	 */
	private User user;
	
    /**
     * Set the fields of the user state upon entry
     * @param username name of the user
     * @param albumname name of the album
     */
	public void setField(String username,String albumname){
		try {
			this.user = User.readUser(username);
		}catch(Exception e) {}
		this.a = user.getAlbum(albumname);
		album = a.getPhotos();//album = a.getPhotos().listIterator();
		index = 0;
		try {
			Image im = new Image(new FileInputStream(album.get(index).getPath()));
			imageview.setImage(im);
		}catch(Exception ex) {}
		setButtons();
	}
	
	/**
	 * checks if next or previous images are available and sets buttons to be deactivated if not
	 */
	private void setButtons(){
		prevB.setDisable(index == 0);
		nextB.setDisable(index == album.size()-1);
	}

	/**
	 * switch to previous image
	 * @param e user presses button
	 */
	public void previousImage(final ActionEvent e) {
		try {
			Image im = new Image(new FileInputStream(album.get(--index).getPath()));
			imageview.setImage(im);
		}catch(Exception ex) {}
		setButtons();
	}
	
	/**
	 * switch to next image
	 * @param e user presses button
	 */
	public void nextImage(final ActionEvent e) {
		try {
			Image im = new Image(new FileInputStream(album.get(++index).getPath()));
			imageview.setImage(im);
		}catch(Exception ex) {}
		setButtons();
	}

	/**
	 * Switch to the selected album
	 * @param e user presses button
     * @throws IOException when path to file is incorrect
	 */
	public void switchToAlbum(final ActionEvent e) throws IOException {
		switchToAlbum(e, user.getName(), a.getName(), null);
	}
}
