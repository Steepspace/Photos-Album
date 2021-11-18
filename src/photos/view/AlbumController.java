package photos.view;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.ListCell;
import javafx.scene.Node;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.util.List;

import photos.model.User;
import photos.model.Album;
import photos.model.Photo;

/**
 * Controller class to handle Album Functions
 * @author Apurva Narde
 * @author Max Geiger
 */
public class AlbumController extends BaseController{

    /**
     * Contains the list of images for the album
     */
    @FXML ListView<Photo> imageList;

    /**
     * Contains the list of tags for the image
     */
    @FXML ListView<List<String>> tagList;

    /**
     * Used to selecte a specific element in the image list
     */
    private SelectionModel<Photo> modelImage;

    /**
     * Used to selecte a specific element in the tag list
     */
    private SelectionModel<List<String>> modelTag;

    /**
     * Text to display key properties of image and album.
     */
    @FXML Text captionText, dateText, albumText;

    /**
     * Selected Image that is being displayed.
     */
    @FXML ImageView displayImage;

    /**
     * Current user
     */
    private User user;

    /**
     * Current album
     */
    private Album album;

    /**
     * Current photo to be selected
     */
    private Photo albumPhoto;

    /**
     * Set the fields of the album state upon entry
     * @param username name of the user
     * @param albumName name of the album
     * @param photo photo to be selected
     */
    public void setField(String username, String albumName, Photo photo) {
        try {
            user = User.readUser(username);
        } catch (Exception e) {}

        album = user.getAlbum(albumName);
        albumPhoto = photo;

        albumText.setText("Album: " + albumName);
        refresh();
    }

    /**
     * Initializes the tag list and populates images in the albums
     */
    private void refresh() {
        //insert photos into listview
        imageList.setItems(album.getPhotos());

        imageList.setCellFactory(param -> new ListCell<Photo>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Photo photo, boolean empty) {
                super.updateItem(photo, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        imageView.setImage(new Image(new FileInputStream(photo.getPath())));
                    } catch (Exception e) {
                    }
                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);
                    setText("Caption: " + photo.getCaption());
                    setGraphic(imageView);
                }
            }
        });

        modelImage = imageList.getSelectionModel();
        modelImage.selectedIndexProperty().addListener( (obs, oldVal, newVal) -> imageDetail());
        if(albumPhoto == null) modelImage.selectFirst();
        else modelImage.select(albumPhoto);
    }

    /**
     * Display the selected image in a separate display area
     * display the tags of the image along with caption and date
     */
    private void imageDetail(){
        // Display selected image in display area
        final Photo photo = modelImage.getSelectedItem();

        if(photo == null){
            captionText.setText("Caption: ");
            dateText.setText("Date: ");
            displayImage.setImage(null);
            tagList.setItems(null);
            return;
        }

        try {
            displayImage.setImage(new Image(new FileInputStream(photo.getPath())));
        } catch (Exception e) {
        }
        displayImage.setFitWidth(350);
        displayImage.setPreserveRatio(true);

        // insert tags into listview
        tagList.setItems(photo.getTagList());

        modelTag = tagList.getSelectionModel();
        // selection handling
        modelTag.selectFirst();

        captionText.setText("Caption: " + photo.getCaption());
        dateText.setText("Date: " + photo);
    }

    /**
     * Add a photo to the album from a file chooser menu
     * @param e user presses button
     */
    public void switchToAddPhoto(final ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        List<File> files = fileChooser.showOpenMultipleDialog((Stage)((Node)e.getSource()).getScene().getWindow());
        if(files != null){
            for (File file : files) {
                if (file == null || !user.addPhoto(album.getName(), file.getPath()))
                    getAlert("Error", "Photo already exists in this album", "Please choose a different photo");
            }
            user.writeUser();
            albumPhoto = modelImage.getSelectedItem();
            refresh();
        }
    }

    /**
     * Deleted selected photo from album
     * @param e user presses button
     */
    public void deletePhoto(final ActionEvent e) {
        Photo photo = modelImage.getSelectedItem();
        if(photo != null){
            album.removePhoto(photo.getPath());
            albumPhoto = null;
            user.writeUser();
            refresh();
        }
    }

    /**
     * Go back to the user scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToUser(final ActionEvent e) throws IOException {
        switchToUser(e, user.getName());
    }

    /**
     * Switch to edit caption scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToEditCaption(final ActionEvent e) throws IOException {
        Photo photo = modelImage.getSelectedItem();
        if(photo != null) switchToEditCaption(e, user.getName(), album.getName(), photo.getPath(), photo.getCaption());
    }

    /**
     * Switch to copy photo scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToCopyPhoto(final ActionEvent e) throws IOException {
        Photo photo = modelImage.getSelectedItem();
        if(photo != null) switchToCopyPhoto(e, user.getName(), album.getName(), photo.getPath());
    }

    /**
     * Switch to move photo scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToMovePhoto(final ActionEvent e) throws IOException {
        Photo photo = modelImage.getSelectedItem();
        if(photo != null) switchToMovePhoto(e, user.getName(), album.getName(), photo.getPath());
    }

    /**
     * Switch to slide show scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToSlideShow(final ActionEvent e) throws IOException {
    	if(album.isEmpty()) {
    		getAlert("Error", "Album empty","Please add a photo to this album, then try again.");
    		return;
    	}
    	switchToSlideShow(e, user.getName(),album.getName());
    }

    /**
     * Switch to add tag scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToAddTag(final ActionEvent e) throws IOException{
        Photo photo = modelImage.getSelectedItem();
        if(photo != null) switchToAddTag(e, user.getName(), album.getName(), photo.getPath());
    }

    /**
     * Delete the selected tag
     * @param e user presses button
     */
    public void deleteTag(final ActionEvent e){
        if(modelTag != null){
            List<String> tag = modelTag.getSelectedItem();
            Photo photo = modelImage.getSelectedItem();
            if (tag != null) {
                photo.removeTag(tag.get(0), tag.get(1));
                user.writeUser();
                // insert tags into listview
                tagList.setItems(photo.getTagList());
            }
        }
    }
}
