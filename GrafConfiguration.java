package at.graffity.api;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GrafConfiguration {

    private static SaveInformation saveInformation = new SaveInformation();
    private static ReadInformation readInformation = new ReadInformation();

    private final String path;
    private final BufferedWriter writer;


    public GrafConfiguration(String path) {
        File file = new File(path);
        this.path = path;
        writer = setWriter();

    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setString(String id, String string) {
        saveInformation.set(id, string, writer, path);
    }

    public void setBoolean(String id, Boolean b) {
        saveInformation.set(id, b.toString(), writer, path);
    }

    public void setInteger(String id, Integer i) {
        saveInformation.set(id, i.toString(), writer, path);
    }

    public void setArray(String id, String[] array) {
        saveInformation.setArray(id, array, writer);
    }

    public void setDouble(String id, Double d) {
        saveInformation.set(id, d.toString(), writer, path);
    }

    public void setDate(String id, LocalDate date) {
        saveInformation.set(id, date.toString(), writer, path);
    }

    public void setHash(String id, HashMap hash) {
        saveInformation.setHash(id, hash, writer, path);
    }


    public String getString(String id) {
        return readInformation.get(id, path);
    }

    public Boolean getBoolean(String id) {
        return Boolean.valueOf(readInformation.get(id, path));
    }

    public Integer getInteger(String id) {
        return Integer.valueOf(readInformation.get(id, path));
    }

    public LocalDate getDate(String id) {
        return LocalDate.parse(readInformation.get(id, path));
    }

    public Double getDouble(String id) {
        return Double.valueOf(readInformation.get(id, path));
    }

    public ArrayList<String> getStringList(String id) {
        return readInformation.getStringList(id, path);
    }

    public ArrayList<String> getAll() {
        return readInformation.readCfg(path);
    }


    //Returnt den Speicherpfad des Objects
    private String getPath() {
        return path;
    }

    //Returnt den Schreiber des Objects
    private BufferedWriter getWriter() {
        return writer;
    }

    public void setComment(String comment) {
        saveInformation.setComment(comment, writer);
    }

    //Bestimmt den Writer des Objects. Wird nur in der Objekt Initialisierung benutzt
    private BufferedWriter setWriter() {
        File file = new File(path);
        try {
            return new BufferedWriter(new FileWriter(file));
            //Append true is very, very wichtig. Ansonsten wird nämlich das ganze File überschrieben
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
