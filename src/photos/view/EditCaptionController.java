package photos.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

import photos.model.User;
import photos.model.Photo;

/**
 * Controller class to edit caption of a photo
 * @author Apurva Narde
 * @author Max Geiger
 */
public class EditCaptionController extends BaseController {

    /**
     * name and value of a tag
     */
    @FXML TextField captionText;

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
     * Set the user to the provided username
     * @param username name of the user
     * @param albumName album of the user
     * @param path path of the image
     * @param caption old caption of the image
     */
    public void setField(String username, String albumName, String path, String caption){
        try {
            user = User.readUser(username);
        } catch (Exception e) {}
        captionText.setText(caption);

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
     * Set the caption of the photo
     * @param e user presses button
     */
    public void editCaption(final ActionEvent e) throws IOException{
        final String caption = captionText.getText().strip();

        user.captionPhoto(caption, path);
        user.writeUser();
        switchToAlbum(e, user.getName(), albumName, new Photo(path));
    }
}
