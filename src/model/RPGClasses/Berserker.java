package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Berserker extends RPGClass {
    private String martialArt;
    private String weapon;

    public Berserker() {
        super("Berserker");
    }

    @Override
    public void askQuestions(FileView view) {
        int martialArtChoice = -1;
        int weaponChoice = -1;

        do {
            view.displayMessage("What is your Martial Art?");
            view.displayMessage("1. Karate");
            view.displayMessage("2. Boxing");
            martialArtChoice = view.getIntAnswer();

            switch (martialArtChoice) {
                case 1:
                    martialArt = "Karate";
                    break;
                case 2:
                    martialArt = "Boxing";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    martialArtChoice = -1; // Reset to continue the loop
            }
        } while (martialArtChoice == -1);

        do {
            view.displayMessage("What is your Weapon?");
            view.displayMessage("1. Staff");
            view.displayMessage("2. Sword");
            weaponChoice = view.getIntAnswer();

            switch (weaponChoice) {
                case 1:
                    weapon = "Staff";
                    break;
                case 2:
                    weapon = "Sword";
                    break;
                default:
                    view.displayMessage("Invalid input.");
                    weaponChoice = -1; // Reset to continue the loop
            }
        } while (weaponChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Martial Art: " + martialArt);
        writer.newLine();
        writer.write("Weapon: " + weapon);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Martial Art: ")) {
                martialArt = line.substring("Martial Art: ".length());
            } else if (line.startsWith("Weapon: ")) {
                weapon = line.substring("Weapon: ".length());
            }
            if (martialArt != null && weapon != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Martial Art: " + martialArt);
        view.displayMessage("Weapon: " + weapon);
    }
}
