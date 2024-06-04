package controller;

import java.io.File;
import config.Config;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.User;

import view.FileView;

public class UserController {
    private Map<String, User> users;
    private User currentUser; // Add a field to store the current user
    private FileView view = new FileView();

    public UserController() {
        users = new HashMap<>();
        loadExistingUsers();
    }

    public boolean addUser(String username) {
        if (userExists(username)) {
            return false; // User already exists
        }
        User user = new User(username);
        users.put(username, user);
        createUserDirectory(user);
        currentUser = user;
        return true;
    }

    public boolean deleteUser(String username) {
        User user = users.get(username);
        if (user != null) {
            File userDir = user.getUserDirectory();
            if (userDir.exists() && userDir.isDirectory()) {
                try {
                    deleteDirectory(userDir);
                    // Only remove the user from the hashmap after successful deletion
                    users.remove(username);
                    // Check if the user being deleted is the current user
                    currentUser = null;
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false; // Return false if deletion fails
                }
            }
        }
        return false;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    private void createUserDirectory(User user) {
        File userDir = new File(Config.BASE_DIRECTORY + "/" + user.getUsername());
        if (!userDir.exists()) {
            userDir.mkdirs(); // Ensure directories are created recursively
        }
    }

    private void loadExistingUsers() {
        File baseDir = new File(Config.BASE_DIRECTORY);
        File[] userDirs = baseDir.listFiles(File::isDirectory);
        if (userDirs != null) {
            for (File userDir : userDirs) {
                String username = userDir.getName();
                User user = new User(username);
                users.put(username, user);
            }
        }
    }

    public void showAllUsers() {
        if (users.isEmpty()) {
            view.displayMessage("No users found.");
            return;
        }

        view.displayMessage("List of all users:");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            view.displayMessage(entry.getKey());
        }
    }

    private void deleteDirectory(File directory) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        if (!directory.delete()) {
            throw new IOException("Failed to delete directory: " + directory.getAbsolutePath());
        }
    }

    public boolean renameUser(String oldUsername, String newUsername) {
        User user = getUser(oldUsername);
        if (user != null) {
            // Check if the new username already exists
            if (userExists(newUsername)) {
                return false; // New username already exists
            }
            
            // Get the old and new directories
            File oldDir = user.getUserDirectory();
            File newDir = new File(Config.BASE_DIRECTORY + "/" + newUsername);
            
            // Rename the user's directory
            if (oldDir.renameTo(newDir)) {
                // Update the user's username
                user.setUsername(newUsername);
                // Remove the old username entry from the user list
                users.remove(oldUsername);
                // Add the user with the new username to the user list
                users.put(newUsername, user);
                return true;
            } else {
                // Roll back the username change if directory renaming fails
                user.setUsername(oldUsername);
                return false;
            }
        }
        return false; // User not found
    }    
    
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
