package org.example;

public class Main {


    public static void main(String[] args) {
        Pokemon pokemon = new Pokemon(11, "fire", 11.3, 15.6);

        System.out.println("Hello world!");

        pokemon.writeToFile(pokemon);
    }
}