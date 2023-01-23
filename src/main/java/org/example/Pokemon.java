package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pokemon implements Comparable <Pokemon> {

    //Declaring the descriptive variables for the Pokemon object
    private final int level;
    private final String type;
    private final double height;
    private final double weight;

    private Pokemon() {
        this(0, "", 0.0,0.0);
    }

    public Pokemon(int level, String type, double height, double weight) {

        //Intializing the descriptive variables for the Pokemon object
        this.level = level;
        this.type = type;
        this.height = height;
        this.weight = weight;

    }

    //Converts object's attributes into a byte array
    public static byte[] objectToBytes(Pokemon pokemon) {

        //Separates the values of the pokemon object into comma separated values (csv)
        String attributes = ("" + pokemon.getLevel() + "," + pokemon.getType() + "," + pokemon.getHeight() + "," + pokemon.getWeight());

        byte[] strToBytes = attributes.getBytes();

        return strToBytes;
    }

    //Method that writes the components of the object to a csv file
    public static void serializeToCSV(List<Pokemon> pokeList, String filename)
            throws IOException {

        //Creates a path to access the file
        Path pokedex = Paths.get(filename);

        String newLine = System.lineSeparator();
        byte[] byteLine = newLine.getBytes();

        String blank = "";
        byte[] blankBytes = blank.getBytes();

        //Clears the csv file
        Files.write(pokedex, blankBytes);

        Collections.sort(pokeList);

        //Writes the attributes of the different objects in the list to file
        for (int i = 0; i < pokeList.size(); i++) {
            Files.write(pokedex, objectToBytes(pokeList.get(i)), StandardOpenOption.APPEND);
            if (i != (pokeList.size() - 1)) {
                Files.write(pokedex, byteLine, StandardOpenOption.APPEND);
            }
        }

    }

    //Counts the amount of lines in a file
    public static int countLines(String filename)
        throws IOException {

        Path pokedex = Paths.get(filename);

        long lines = Files.lines(pokedex).count();

        return (int) lines;
    }

    //Takes the data from filename and then applies it to another new object
    public static List<Pokemon> deserializePokemon(String filename) throws IOException {

        List<Pokemon> pokeList = new ArrayList<>();

        Path pokedex = Paths.get(filename);

        String data = "";

        String[] dataParts = {};

        for (int i = 0; i < countLines(filename); i++) {
            data = Files.readAllLines(pokedex).get(i);
            dataParts = data.split(",");

            pokeList.add(new Pokemon(Integer.parseInt(dataParts[0]), dataParts[1], Double.parseDouble(dataParts[2]), Double.parseDouble(dataParts[3])));
        }

        return pokeList;
    }

    public String printToCSV () {
        return ("" + level + "," + type + "," + height + "," + weight);
    }

    //Checks to see if the new Pokemon object is equal to the original one
    @Override
    public boolean equals (Object poke) {

        if (poke == this) {
            return true;
        }

        if (!(poke instanceof Pokemon)) {
            return false;
        }

        Pokemon other = (Pokemon) poke;

        boolean typeEquals = ((this.type == null) && (other.type == null)) || ((this.type != null) && (other.type.equals(this.type)));

        return ((this.level == other.level) && (this.height == other.height)) && ((this.weight == other.weight) && typeEquals);
    }
    //Getters
    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Pokemon other) {

        boolean one =
                //True if both weight and height are greater
                ((Double.compare(this.weight, other.weight)) == 1) && ((Double.compare(this.height, other.height)) == 1) ||
                //True if weight is greater but height is equal
                ((Double.compare(this.weight, other.weight)) == 1) && ((Double.compare(this.height, other.height)) == 0) ||
                //True if weight is equal but height is greater
                ((Double.compare(this.weight, other.weight)) == 0) && ((Double.compare(this.height, other.height)) == 1);

        boolean negativeOne =
                //True if weight is equal but height is less
                ((Double.compare(this.weight, other.weight)) == 0) && ((Double.compare(this.height, other.height)) == -1) ||
                //True if weight is less but height is equal
                ((Double.compare(this.weight, other.weight)) == -1) && ((Double.compare(this.height, other.height)) == 0) ||
                //True if weight is less but height is less
                ((Double.compare(this.weight, other.weight)) == -1) && ((Double.compare(this.height, other.height)) == -1);

        System.out.println(one + " + " + negativeOne);

        if (one) {
            return 1;
        }
        else if (negativeOne) {
            return -1;
        }
        else {
            //True if both weight and height are equal
            //True if weight is greater but height is less
            //True if weight is less but height is greater
            return 0;
        }
//        return Double.compare(this.weight, other.weight);
    }

}