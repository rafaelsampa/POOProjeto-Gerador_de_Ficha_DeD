package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import view.FileView;

public abstract class RPGRace {
    protected String raceName;

    public RPGRace(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceName() {
        return raceName;
    }

    public abstract void askQuestions(FileView view);
    public abstract void saveToFile(BufferedWriter writer) throws IOException;
    public abstract void readFromFile(BufferedReader reader) throws IOException;
    public abstract void displayData(FileView view);
}
