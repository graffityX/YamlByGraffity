package at.graffity.api;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class SaveInformation {

    private void write(String id, String string, BufferedWriter writer, String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.startsWith(id + " : ")) {
                    writer.write(id + " : " + string + "\n");
                    return;
                } else {
                    writer.write(scanner.nextLine() + "\n");
                }


            }

            writer.write(id + " : " + string + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void set(String id, String string, BufferedWriter writer, String path) {
            //Hier wird versucht zu schreiben. Die Id entspricht dem Key, der String dem Value. Das \n wird für einen Absatz benutzt
            this.write(id, string, writer, path);
    }

    public void setComment(String comment, BufferedWriter writer) {
        try {
            writer.write("##### " + comment + "\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setArray(String id, String[] array, BufferedWriter writer) {

        try {
            int i = 0;
            for (String str : array) {
                //Das Ergebnis würde ca so aussehen "array.id.0 : input + absatz". Das 0 wird immer um eins erhöht
                //So ist es auch im File klar sichtbar, der wieviele Teil des Arrays dies ist
                writer.write("array." + id + "." + i + " : " + str + "\n");
                i++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHash(String hashKey, HashMap hash, BufferedWriter writer, String path) {

           this.write(hashKey, hash.get(hashKey).toString(), writer, path);

    }


}