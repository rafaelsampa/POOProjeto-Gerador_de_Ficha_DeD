package model.RPGRaces;
import java.io.*;

import model.RPGRace;
import view.FileView;

public class Dwarf extends RPGRace {
    private String subrace;
    FileView view = new FileView();

    public Dwarf() {
        super("Dwarf");
    }

    @Override
    public void askQuestions(FileView view) {
        int subRaceChoice = -1;

        do {
            view.displayMessage("What is your subrace?");
            view.displayMessage("1. Hill Dwarf");
            view.displayMessage("2. Mountain Dwarf");
            subRaceChoice = view.getIntAnswer();

            switch (subRaceChoice) {
                case 1:
                    subrace = "Hill Dwarf";
                    break;
                case 2:
                    subrace = "Mountain Dwarf";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    subRaceChoice = -1; // Reset to continue the loop
            }
        } while (subRaceChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Race: " + raceName);
        writer.newLine();
        writer.write("Subrace: " + subrace);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Subrace: ")) {
                subrace = line.substring("Subrace: ".length());
            }
            if (subrace != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Race: " + raceName);
        view.displayMessage("Subrace: " + subrace);
    }
}
