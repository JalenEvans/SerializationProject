package org.example;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Pokemon pokemon = new Pokemon(11, "fire", 11.3, 15.6);

        System.out.println("Hello world!");

        System.out.println(pokemon.printFile());

        //Pokemon.serializeToCSV(pokemon);
    }
}