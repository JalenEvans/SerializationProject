package org.example;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    public static List<Pokemon> pokeList = new ArrayList<>();;
    public static List<Pokemon> newPokeList = new ArrayList<>();;

    @BeforeAll
    public static void setup()
            throws IOException {
        System.out.println("Starting all JUnit Tests...");

        Pokemon charmander = new Pokemon(11, "fire", 0.6, 8.5);
        Pokemon squirtle = new Pokemon(13, "water", 0.5, 9.0);
        Pokemon bulbasaur = new Pokemon(12, "grass", 0.7, 6.9);
        Pokemon arceus = new Pokemon(80, "normal", 3.2, 320.0);

        pokeList.add(charmander);
        pokeList.add(squirtle);
        pokeList.add(bulbasaur);
        pokeList.add(arceus);

        Collections.sort(pokeList);

        Pokemon.serializeToCSV(pokeList, "./csvFiles/PokeDex.csv");
        System.out.println("break");
        newPokeList = Pokemon.deserializePokemon("./csvFiles/PokeDex.csv");
        //newPokeList.add(arceus);
    }

    @Test
    public void pokeListEquals() {
        List<Pokemon> expectedList = new ArrayList<>();
        expectedList = pokeList;

        for (Pokemon poke : pokeList) {
            System.out.println(poke.printToCSV());
        }

        List<Pokemon> actualList = new ArrayList<>();
        actualList = newPokeList;

        for (Pokemon poke : newPokeList) {
            System.out.println(poke.printToCSV());
        }

        assertEquals(expectedList, actualList);
    }

}