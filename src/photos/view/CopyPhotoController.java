package photos.view;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.User;
import photos.model.Album;
import photos.model.Photo;

/**
 * Controller class to copy photo from one album to another
 * @author Apurva Narde
 * @author Max Geiger
 */
public class CopyPhotoController extends BaseController{

    /**
     * Contains the list of albums
     */
    @FXML ListView<Album> albumList;

    /**
     * Current user
     */
    private User user;

    /**
     * Current Album
     */
    private Album album;

    /**
     * Current path of image
     */
    private String path;

    /**
     * Used to selecte a specific element in the list
     */
    private SelectionModel<Album> model;

    /**
     * Set the fields to the provided values
     * @param username name of the user
     * @param albumName album of the user
     * @param path path of the image
     */
    public void setField(String username, String albumName, String path){
        try {
            user = User.readUser(username);
        } catch (Exception e) {}

        album = user.getAlbum(albumName);
        this.path = path;

        // insert items into listview
        albumList.setItems(user.getAlbums());

        model = albumList.getSelectionModel();
        // selection handling
        model.select(album);
    }

    /**
     * Switch back to the album
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToAlbum(final ActionEvent e) throws IOException {
        switchToAlbum(e, user.getName(), album.getName(), new Photo(path));
    }

    /**
     * Copy the photo to the selected album
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void copyPhoto(final ActionEvent e) throws IOException{
        if(!user.copyPhoto(album.getName(), model.getSelectedItem().getName(), path)){
            getAlert("Error", "Image already exists in this album", "Please choose different album");
            return;
        }
        user.writeUser();
        switchToAlbum(e, user.getName(), album.getName(), new Photo(path));
    }
}
