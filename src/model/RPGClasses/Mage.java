package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Mage extends RPGClass {
    private String element;
    private String specialty;

    public Mage() {
        super("Mage");
    }

    @Override
    public void askQuestions(FileView view) {
        int elementChoice = -1;
        int specialtyChoice = -1;
        do {
            view.displayMessage("What is your Element?");
            view.displayMessage("1. Fire");
            view.displayMessage("2. Ice");
            elementChoice = view.getIntAnswer();
            
            switch (elementChoice) {
                case 1:
                    element = "Fire";
                    break;
                case 2:
                    element = "Ice";
                    break;
                default:
                    view.displayMessage("Invalid input.");
                    elementChoice = -1; // Reset to continue the loop
            }
        } while (elementChoice == -1);
        
        do {
            view.displayMessage("What is your Specialty?");
            view.displayMessage("1. Attack");
            view.displayMessage("2. Defense");
            specialtyChoice = view.getIntAnswer();
            
            switch (specialtyChoice) {
                case 1:
                    specialty = "Attack";
                    break;
                case 2:
                    specialty = "Defense";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    specialtyChoice = -1; // Reset to continue the loop
            }
        } while (specialtyChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Element: " + element);
        writer.newLine();
        writer.write("Specialty: " + specialty);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Element: ")) {
                element = line.substring("Element: ".length());
            } else if (line.startsWith("Specialty: ")) {
                specialty = line.substring("Specialty: ".length());
            }
            if (element != null && specialty != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Element: " + element);
        view.displayMessage("Specialty: " + specialty);
    }
}
