package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Paladin extends RPGClass {
    private String oath;

    public Paladin() {
        super("Paladin");
    }

    @Override
    public void askQuestions(FileView view) {
        int oathChoice = -1;

        do {
            view.displayMessage("What is your Oath?");
            view.displayMessage("1. Sacred");
            view.displayMessage("2. Fallen");
            oathChoice = view.getIntAnswer();
            
            switch (oathChoice) {
                case 1:
                    oath = "Sacred";
                    break;
                case 2:
                    oath = "Fallen";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    oathChoice = -1; // Reset to continue the loop
            }
        } while (oathChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Oath: " + oath);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Oath: ")) {
                oath = line.substring("Oath: ".length());
            }
            if (oath != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Oath: " + oath);
    }
}
