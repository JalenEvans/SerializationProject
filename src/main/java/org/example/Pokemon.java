package org.example;

import java.io.IOException;
import java.nio.file.*;

public class Pokemon {

    //Declaring the descriptive variables for the Pokemon object
    private int level;
    private String type;
    private double height;
    private double weight;

    //Creating the file path for PokeDex.csv
    static Path pokedex;


    public Pokemon (int level, String type, double height, double weight) {

        //Intializing the descriptive variables for the Pokemon object
        this.level = level;
        this.type = type;
        this.height = height;
        this.weight = weight;

    }

    //Method that writes the components of the object to a csv file
    public static void serializeToCSV(Pokemon pokemon)
    throws IOException {
        String object = pokemon.printForCSV();

//        pokedex = Paths.get("/csvFiles/PokeDex.csv");
        pokedex = Paths.get("/src/main/java/org/example/test.txt");
        byte[] strToBytes = object.getBytes();

        Files.write(pokedex, strToBytes);

        //System.out.print(pokemon.printForCSV());
    }

    public static String printFile() throws IOException {

        String object = Files.readAllLines(pokedex).get(0);
        return object;
    }

    public String printForCSV() {
        return "" + level + "," + type + "," + height + "," + weight;
    }

}