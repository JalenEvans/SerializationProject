package org.example;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    public static Pokemon pokemon;
    public static Pokemon pokemon2;

    @BeforeAll
    public static void setup()
            throws IOException {
        System.out.println("Starting all JUnit Tests...");
        pokemon = new Pokemon(11, "fire", 11.3, 15.6);
        pokemon.serializeToCSV(pokemon, "./csvFiles/PokeDex.csv");
        pokemon2 = pokemon.deserializePokemon("./csvFiles/PokeDex.csv");
    }

    @Test
    public void levelEqual() {
        int expectedLevel = pokemon.getLevel();
        int actualLevel = pokemon2.getLevel();
        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void typeEqual() {
        String expectedType = pokemon.getType();
        String actualType = pokemon2.getType();
        assertEquals(expectedType, actualType);
    }

    @Test
    public void heightEqual() {
        double expectedHeight = pokemon.getHeight();
        double actualHeight = pokemon2.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void WeightEqual() {
        double expectedWeight = pokemon.getWeight();
        double actualWeight = pokemon2.getWeight();
        assertEquals(expectedWeight, actualWeight);
    }

    @AfterAll
    public static void done() {
        System.out.println("All tests successful.");
    }
}