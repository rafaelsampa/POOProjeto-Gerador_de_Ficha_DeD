package controller;
import java.io.File;
import java.io.IOException;

import model.FileModel;
import model.RPGClass;
import model.RPGRace;
import model.User;
import model.CharacterData;
import view.FileView;

public class FileController {
    private UserController userController;
    private FileModel fileModel;
    private FileView view;

    public FileController(UserController userController, FileModel fileModel, FileView view) {
        this.userController = userController;
        this.fileModel = fileModel;
        this.view = view;
    }

    private void classesMenu() {
        System.out.println("Choose class: ");
        System.out.println("1. Mage");
        System.out.println("2. Bard");
        System.out.println("3. Berserker");
        System.out.println("4. Paladin");
        System.out.println("5. Rogue");
        System.out.println("6. Druid");
        System.out.println("7. Ranger");
    }

    private String getClassFromNumber() {
        int classChoice = -1;
        do {
            classesMenu();
            classChoice = view.getIntAnswer();
            switch (classChoice) {
                case 1:
                    return "Mage";
                case 2:
                    return "Bard";
                case 3:
                    return "Berserker";
                case 4:
                    return"Paladin";
                case 5:
                    return "Rogue";
                case 6:
                    return "Druid";
                case 7:
                    return "Ranger";
                default:
                    view.displayMessage("Invalid number.");
                    classChoice = -1;
            }
        } while (true);
    }

    private void racesMenu() {
        System.out.println("Choose race: ");
        System.out.println("1. Elf");
        System.out.println("2. Dwarf");
    }

    private String getRaceFromNumber() {
        int classChoice = -1;
        do {
            racesMenu();
            classChoice = view.getIntAnswer();
            switch (classChoice) {
                case 1:
                    return "Elf";
                case 2:
                    return "Dwarf";
                default:
                    view.displayMessage("Invalid number.");
                    classChoice = -1;
            }
        } while (true);
    }

