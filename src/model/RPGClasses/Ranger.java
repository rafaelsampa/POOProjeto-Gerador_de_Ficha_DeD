package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Ranger extends RPGClass {
    private String weapon;
    private String arrow;

    public Ranger() {
        super("Ranger");
    }

    @Override
    public void askQuestions(FileView view) {
        int weaponChoice = -1;
        int arrowChoice = -1;

        do {
            view.displayMessage("What is your weapon?");
            view.displayMessage("1. Bow");
            view.displayMessage("2. Crossbow");
            weaponChoice = view.getIntAnswer();

            switch (weaponChoice) {
                case 1:
                    weapon = "Bow";
                    break;
                case 2:
                    weapon = "Crossbow";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    weaponChoice = -1; // Reset to continue the loop
            }
        } while (weaponChoice == -1);

        do {
            view.displayMessage("What is your arrow type?");
            view.displayMessage("1. Poisonous");
            view.displayMessage("2. Flaming");
            arrowChoice = view.getIntAnswer();

            switch (arrowChoice) {
                case 1:
                    arrow = "Poisonous";
                    break;
                case 2:
                    arrow = "Flaming";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    arrowChoice = -1; // Reset to continue the loop
            }
        } while (arrowChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Weapon: " + weapon);
        writer.newLine();
        writer.write("Arrow type: " + arrow);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Weapon: ")) {
                weapon = line.substring("Weapon: ".length());
            }
            if (line.startsWith("Arrow type: ")) {
                arrow = line.substring("Arrow type: ".length());
            }
            if (weapon != null && arrow != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Weapon: " + weapon);
        view.displayMessage("Arrow type: " + arrow);
    }
}
