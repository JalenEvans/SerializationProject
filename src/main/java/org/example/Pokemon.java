package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Pokemon implements Comparable <Pokemon> {

    //Declaring the descriptive variables for the Pokemon object
    private final int level;
    private final String type;
    private final double height;
    private final double weight;


    public Pokemon(int level, String type, double height, double weight) {

        //Intializing the descriptive variables for the Pokemon object
        this.level = level;
        this.type = type;
        this.height = height;
        this.weight = weight;

    }

    //Method that writes the components of the object to a csv file
    public void serializeToCSV(Pokemon pokemon, String filename)
            throws IOException {

        String attributes = pokemon.printForCSV();

        //Creates a path to access the file Pokedex.csv
        Path pokedex = Paths.get(filename);
        byte[] strToBytes = attributes.getBytes();

        //Writes the attributes of pokemon to PokeDex.csv
        Files.write(pokedex, strToBytes);

    }

    //Reads and then returns the contents of PokeDex.csv
    public static String readFile(String filename) throws IOException {

        Path pokedex = Paths.get(filename);
        return Files.readAllLines(pokedex).get(0);

    }

    //Takes the data from PokeDex.csv and then applies it to another new object
    public Pokemon deserializePokemon(String filename) throws IOException {

        String data = readFile(filename);
        String[] dataParts = data.split(",");
        return new Pokemon(Integer.parseInt(dataParts[0]), dataParts[1], Double.parseDouble(dataParts[2]), Double.parseDouble(dataParts[3]));

    }

    //Seperates the values of the pokemon object into comma separated values (csv)
    public String printForCSV() {
        return "" + level + "," + type + "," + height + "," + weight;
    }

    //Checks to see if the new Pokemon object is equal to the original one
    public boolean equals (Object poke) {

        if (poke == this) {
            return true;
        }

        if (!(poke instanceof Pokemon)) {
            return false;
        }

        Pokemon other = (Pokemon) poke;

        boolean typeEquals = (this.type == null & other.type == null) || (this.type != null && other.type.equals(this.type));

        return (this.level == other.level && this.height == other.height) && (this.weight == other.weight && typeEquals);
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
    public int compareTo(Pokemon o) {
        return 0;
    }

}