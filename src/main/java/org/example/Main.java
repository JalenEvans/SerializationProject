package org.example;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Pokemon pokemon = new Pokemon(11, "fire", 11.3, 15.6);

        pokemon.serializeToCSV(pokemon, "./csvFiles/PokeDex.csv");

        Pokemon pokemon2 = pokemon.deserializePokemon("./csvFiles/PokeDex.csv");

        System.out.println(pokemon.equals(pokemon2));



    }
}