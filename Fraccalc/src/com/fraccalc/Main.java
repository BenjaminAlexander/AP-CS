package com.fraccalc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        looper();
        System.out.println("Program ended");
    }

    public static void looper() {
        Scanner userInput = new Scanner(System.in);
        String input;

        //initial input request
        System.out.println("Enter something: ");
        input = userInput.nextLine();


        while (!input.equals("quit")) {
            System.out.println("The input is: " + input);

            System.out.println(isFractionFormattedProperly(input));
            //handleInput(input);

            //start the loop again
            System.out.println("Enter something: ");
            input = userInput.nextLine();
        }
    }

    public static void handleInput(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            System.out.println("ERROR: the input does not contain exactly 3 tokens.");
        } else {
            for (String token : tokens) {
                System.out.println(token);
            }
            parseWhole(tokens[0]);
        }
    }
    //Checks to make sure the underscore and slash in the fraction string are in valid locations.
    //does not check if what is between the underscore and slash is parseable
    public static boolean isFractionFormattedProperly(String fraction)
    {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        //if there is an underscore...
        if(underscoreIndex >= 0){
            //it must not be the first character
            if(
                    underscoreIndex != 0 && //it must not be the first character
                    underscoreIndex != fraction.length() - 1 && //it must not be the last character
                    underscoreIndex + 1 < slashIndex && //following the underscore and at least one character, there must be a slash
                    slashIndex != fraction.length() - 1 && //the slash must not be the last character
                    fraction.indexOf("_", underscoreIndex + 1) == -1 && //there cannot be a second underscore
                    fraction.indexOf("/", slashIndex + 1) == -1 //there cannot be a second slash
            ) {
                //it has a whole, numerator, and denominator
                return true;
            }
        }
        else if (slashIndex >= 0) {

            if(
                    slashIndex != 0 && //it must not be the first character
                    slashIndex != fraction.length() - 1 //it must not be the last character
            ) {
                //numerator and denominator with no whole
                return true;
            }
        }
        else {
           //its a whole with no fraction
            return true;
        }

        return false;
    }

    public static int parseWhole(String fraction)
    {
        System.out.println("parseWhole: " + fraction);
        String[] tokens = fraction.split("_");
        for (String token : tokens) {
            System.out.println(token);
        }
        return 0;
    }
}
