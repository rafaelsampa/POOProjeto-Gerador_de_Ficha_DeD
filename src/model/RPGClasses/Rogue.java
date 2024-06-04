package model.RPGClasses;

import java.io.*;

import model.RPGClass;
import view.FileView;

public class Rogue extends RPGClass{
    private String stealthTechnique;

    public Rogue() {
        super("Rogue");
    }

    @Override
    public void askQuestions(FileView view) {
        int stealthTechniqueChoice = -1;

        do {
            view.displayMessage("What is your stealth technique?");
            view.displayMessage("1. Trickster");
            view.displayMessage("2. Thief");
            stealthTechniqueChoice = view.getIntAnswer();

            switch (stealthTechniqueChoice) {
                case 1:
                    stealthTechnique = "Trickster";
                    break;
                case 2:
                    stealthTechnique = "Thief";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    stealthTechniqueChoice = -1; // Reset to continue the loop
            }
        } while (stealthTechniqueChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Stealth Technique: " + stealthTechnique);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Stealth Technique: ")) {
                stealthTechnique = line.substring("Stealth Technique: ".length());
            }
            if (stealthTechnique != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Stealth Technique: " + stealthTechnique);
    }
    
}
