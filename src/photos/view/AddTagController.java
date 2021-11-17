package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.User;
import photos.model.Photo;

/**
 * Controller class to add a tag to a photo
 * @author Apurva Narde
 * @author Max Geiger
 */
public class AddTagController extends BaseController {

    /**
     * name and value of a tag
     */
    @FXML TextField tagName, tagValue;

    /**
     * Current User
     */
    private User user;

    /**
     * Current Album
     */
    private String albumName;

    /**
     * Current path of image
     */
    private String path;

    /**
     * Set the fields to the provided username
     * @param username name of the user
     * @param albumName album of the user
     * @param path path of the image
     */
    public void setField(String username, String albumName, String path){
        try {
            user = User.readUser(username);
        } catch (Exception e) {}
        this.albumName = albumName;
        this.path = path;
    }

    /**
     * Switch back to the album
     * @param e user presses button
     */
    public void switchToAlbum(final ActionEvent e) throws IOException {
        switchToAlbum(e, user.getName(), albumName, new Photo(path));
    }

    /**
     * Create the album based on the provided album name
     * @param e user presses button
     */
    public void addTag(final ActionEvent e) throws IOException{
        final String name = tagName.getText().strip();
        final String value = tagValue.getText().strip();

        if(name.isEmpty() || value.isEmpty()){
            getAlert("Error", "Name/Value of the tag cannot be empty", "Please enter valid tag name/value");
            return;
        }

        if(!user.addTag(name, value, path)){
            getAlert("Error", "Tag already exists", "Please enter unique tag");
            return;
        }
        user.writeUser();
        switchToAlbum(e, user.getName(), albumName, new Photo(path));
    }
}
