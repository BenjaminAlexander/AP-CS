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

            handleInput(input);

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
        }
    }
}
