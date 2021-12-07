package at.graffity.api;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadInformation {

    //Eine private Methode die jedes Mal genutzt wird um den File Reader aufzurufen
    private Scanner setReader(String path) {
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scanner;


    }

    //Anhand dieser Methode werde ich den Code erklären
    //Die ID ist der Key, der vom Programm angesteuert und danacher der Wert ausgelesen wird
    //Path ist wiederum die Adresse, die für den Abruf des richtigen Readers benötigt wird
    public String get(String id, String path) {
        Scanner reader = this.setReader(path);

        //Solange der reader eine neue Zeile findet, wird er sie benutzen
        while (reader.hasNextLine()) {

            String[] array = reader.nextLine().split(" : ");
            //Hier wird der Key und der Value bestimmt. Key ist dann array[0]und value wird später array[2]
            //Das " : " ist Teil von keinem. Schade irgendwie :(
            if (array[0].equalsIgnoreCase(id)) {
                //Wenn der Key so ist, wie ich ihn haben wollte, wird der Reader geschlossen und array[1] also der Value zurückgegeben

                reader.close();
                return array[1];
            }
        }
        reader.close();
        //Sollte der KEy nicht gefunden werden, wird null returnt und der Reader geschlossen
        System.out.println("Das gesuchte Element wurde nicht gefunden!");
        return null;

    }


    public ArrayList<String> getStringList(String id, String path) {
        Scanner reader = this.setReader(path);
        ArrayList<String> stringList = new ArrayList<>();
        while (reader.hasNextLine()) {
            String nextLine = reader.nextLine();
            if(nextLine.startsWith("array." + id)) {
                String[] splitted = nextLine.split(" : ");
                stringList.add(splitted[1]);
            }
        }
        if(stringList.isEmpty()) {
            System.out.println("Diese Liste ist leer. Es wurden keine Elemente gefunden!");
        }
        reader.close();
        return stringList;


    }



    public ArrayList<String> readCfg(String path) {

        Scanner reader = this.setReader(path);
        String str;
        ArrayList<String> stringArray = new ArrayList<>();
        while(reader.hasNextLine()) {
            stringArray.add(reader.nextLine());
        }
        return stringArray;


    }


}