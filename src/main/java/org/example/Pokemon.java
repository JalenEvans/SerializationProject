package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pokemon implements Comparable<Pokemon> {

    //Declaring the descriptive variables for the Pokemon object
    private final int level;
    private final String type;
    private final double height;
    private final double weight;

    private Pokemon() {
        this(0, "", 0.0, 0.0);
    }

    public Pokemon(int level, String type, double height, double weight) {

        //Intializing the descriptive variables for the Pokemon object
        this.level = level;
        this.type = type;
        this.height = height;
        this.weight = weight;

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
        Files.write(pokedex, "".getBytes());

        Collections.sort(pokeList);

        //Writes the attributes of the different objects in the list to file
        for (Pokemon poke : pokeList) {
            Files.write(pokedex, poke.printToCSV().getBytes(), StandardOpenOption.APPEND);
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

        List<String> data = Files.readAllLines(pokedex);

        String[] dataParts = {};

        for (String line : data) {
            dataParts = line.split(",");

            pokeList.add(new Pokemon(Integer.parseInt(dataParts[0].trim()), dataParts[1].trim(), Double.parseDouble(dataParts[2].trim()), Double.parseDouble(dataParts[3].trim())));
        }

        return pokeList;
    }

    public String printToCSV() {
        return ("" + level + "," + type + "," + height + "," + weight + "\n");
    }

    //Checks to see if the new Pokemon object is equal to the original one
    @Override
    public boolean equals(Object poke) {

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
    public int compareTo (Pokemon other) {

        boolean same =
                ((Double.compare(this.weight, other.weight)) == 0) &&
                        ((Double.compare(this.height, other.height)) == 0);

        boolean greater =
                ((Double.compare(this.weight, other.weight)) >= 0) &&
                        ((Double.compare(this.height, other.height)) >= 0);

        System.out.println(greater + " " + same);

        return same ? 0 : greater ? 1 : -1;
    }

//    @Override
//    public int compareTo (Pokemon other) {
//        return Double.compare(this.height, other.height);
//    }

    //Comment to test .gitignore file



}