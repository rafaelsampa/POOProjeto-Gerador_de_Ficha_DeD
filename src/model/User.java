package model;

import java.io.File;
import config.Config;

public class User {
    private String username;
    private File userDirectory;

    public User(String username) {
        this.username = username;
        this.userDirectory = new File(Config.BASE_DIRECTORY + "/" + username);
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }
    }

    public String getUsername() {
        return username;
    }

    public File getUserDirectory() {
        this.userDirectory = new File(Config.BASE_DIRECTORY + "/" + username);
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }
        return userDirectory;
    }

    public void setUsername(String username) {
        this.username = username;
        // Update userDirectory whenever username changes
        this.userDirectory = new File(Config.BASE_DIRECTORY + "/" + username);
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }
    }

    public void setUserDirectory(File userDirectory) {
        this.userDirectory = userDirectory;
    }
}
