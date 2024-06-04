package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import view.FileView;

public abstract class RPGClass {
    protected String className;

    public RPGClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public abstract void askQuestions(FileView view);
    public abstract void saveToFile(BufferedWriter writer) throws IOException;
    public abstract void readFromFile(BufferedReader reader) throws IOException;
    public abstract void displayData(FileView view);
}