    public void run() {
        int choice = -1;
        while (true) {
            choice = view.getMenuChoice();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    showAllUsers();
                    break;
                case 3:
                    selectUser();
                    break;
                case 4:
                    showCurrentUser();
                    break;
                case 5:
                    editUser();
                    break;
                case 6:
                    deleteSelectedUser();
                    break;
                case 7:
                    showUserFiles();
                    break;
                case 8:
                    saveInformation();
                    break;
                case 9:
                    readInformation();
                    break;
                case 10:
                    editInformation();
                    break;
                case 11:
                    deleteInformation();
                    break;
                case 12:
                    view.displayMessage("Exiting...");
                    view.closeScanner();
                    return;
                default:
                    view.displayMessage("Invalid choice.");
                    break;
            }
        }
    }

    public static String capitalizeWords(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return phrase;
        }

        StringBuilder result = new StringBuilder(phrase.length());
        String[] words = phrase.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                String rest = word.substring(1).toLowerCase();
                result.append(firstChar).append(rest).append(" ");
            }
        }

        return result.toString().trim();
    }

    private void addUser() {
        String username = view.getUserInput("Enter new username: ");
        username = capitalizeWords(username);
        if (userController.addUser(username)) {
            view.displayMessage("User " + username + " added successfully.");
            selectUserWhenAdding(username);
        } else {
            view.displayMessage("User already exists.");
        }
    }

    private void selectUserWhenAdding(String username) {
        User currentUser = userController.getUser(username);
        userController.setCurrentUser(currentUser);
        if (currentUser != null) {
            view.displayMessage("User " + username + " selected.");
        } else {
            view.displayMessage("User not found.");
        }
    }

    private void deleteSelectedUser() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
    
        String confirmation = view.getUserInput("Are you sure you want to delete the selected user? Type CONFIRM to proceed: ");
        if (!confirmation.equals("CONFIRM")) {
            view.displayMessage("Deletion canceled.");
            return;
        }
    
        String username = currentUser.getUsername();
        boolean deleted = userController.deleteUser(username);
        if (deleted) {
            view.displayMessage("User deleted successfully.");
        } else {
            view.displayMessage("User not found or failed to delete.");
        }
    }  

    private void selectUser() {
        String username = view.getUserInput("Enter username: ");
        username = capitalizeWords(username);
        User currentUser = userController.getUser(username); // Get the current user from the controller
        if (currentUser != null) {
            userController.setCurrentUser(currentUser); // Set the current user in the controller
            view.displayMessage("User " + username + " selected.");
        } else {
            view.displayMessage("User not found.");
        }
    }

    private void showCurrentUser() {
        User currentUser = userController.getCurrentUser(); // Get the current user from the model
        if (currentUser != null) {
            view.displayMessage("Current user: " + currentUser.getUsername());
        } else {
            view.displayMessage("No user selected.");
        }
    }

    private void showAllUsers() {
        userController.showAllUsers();
    }

    private void saveInformation() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to save: ");
        File userDir = currentUser.getUserDirectory();
        File fileToSave = new File(userDir, fileName);
        
        if (fileToSave.exists()) {
            view.displayMessage("File already exists. Please choose a different file name.");
            return;
        }
    
        String characterName = view.getUserInput("Enter the character's name: ");

        String raceName = getRaceFromNumber();

        // Debug statement to verify the selected race
        //view.displayMessage("Selected race: " + raceName);

        RPGRace rpgRace = fileModel.races.get(raceName);
    
        // Debug statement to verify the retrieved RPGRace instance
        //System.out.println("RPGRace instance: " + rpgRace);

        if (rpgRace != null) {
            rpgRace.askQuestions(view);
        } else {
            view.displayMessage("Invalid race.");
            return;
        }

        String className = getClassFromNumber();

        // Debug statement to verify the selected class
        //view.displayMessage("Selected class: " + className);
    
        // Get the RPGClass instance based on the selected class name
        RPGClass rpgClass = fileModel.classes.get(className);

        // Debug statement to verify the retrieved RPGClass instance
        //System.out.println("RPGClass instance: " + rpgClass);
    
        if (rpgClass != null) {
            rpgClass.askQuestions(view);
            try {
                fileModel.saveToFile(currentUser, fileName, characterName, raceName, rpgClass, rpgRace);
                view.displayMessage("Data saved to file.");
            } catch (IOException e) {
                view.displayMessage("An error occurred while saving to file.");
                e.printStackTrace();
            }
        } else {
            view.displayMessage("Invalid class.");
        }
    }        

    private void readInformation() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to read: ");
        File userDir = currentUser.getUserDirectory();
        File fileToRead = new File(userDir, fileName);
        if (!fileToRead.exists()) {
            view.displayMessage("File does not exist.");
            return;
        }
        try {
            CharacterData characterData = fileModel.readFromFile(currentUser, fileName, view);
            if (characterData != null) {
                view.displayMessage("Character Name: " + characterData.getName());
                characterData.getRpgRace().displayData(view);
                characterData.getRpgClass().displayData(view);
            } else {
                view.displayMessage("File is empty or missing data.");
            }
        } catch (IOException e) {
            view.displayMessage("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }

    private void editInformation() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to edit: ");
        File userDir = currentUser.getUserDirectory();
        File fileToEdit = new File(userDir, fileName);
    
        if (!fileToEdit.exists()) {
            view.displayMessage("File does not exist.");
            return;
        }
        
        view.displayMessage("This will override ALL existing information on this character sheet: ");
        String confirmation = view.getUserInput("Are you sure you want to edit the selected character sheet? Type CONFIRM to proceed: ");
        if (!confirmation.equals("CONFIRM")) {
            view.displayMessage("Editing operation canceled.");
            return;
        }

        String characterName;

        String changeName = view.getUserInput("Would you like to change the character's name? (YES/NO): ");
        if (changeName.equals("YES")) {
            characterName = view.getUserInput("Enter the character's new name: ");
            if (characterName == null) {
                view.displayMessage("Error reading character name from file.");
                return;
            }
        } else {
            try {
                CharacterData characterData = fileModel.readFromFile(currentUser, fileName, view);
                characterName = characterData.getName();
                if (characterName == null) {
                    view.displayMessage("Error reading character name from file.");
                    return;
                }
            } catch (IOException e) {
                view.displayMessage("An error occurred while reading the character name from file.");
                e.printStackTrace();
                return;
            }
        }
        
        String raceName = getRaceFromNumber();
        RPGRace rpgRace = fileModel.races.get(raceName);

        if (rpgRace != null) {
            rpgRace.askQuestions(view);
        } else {
            view.displayMessage("Invalid race.");
        }

        String className = getClassFromNumber();
        RPGClass rpgClass = fileModel.classes.get(className);

        if (rpgClass != null) {
            rpgClass.askQuestions(view); // Ask questions specific to the class
        } else {
            view.displayMessage("Invalid class.");
        }

        if (rpgClass != null && rpgRace != null) {
            try {
                fileModel.saveToFile(currentUser, fileName, characterName, raceName, rpgClass, rpgRace);
                view.displayMessage("Data updated in file.");
            } catch (IOException e) {
                view.displayMessage("An error occurred while editing the file.");
                e.printStackTrace();
            }
        } else {
            view.displayMessage("Invalid class.");
        }
    }
    
    private void deleteInformation() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to delete: ");
        File userDir = currentUser.getUserDirectory();
        File fileToDelete = new File(userDir, fileName);
        
        if (fileToDelete.exists()) {
            // Prompt for confirmation only if the file exists
            String confirmation = view.getUserInput("Are you sure you want to delete the file? (Type CONFIRM to proceed): ");
            if (!confirmation.equalsIgnoreCase("CONFIRM")) {
                view.displayMessage("Deletion cancelled.");
                return;
            }
            boolean deleted = fileModel.deleteFile(currentUser, fileName);
            
            if (deleted) {
                view.displayMessage("File deleted successfully.");
            } else {
                view.displayMessage("Failed to delete the file.");
            }
        } else {
            view.displayMessage("File does not exist.");
        }
    }

    private void showUserFiles() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        File userDir = currentUser.getUserDirectory();
        File[] files = userDir.listFiles();
        if (files != null && files.length > 0) {
            view.displayMessage("Files for user " + currentUser.getUsername() + ":");
            for (File file : files) {
                view.displayMessage(file.getName());
            }
        } else {
            view.displayMessage("No files found for user " + currentUser.getUsername() + ".");
        }
    }

    private void editUser() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
    
        String newUsername = view.getUserInput("Enter new username: ");
        newUsername = capitalizeWords(newUsername);
    
        boolean renamed = userController.renameUser(currentUser.getUsername(), newUsername);
        if (renamed) {
            view.displayMessage("User renamed successfully to " + newUsername + ".");
            currentUser.setUsername(newUsername); // Update currentUser's username
        } else {
            view.displayMessage("New username already exists. Failed to rename user.");
        }
    }     
}
