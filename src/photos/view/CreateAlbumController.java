package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import photos.model.User;
import photos.model.Photo;

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
    private User user;

    /**
     * Collection of photos
     */
    private ArrayList<Photo> photos;

    /**
     * Set the user to the provided username
     * @param username name of the user
     * @param photos list of photos to add if the method is being called as a result of search
     */
    public void setField(String username, List<Photo> photos){
        try {
            user = User.readUser(username);
        } catch (Exception e) {}
        this.photos = (photos != null) ? new ArrayList<Photo>(photos) : null;
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

        if((photos != null && !user.addAlbum(name, photos)) || (photos == null && !user.addAlbum(name))) {
            getAlert("Error", "Album already exists", "Please enter valid album name");
            return;
        }
        user.writeUser();
        switchToUser(e, user.getName());
    }
}
