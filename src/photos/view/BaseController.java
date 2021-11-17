package photos.view;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

import java.util.List;

import photos.model.Photo;

/**
 * Base class which includes all switching functions
 * @author Apurva Narde
 * @author Max Geiger
 */
public abstract class BaseController {

    /**
     * Quit the Application
     */
    public void quit(final ActionEvent e){
        System.exit(0);
    }

    /**
     * Create a pop up to alert the user of an error
     * @param title title of the pop up
     * @param header header of the pop up
     * @param content content of the pop up
     */
    public void getAlert(final String title, final String header, final String content){
        final Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(null);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Switch to the given scene
     * @param e user presses button
     * @param fxml String path to the fxml file
     */
    private void switchScene(final ActionEvent e, final String fxml) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource(fxml));
        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch to the Admin Scene
     * @param e user presses button
     */
    public void switchToAdmin(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/Admin.fxml");
    }

    /**
     * Switch to the User Scene
     * @param e user presses button
     * @param username username of the user
     */
    public void switchToUser(final ActionEvent e, final String username) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/User.fxml"));
        final Parent root = loader.load();

        final UserController controller = loader.getController();
        controller.setField(username);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch back to the login scene
     * @param e user presses button
     */
    public void switchToLogin(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/Login.fxml");
    }

    /**
     * Admin functions
     * Switch back to create user scene
     * @param e user presses button
     */
    public void switchToCreateUser(final ActionEvent e) throws IOException {
        switchScene(e, "/photos/view/CreateUser.fxml");
    }

    /**
     * User functions
     * Switch to the create album scene
     * @param e user presses button
     * @param username username of the user
     * @param photos photos to add for the album (null when not being called from SearchController.java)
     */
    public void switchToCreateAlbum(final ActionEvent e, final String username, List<Photo> photos) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/CreateAlbum.fxml"));
        final Parent root = loader.load();

        final CreateAlbumController controller = loader.getController();

        controller.setField(username, photos);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * User functions
     * Switch to the rename album scene
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album to rename
     */
    public void switchToRenameAlbum(final ActionEvent e, final String username, final String albumName) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/RenameAlbum.fxml"));
        final Parent root = loader.load();

        final RenameAlbumController controller = loader.getController();

        controller.setField(username, albumName);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * User functions
     * Open the selected album
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album to open
     * @param photo photo to select once switching to the album
     */
    public void switchToAlbum(final ActionEvent e, final String username, final String albumName, final Photo photo) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/Album.fxml"));
        final Parent root = loader.load();

        final AlbumController controller = loader.getController();

        controller.setField(username, albumName, photo);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Album functions
     * Open the add Tag scene
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album in which the photo resides
     * @param path path of the image
     */
    public void switchToAddTag(final ActionEvent e, final String username, final String albumName, final String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/AddTag.fxml"));
        final Parent root = loader.load();

        final AddTagController controller = loader.getController();

        controller.setField(username, albumName, path);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Album functions
     * Open the edit caption scene
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album in which the photo resides
     * @param path path of the image
     * @param caption old caption of the image
     */
    public void switchToEditCaption(final ActionEvent e, final String username, final String albumName, final String path, final String caption) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/EditCaption.fxml"));
        final Parent root = loader.load();

        final EditCaptionController controller = loader.getController();

        controller.setField(username, albumName, path, caption);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Album functions
     * Open the copy photo scene
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album in which the photo resides
     * @param path path of the image
     */
    public void switchToCopyPhoto(final ActionEvent e, final String username, final String albumName, final String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/CopyPhoto.fxml"));
        final Parent root = loader.load();

        final CopyPhotoController controller = loader.getController();

        controller.setField(username, albumName, path);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Album functions
     * Open the move photo scene
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album in which the photo resides
     * @param path path of the image
     */
    public void switchToMovePhoto(final ActionEvent e, final String username, final String albumName, final String path) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/MovePhoto.fxml"));
        final Parent root = loader.load();

        final MovePhotoController controller = loader.getController();

        controller.setField(username, albumName, path);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * User functions
     * Open the Search scene
     * @param e user presses button
     * @param username username of the user
     */
    public void switchToSearch(final ActionEvent e, final String username) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/Search.fxml"));
        final Parent root = loader.load();

        final SearchController controller = loader.getController();

        controller.setField(username);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Album functions
     * Open the copy photo scene
     * @param e user presses button
     * @param username username of the user
     * @param albumName name of the album in which the photo resides
     * @param path path of the image
     */
    public void switchToSlideShow(final ActionEvent e, final String username, final String albumName) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/photos/view/SlideShow.fxml"));
        final Parent root = loader.load();

        final SlideShowController controller = loader.getController();

        controller.setField(username, albumName);

        final Scene scene = new Scene(root);
        final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
