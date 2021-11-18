package photos.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * General User Class.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class User implements Serializable {

    /**
     * Username of user
     */
    private String name;

    /**
     * Collection of albums.
     */
    private ArrayList<Album> albums;

    /**
     * Serialization ID
     */
    static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param name Username of user
     */
    public User(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }

    /**
     * Get Username
     * @return username of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Rename an existing album
     * @param from album to rename
     * @param to new album name
     * @return false if the new name already exists or if the user does not contain the album and true otherwise
     */
    public boolean renameAlbum(String from, String to){
        if(from.equals(to)) return true;
        if(this.contains(from) && !this.contains(to)){
            this.getAlbum(from).setName(to);
            return true;
        }
        return false;
    }

    /**
     * Get albums
     * @return Collection of albums.
     */
    public ObservableList<Album> getAlbums(){
        return FXCollections.observableArrayList(this.albums);
    }

    /**
     * Check if the collection contains the specific album
     * @param albumName album name
     * @return true if the album is in the user collection and false otherwise
     */
    public boolean contains(String albumName){
        return albums.contains(new Album(albumName));
    }

    /**
     * Get the album instance
     * @param albumName album name
     * @return album instance if user has the album otherwise null
     */
    public Album getAlbum(String albumName){
        Album album = new Album(albumName);
        int index = albums.indexOf(album);
        if(index == -1) return null;
        return albums.get(index);
    }

    /**
     * Add photo to an album
     * @param albumName album name
     * @param path photo path
     * @return false if the album already contains the photo and true otherwise
     */
    public boolean addPhoto(String albumName, String path){
        if(!this.contains(albumName)) return false;
        // check to see if the photo exists in any other album first
        for(Album album : albums){
            if(album.contains(path)) return this.getAlbum(albumName).addPhoto(album.getPhoto(path));
        }
        return this.getAlbum(albumName).addPhoto(new Photo(path));
    }

    /**
     * Remove photo from an album
     * @param albumName album name
     * @param path photo absolute path
     */
    public void removePhoto(String albumName, String path){
        this.getAlbum(albumName).removePhoto(path);
    }

    /**
     * Add a key value tag to the image
     * @param name tag name
     * @param value tag value
     * @param path photo absolute path
     * @return false if a duplicate tag exists and true otherwise
     */
    public boolean addTag(String name, String value, String path) {
        for (Album album : albums) {
            if(album.contains(path)) return album.getPhoto(path).addTag(name, value);
        }
        return false;
    }

    /**
     * Remove a tag from an image
     * @param name tag name
     * @param value tag value
     * @param path photo absolute path
     */
    public void removeTag(String name, String value, String path) {
        for (Album album : albums) {
            if(album.contains(path)){
                album.getPhoto(path).removeTag(name, value);
                break;
            }
        }
    }

    /**
     * Set caption of the image
     * @param caption caption of image
     * @param path photo absolute path
     */
    public void captionPhoto(String caption, String path) {
        for (Album album : albums) {
            if(album.contains(path)){
                album.getPhoto(path).setCaption(caption);
                break;
            }
        }
    }

    /**
     * Add album to the collection
     * @param albumName album name
     * @return false if the user already contains the album and true otherwise
     */
    public boolean addAlbum(String albumName){
        if(this.contains(albumName)) return false;
        return this.albums.add(new Album(albumName));
    }

    /**
     * Add album to the collection
     * to add results from search to the user's album
     * @param albumName album name
     * @param photos collection of photos
     * @return false if the user already contains the album and true otherwise
     */
    public boolean addAlbum(String albumName, ArrayList<Photo> photos){
        if(this.contains(albumName)) return false;
        return this.albums.add(new Album(albumName, photos));
    }

    /**
     * Remove album from the user collection
     * @param albumName album name
     */
    public void removeAlbum(String albumName){
        this.albums.remove(this.getAlbum(albumName));
    }

    /**
     * Find all photos that fit into the date range
     * @param begin start date
     * @param end end date
     * @return null if no results found and collection of photos if there are valid matches
     * @throws ParseException if the dates are in incorrect format
     */
    public ObservableList<Photo> search(String begin, String end) throws ParseException{
        ArrayList<Photo> photos = new ArrayList<>();
        Calendar earliest = Calendar.getInstance();
        Calendar latest = Calendar.getInstance();
        earliest.setTime((new SimpleDateFormat("MM/dd/yyyy")).parse(begin));
        latest.setTime((new SimpleDateFormat("MM/dd/yyyy")).parse(end));

        for(Album album : albums){
            // get all photos of the album if the search range is within album range
            if(earliest.compareTo(album.getEarliest()) <= 0 && latest.compareTo(album.getLatest()) >= 0){
                for (Photo photo : album.getPhotos()) {
                    if (!photos.contains(photo)) photos.add(photo);
                }
            }
            else if(earliest.compareTo(album.getEarliest()) <= 0 && latest.compareTo(album.getEarliest()) >= 0){
                Predicate<Photo> pred = p -> latest.compareTo(p.getCal()) >= 0;

                for (Photo photo : album.getPhotos()) {
                    if (pred.test(photo) && !photos.contains(photo)) photos.add(photo);
                }
            }
            else if(latest.compareTo(album.getLatest()) >= 0 && earliest.compareTo(album.getLatest()) <= 0){

                Predicate<Photo> pred = p -> earliest.compareTo(p.getCal()) <= 0;

                for (Photo photo : album.getPhotos()) {
                    if (pred.test(photo) && !photos.contains(photo)) photos.add(photo);
                }
            }
        }
        return FXCollections.observableArrayList(photos);
    }

    /**
     * Find all photos that match the tag query
     * @param byTag query
     * @return null if no results found and collection of photos if there are valid matches
     */
    public ObservableList<Photo> search(String byTag){
        ArrayList<Photo> photos = new ArrayList<>();

        Predicate<Photo> pred;
        if (byTag.split("AND").length == 2){
            String k1 = byTag.split("AND")[0].strip().split("=")[0].strip();
            String v1 = byTag.split("AND")[0].strip().split("=")[1].strip();
            String k2 = byTag.split("AND")[1].strip().split("=")[0].strip();
            String v2 = byTag.split("AND")[1].strip().split("=")[1].strip();

            pred = p ->
                (p.getTags().containsKey(k1) && p.getTags().get(k1).contains(v1)) &&
                (p.getTags().containsKey(k2) && p.getTags().get(k2).contains(v2));
        }
        else if (byTag.split("OR").length == 2){
            String k1 = byTag.split("OR")[0].strip().split("=")[0].strip();
            String v1 = byTag.split("OR")[0].strip().split("=")[1].strip();
            String k2 = byTag.split("OR")[1].strip().split("=")[0].strip();
            String v2 = byTag.split("OR")[1].strip().split("=")[1].strip();

            pred = p ->
                (p.getTags().containsKey(k1) && p.getTags().get(k1).contains(v1)) ||
                (p.getTags().containsKey(k2) && p.getTags().get(k2).contains(v2));
        }
        else{
            String k1 = byTag.split("=")[0].strip();
            String v1 = byTag.split("=")[1].strip();
            pred = p -> (p.getTags().containsKey(k1) && p.getTags().get(k1).contains(v1));
        }

        for(Album album : albums){
            for(Photo photo : album.getPhotos()){
                if(pred.test(photo) && !photos.contains(photo)) photos.add(photo);
            }
        }
        return FXCollections.observableArrayList(photos);
    }

    /**
     * Copy photo from one album to another
     * @param from album to copy the photo from
     * @param to album to copy the photo to
     * @param path absolute path of the photo to be copied
     * @return false if the from doesn't exist or if the to doesn't exist or if the from does not contain the photo or if the to already contains the photo and true otherwise
     */
    public boolean copyPhoto(String from, String to, String path){
        if(!this.contains(from) || !this.contains(to) || !this.getAlbum(from).contains(path)) return false;

        return this.getAlbum(to).addPhoto(this.getAlbum(from).getPhoto(path));
    }

    /**
     * Move photo from one album to another
     * @param from album to copy the photo from
     * @param to album to copy the photo to
     * @param path absolute path of the photo to be copied
     * @return false if the from doesn't exist or if the to doesn't exist or if the from does not contain the photo or if the to already contains the photo and true otherwise
     */
    public boolean movePhoto(String from, String to, String path){
        if(!copyPhoto(from, to, path)) return false;
        this.getAlbum(from).removePhoto(path);
        return true;
    }

    /**
     * Serialize the User instance into a .dat file.
     */
    public void writeUser(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../data/" + name + ".dat"));
            oos.writeObject(this);

        } catch(Exception e){}
    }

    /**
     * Unserialize the User instance from a .dat file.
     * @param name username of User
     * @return User instance of the corresponding username
     * @throws IOException when path to file is incorrect
     * @throws ClassNotFoundException when the class being cast is not the original class
     */
    public static User readUser(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../data/" + name + ".dat"));
        User user = (User)ois.readObject();
        return user;
    }

    /**
     * Check User equality
     * @param obj the other user instance
     * @return true if user names are same and false otherwise
     */
    public boolean equals(Object obj){
        if(obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        final User other = (User)obj;

        return this.name.equals(other.getName());
    }

    /**
     * toString
     * @return String containing the username of the user.
     */
    public String toString(){
        return this.name;
    }
}
