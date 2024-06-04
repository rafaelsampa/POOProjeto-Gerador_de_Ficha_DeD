package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Bard extends RPGClass {
    private String instrument;
    private String style;

    public Bard() {
        super("Bard");
    }

    @Override
    public void askQuestions(FileView view) {
        int instrumentChoice = -1;
        int styleChoice = -1;

        do {
            view.displayMessage("What is your instrument?");
            view.displayMessage("1. Violin");
            view.displayMessage("2. Flute");
            instrumentChoice = view.getIntAnswer();

            switch (instrumentChoice) {
                case 1:
                    instrument = "Violin";
                    break;
                case 2:
                instrument = "Flute";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    instrumentChoice = -1; // Reset to continue the loop
            }
        } while (instrumentChoice == -1);

        do {
            view.displayMessage("What is your style?");
            view.displayMessage("1. Classical");
            view.displayMessage("2. Jazz");
            styleChoice = view.getIntAnswer();

            switch (styleChoice) {
                case 1:
                    style = "Classical";
                    break;
                case 2:
                    style = "Jazz";
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
        writer.write("Instrument: " + instrument);
        writer.newLine();
        writer.write("Style: " + style);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Instrument: ")) {
                instrument = line.substring("Instrument: ".length());
            }
            if (line.startsWith("Style: ")) {
                style = line.substring("Style: ".length());
            }
            if (instrument != null && style != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Instrument: " + instrument);
        view.displayMessage("Style: " + style);
    }
}
