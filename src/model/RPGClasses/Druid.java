package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Druid extends RPGClass {
    private String wildShape;
    private String school;

    public Druid() {
        super("Druid");
    }

    @Override
    public void askQuestions(FileView view) {
        int instrumentChoice = -1;
        int styleChoice = -1;

        do {
            view.displayMessage("What is your wild shape?");
            view.displayMessage("1. Cat");
            view.displayMessage("2. Dog");
            instrumentChoice = view.getIntAnswer();

            switch (instrumentChoice) {
                case 1:
                    wildShape = "Cat";
                    break;
                case 2:
                    wildShape = "Dog";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    instrumentChoice = -1; // Reset to continue the loop
            }
        } while (instrumentChoice == -1);

        do {
            view.displayMessage("What is your school?");
            view.displayMessage("1. Good");
            view.displayMessage("2. Bad");
            styleChoice = view.getIntAnswer();

            switch (styleChoice) {
                case 1:
                    school = "Good";
                    break;
                case 2:
                    school = "Bad";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    styleChoice = -1; // Reset to continue the loop
            }
        } while (styleChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Wild Shape: " + wildShape);
        writer.newLine();
        writer.write("School: " + school);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Wild Shape: ")) {
                wildShape = line.substring("Wild Shape: ".length());
            }
            if (line.startsWith("School: ")) {
                school = line.substring("School: ".length());
            }
            if (wildShape != null && school != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Wild Shape: " + wildShape);
        view.displayMessage("School: " + school);
    }
}
