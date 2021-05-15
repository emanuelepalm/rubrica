package json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import people.Contatti;
import people.People;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonHandler {
    private static String json = "";
    private static final String path = "C:\\Users\\lelej\\Desktop\\rubrica\\src\\main\\resources\\";
    private static String extension = ".json";
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void generateArray() {
        ArrayList<Contatti> contacts = new ArrayList<Contatti>(1);
        for(int i = 0; i < 10; i++) {
            contacts.add(Contatti.populate());
        }
        this.setJson(writeJson(contacts));
    }

    public static String writeJson(ArrayList<Contatti> people) {
        return new Gson().toJson(people);
    }
    public static ArrayList<Contatti> convertJsosn(String json) {
        return new ArrayList<Contatti>(Arrays.asList(new Gson().fromJson(json, Contatti[].class)));
    }

    public static ArrayList<Contatti> convertJson(String json) {
        ArrayList<Contatti> people = new ArrayList<Contatti>();
      try {
            people.addAll(Arrays.asList(new Gson().fromJson(json,Contatti[].class)));

       }catch (JsonSyntaxException e) {
            System.out.println("Errore formato json scorretto!!");
        }
        return people;
    }
    public static void writeFile(String fileName, ArrayList<Contatti> contattiArrayList) {
        try {
        File file = new File(path+ fileName +extension);
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
            FileWriter myWriter = new FileWriter(path + fileName + extension);
            myWriter.write(writeJson(contattiArrayList));
            myWriter.flush();
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
             else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static ArrayList<Contatti> readFile(String fileName)  {
        try {
            FileReader fileReader =new FileReader(path + fileName + extension);
            int i;
            while ((i = fileReader.read()) != -1) {
                json += ((char) i);
            }
            fileReader.close();
        }catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<Contatti>(Arrays.asList(new Gson().fromJson(json, Contatti[].class)));
    }
}
