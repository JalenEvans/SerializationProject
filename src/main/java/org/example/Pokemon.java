package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pokemon {

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
    public static void serializeToCSV(Pokemon pokemon, String filename)
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
        String attributes = Files.readAllLines(pokedex).get(0);
        return attributes;

    }

    //Takes the data from PokeDex.csv and then applies it to another new object
    public static Pokemon deserializePokemon(String filename) throws IOException {

        String data = readFile(filename);
        String[] dataParts = data.split(",");
        Pokemon pokemon = new Pokemon(Integer.parseInt(dataParts[0]), dataParts[1], Double.parseDouble(dataParts[2]), Double.parseDouble(dataParts[3]));
        return pokemon;

    }

    //Seperates the values of the pokemon object into comma separated values (csv)
    public String printForCSV() {
        return "" + level + "," + type + "," + height + "," + weight;
    }

    public boolean equals(Object poke) {

        String[] ogPokemon = printForCSV().split(",");

        Pokemon newPoke = (Pokemon) poke;

        String[] newPokemon = newPoke.printForCSV().split(",");

        for (int i = 0; i < ogPokemon.length; i++) {
            if (!(ogPokemon[i].equals(newPokemon[i]))) {
                System.out.println("Not Equal!");
                return false;
            }
        }

        System.out.println("Equal!");
        return true;
    }
}