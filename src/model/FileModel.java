package model;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import model.RPGClasses.*;
import model.RPGRaces.*;
import view.FileView;

public class FileModel {
    public Map<String, RPGClass> classes;
    public Map<String, RPGRace> races;

    public FileModel() {
        classes = new HashMap<>();
        classes.put("Mage", new Mage());
        classes.put("Bard", new Bard());
        classes.put("Berserker", new Berserker());
        classes.put("Paladin", new Paladin());
        classes.put("Rogue", new Rogue());
        classes.put("Druid", new Druid());
        classes.put("Ranger", new Ranger());
        // Add other RPG classes here

        races = new HashMap<>();
        races.put("Elf", new Elf());
        races.put("Dwarf", new Dwarf());
        // Add other RPG races here
    }

    public void saveToFile(User user, String fileName, String characterName, String characterRace, RPGClass rpgClass, RPGRace rpgRace) throws IOException {
        fileName = fileName.trim();
        File userDir = user.getUserDirectory();
        File file = new File(userDir, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Character Name: " + characterName);
            writer.newLine();
            rpgRace.saveToFile(writer);
            rpgClass.saveToFile(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CharacterData readFromFile(User user, String fileName, FileView view) throws IOException {
        fileName = fileName.trim();
        File userDir = user.getUserDirectory();
        File file = new File(userDir, fileName);
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && line.startsWith("Character Name: ")) {
                String characterName = line.substring("Character Name: ".length());
    
                RPGClass rpgClass = null;
                RPGRace rpgRace = null;
    
                while ((line = reader.readLine()) != null) {
    
                    if (line.startsWith("Race: ")) {
                        String raceName = line.substring("Race: ".length());
                        rpgRace = races.get(raceName);
                        if (rpgRace != null) {
                            rpgRace.readFromFile(reader);
                        }
                    }

                    if (line.startsWith("Class: ")) {
                        String className = line.substring("Class: ".length());
                        rpgClass = classes.get(className);
                        if (rpgClass != null) {
                            rpgClass.readFromFile(reader);
                        }
                    }
    
                    if (rpgClass != null && rpgRace != null) {
                        break;
                    }
                }
    
                if (rpgClass != null && rpgRace != null) {
                    return new CharacterData(rpgClass, rpgRace, characterName);
                } else {
                    view.displayMessage("File is missing class or race information.");
                }
            } else {
                view.displayMessage("Character name not found in file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteFile(User user, String fileName) {
        fileName = fileName.trim();
        File userDir = user.getUserDirectory();
        File fileToDelete = new File(userDir, fileName);
        
        if (fileToDelete.exists() && fileToDelete.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
