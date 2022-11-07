package org.example;

import java.nio.*;

public class Pokemon {

    //Declaring the descriptive variables for the Pokemon object
    int level;
    String type;
    double height;
    double weight;
    public Pokemon (int level, String type, double height, double weight) {

        //Intializing the descriptive variables for the Pokemon object
        this.level = level;
        this.type = type;
        this.height = height;
        this.weight = weight;

    }

    //Method that writes the components of the object to a csv file
    public static void writeToFile(Pokemon pokemon) {

        System.out.print("Giraffe");
    }

}