package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.User;

/**
 * Controller class to rename a album
 * @author Apurva Narde
 * @author Max Geiger
 */
public class RenameAlbumController extends BaseController {

    /**
     * name of album that the user wants to switch to
     */
    @FXML TextField albumName;

    /**
     * name of the old Album
     */
    private String oldName;

    /**
     * Current User
     */
    private User user;

    /**
     * Set the user to the provided username
     * @param username name of the user
     * @param album name of the album to rename
     */
    public void setField(String username, String album){
        try {
            user = User.readUser(username);
        } catch (Exception e) {}
        albumName.setText(album);
        oldName = album;
    }

    /**
     * Switch back to the user scene
     * @param e user presses button
     */
    public void switchToUser(final ActionEvent e) throws IOException {
        switchToUser(e, user.getName());
    }

    /**
     * Rename the album based on the provided album name
     * @param e user presses button
     */
    public void renameAlbum(final ActionEvent e) throws IOException{
        final String name = albumName.getText().strip();

        if(name.isEmpty()){
            getAlert("Error", "Name of Album cannot be empty", "Please enter valid album name");
            return;
        }

        if(!user.renameAlbum(oldName, name)){
            getAlert("Error", "Album already exists", "Please enter valid album name");
            return;
        }
        user.writeUser();
        switchToUser(e, user.getName());
    }
}
