package photos.view;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.ListCell;
import javafx.scene.Node;

import javafx.collections.ObservableList;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.FileInputStream;

import java.util.List;
import java.util.Calendar;

import java.text.SimpleDateFormat;

import photos.model.User;
import photos.model.Album;
import photos.model.Photo;


/**
 * Controller class to handle Search functions
 * @author Apurva Narde
 * @author Max Geiger
 */
public class SearchController extends BaseController{

    /**
     * Contains the list of images for the album
     */
    @FXML ListView<Photo> imageList;

    /**
     * Either search by tag query or start date and end date which are stored here
     */
    @FXML TextField tagText, startDate, endDate;

    /**
     * Current user
     */
    private User user;

    /**
     * Collection of photos
     */
    private ObservableList<Photo> results;

    /**
     * Set the fields of the user state upon entry
     * @param username name of the user
     */
    public void setField(String username) {
        try {
            user = User.readUser(username);
        } catch (Exception e) {}
    }

    /**
     * populates images in the albums from the search results
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void search(final ActionEvent e) throws IOException {

        final String byTag = tagText.getText().strip();
        final String start = startDate.getText().strip();
        final String end = endDate.getText().strip();

        if(!byTag.isEmpty() && start.isEmpty() && end.isEmpty()) results = user.search(byTag);
        else if(byTag.isEmpty() && !start.isEmpty() && !end.isEmpty()){
            try {
                results = user.search(start, end);
            } catch (Exception exception) {
                getAlert("Error", "Date format is not correct", "Date format should be mm/dd/yyyy");
                return;
            }
        }
        else{
            getAlert("Error", "Cannot search by both tag and date", "Please search by EITHER tag or date");
            return;
        }

        //insert photos into listview
        imageList.setItems(results);

        if(results.isEmpty()) return;

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
                    setGraphic(imageView);
                }
            }
        });
    }

    /**
     * Switch to create album
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToCreateAlbum(final ActionEvent e) throws IOException {
        if(results != null && !results.isEmpty()) switchToCreateAlbum(e, user.getName(), results);
    }

    /**
     * Go back to the user scene
     * @param e user presses button
     * @throws IOException when path to file is incorrect
     */
    public void switchToUser(final ActionEvent e) throws IOException {
        switchToUser(e, user.getName());
    }
}
