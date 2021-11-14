package photos.model;

import java.util.ArrayList;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Admin User Class
 * @author Apurva Narde
 * @author Max Geiger
 */
public class Admin {

    /**
     * Location of the serializable user data
     */
    private static final String dataDir = "../data";

    /**
     * Constructor
     * private no-args constructor
     */
    private Admin() { }


    /**
     * Takes in a username and creates a user which is stored in data/
     * @param name refers to the username of the user
     * @return true if the user creation is successful and false if the user already exists
     */
    public static boolean createUser(String name){
        try {
            User.readUser(name);
        } catch(Exception e){
            (new User(name)).writeUser();
            return true;
        }
        return false;
    }

    /**
     * Takes in a username and deletes the corresponding .dat file
     * @param name refers to the username of the user
     */
    public static void deleteUser(String name){
        File[] files = new File(dataDir).listFiles();
        for (File file : files) {
            String fname = file.getName();
            if (file.isFile() && fname.substring(0, fname.lastIndexOf('.')).equals(name)) {
                file.delete();
                return;
            }
        }
    }

    /**
     * Admin List users
     * @return List of Strings which contain the username of each user
     */
    public static ObservableList<String> getUsers(){
        ObservableList<String> results = FXCollections.observableArrayList(new ArrayList<>());

        File[] files = new File(dataDir).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            String name = file.getName();
            if (file.isFile()) results.add(name.substring(0, name.lastIndexOf('.')));
        }
        return results;
    }
}
