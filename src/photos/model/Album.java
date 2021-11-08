package photos.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;

/**
 * Album Class handles the storage of a collection of photos.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Album implements Serializable{

    /**
     * Name of the Album.
     */
    private String name;

    /**
     * Collection of Photos
     */
    private ArrayList<Photo> photos;

    /**
     * Photo with the earliest date
     */
    private Photo earliest;

    /**
     * Photo with the latest date
     */
    private Photo latest;

    /**
     * Constructor
     * Used when creating an album containing search results.
     * @param name name of album
     * @param photos collection of photos
     */
    public Album(String name, ArrayList<Photo> photos) {
        this.name = name;
        this.photos = photos;
        earliest = null;
        latest = null;
        resetRange();
    }

    /**
     * Loop through all of the photos and determine the earliest and the latest photo
     */
    private void resetRange(){
        if(!photos.isEmpty()){
            earliest = photos.get(0);
            latest = photos.get(0);
            for(Photo photo : photos){
                if(earliest.compareTo(photo) > 0) earliest = photo;
                if(latest.compareTo(photo) < 0) latest = photo;
            }
        }
        else{
            earliest = null;
            latest = null;
        }
    }

    /**
     * Constructor
     * Associates an empty photos collection with the album.
     * @param name name of album
     */
    public Album(String name) {
        this(name, new ArrayList<>());
    }

    /**
     * Used to rename an album
     * @param name name of album
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get album name
     * @return name of album
     */
    public String getName() {
        return name;
    }

    /**
     * get Earliest date
     * @return Calendar instance with the earliest date
     */
    public Calendar getEarliest(){
        return earliest.getCal();
    }

    /**
     * get Latest date
     * @return Calendar instance with the latest date
     */
    public Calendar getLatest(){
        return latest.getCal();
    }

    /**
     * Check if the album contains a photo
     * @param photo the photo object
     * @return true if the album contains the photo and false otherwise
     */
    private boolean contains(Photo photo){
        return photos.contains(photo);
    }

    /**
     * Check if the album contains a photo
     * @param path String path of the photo
     * @return true if the album contains the photo and false otherwise
     */
    public boolean contains(String path){
        return contains(new Photo(path));
    }

    /**
     * Get the photo object from the album
     * @param path String path of the photo
     * @return photo object if the specific photo exists in the album, null otherwise
     */
    public Photo getPhoto(String path){
        Photo photo = new Photo(path);
        int index = photos.indexOf(photo);
        if(index == -1) return null;
        return photos.get(index);
    }

    /**
     * Get the collection of photos
     * @return ArrayList of photos in the album.
     */
    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    /**
     * Add a photo into the album and update the earliest and latest if needed
     * @param photo A photo object
     * @return false if the album already contains the photo and true otherwise
     */
    public boolean addPhoto(Photo photo){
        if(this.contains(photo)) return false;

        if(earliest == null){
            earliest = photo;
            latest = photo;
        } else {
            if(earliest.compareTo(photo) > 0) earliest = photo;
            if(latest.compareTo(photo) < 0) latest = photo;
        }

        return photos.add(photo);
    }

    /**
     * Remove a photo from the album.
     * Update the earliest and latest photo if needed.
     * @param path Absolute path of the image in the system.
     */
    public void removePhoto(String path){
        Photo photo = new Photo(path);
        this.photos.remove(photo);
        if((earliest != null) && (earliest.equals(photo) || latest.equals(photo))) resetRange();
    }

    /**
     * Check album equality
     * @param obj the other album instance
     * @return true if album names are same and false otherwise
     */
    public boolean equals(Object obj){
        if(obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        final Album other = (Album)obj;

        return this.name.equals(other.getName());
    }

    /**
     * toString
     * @return String containing the name, size, earliest date, and latest date of the album
     */
    public String toString(){
        if(photos.isEmpty()) return "Name: " + name + " Size: " + photos.size();

        return "Name: " + name + ", Size: " + photos.size() + ", Range: " + earliest + " to " + latest;
    }
}
