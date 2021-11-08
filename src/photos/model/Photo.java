package photos.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.io.Serializable;

/**
 * Photo class to handle the storage and management of the basic building blocks.
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Photo implements Comparable<Photo>, Serializable {

    /**
     * Absolute path of the image in the system.
     */
    private String path;

    /**
     * Holds the latest modification date of the image as provided by the system.
     */
    private Calendar cal;

    /**
     * key value pair of String which can be associated with multiple tags.
     */
    private HashMap<String,LinkedList<String>> tags;

    /**
     * Caption of the image.
     */
    private String caption;

    /**
     * Constructor
     * @param path Absolute path of the image in the system.
     * @param cal Holds the latest modification date of the image as provided by the system.
     * @param tags key value pair of String which can be associated with multiple tags.
     * @param caption Caption of the image.
     */
    private Photo(String path, Calendar cal, HashMap<String,LinkedList<String>> tags, String caption) {
        this.path = path;
        this.cal = cal;
        this.tags = tags;
        this.caption = caption;
    }

    /**
     * Constructor
     * Assume path given is valid
     * @param path Absolute path of the image in the system.
     */
    public Photo(String path) {
        this(path, Calendar.getInstance(), new HashMap<>(), null);
        try {
            cal.setTimeInMillis((new File(path)).lastModified());
            cal.set(Calendar.MILLISECOND, 0);
        } catch(Exception e){}
    }

    /**
     * get path of the image
     * @return Absolute path of the image in the system.
     */
    public String getPath() {
        return path;
    }

    /**
     * get last modification time of the image
     * @return Holds the latest modification date of the image as provided by the system.
     */
    public Calendar getCal() {
        return this.cal;
    }

    /**
     * Add a key value tag to the image
     * @param name tag name
     * @param value tag value
     * @return false if a duplicate tag exists and true otherwise
     */
    public boolean addTag(String name, String value) {
        if(!tags.containsKey(name)) tags.put(name, new LinkedList<>());

        if(tags.get(name).contains(value)) return false;

        return tags.get(name).add(value);
    }

    /**
     * Remove a tag from an image
     * @param name tag name
     * @param value tag value
     */
    public void removeTag(String name, String value) {
        if(tags.containsKey(name)) tags.get(name).remove(value);
    }

    /**
     * Get list of tags associated with the image.
     * @return tags key value pair of String which can be associated with multiple tags.
     */
    public HashMap<String,LinkedList<String>> getTags() {
        return tags;
    }

    /**
     * Set caption of the image.
     * @param caption Caption of the image.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Get caption of the image.
     * @return Caption of the image.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Check photo equality
     * @param obj the other photo instance
     * @return true if photo paths are same and false otherwise
     */
    public boolean equals(Object obj){
        if(obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        final Photo other = (Photo)obj;

        return this.path.equals(other.getPath());
    }

    /**
     * Comparison between photos
     * @param other the other photo instance
     * @return positive if the current photo is more recent
     */
    public int compareTo(Photo other){
        return this.cal.compareTo(other.getCal());
    }

    /**
     * toString
     * @return String containing the date of the photo in mm/dd/yyyy format
     */
    public String toString(){
        // return cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
        return path;
    }
}
