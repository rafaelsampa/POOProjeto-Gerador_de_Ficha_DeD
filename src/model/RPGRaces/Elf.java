package model.RPGRaces;
import java.io.*;

import model.RPGRace;
import view.FileView;

public class Elf extends RPGRace {
    private String subrace;
    FileView view = new FileView();

    public Elf() {
        super("Elf");
    }

    @Override
    public void askQuestions(FileView view) {
        int subRaceChoice = -1;

        do {
            view.displayMessage("What is your subrace?");
            view.displayMessage("1. High Elf");
            view.displayMessage("2. Wood Elf");
            subRaceChoice = view.getIntAnswer();

            switch (subRaceChoice) {
                case 1:
                    subrace = "High Elf";
                    break;
                case 2:
                    subrace = "Wood Elf";
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
