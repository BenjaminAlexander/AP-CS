package com.fraccalc;

import java.util.Scanner;

public class MainWithFractionObjects {

    public static void main(String[] args) {
        //looper();
        //parser();
        littleCalc(1,-2, '*', 3, 5);
        System.out.println("Program ended");
    }

    public static void looper() {
        Scanner userInput = new Scanner(System.in);
        String input;

        //initial input request
        System.out.print("Enter something: ");
        input = userInput.nextLine();

        while (!input.equals("quit")) {
            String[] tokens = Utils.split(input, ' ');
            if (tokens.length != 3) {
                System.out.println("ERROR: the input does not contain exactly 3 tokens.");
            } else {
                for (String token : tokens) {
                    System.out.println(token);
                }
            }

            //start the loop again
            System.out.print("Enter something: ");
            input = userInput.nextLine();
        }
    }

    public static void parser() {
        Scanner userInput = new Scanner(System.in);
        String input;

        //initial input request
        System.out.print("Enter something: ");
        input = userInput.nextLine();

        while (!input.equals("quit")) {
            if(Fraction.isFractionParseable(input)) {
                Fraction fraction = new Fraction(input);
                System.out.println(fraction.getProperWhole());
                System.out.println(fraction.getProperNumerator());
                System.out.println(fraction.getProperDenominator());
            }
            else {
                System.out.println("ERROR: fraction is improperly formatted.");
            }

            //start the loop again
            System.out.print("Enter something: ");
            input = userInput.nextLine();
        }
    }

    public static void littleCalc(int numerator1, int denominator1, char operator, int numerator2, int denominator2)
    {
        Fraction fraction1 = new Fraction(numerator1, denominator1);
        Fraction fraction2 = new Fraction(numerator2, denominator2);
        Fraction result;

        if(operator == '+') {
            result = fraction1.add(fraction2);
        }
        else if(operator == '-') {
            result = fraction1.subtract(fraction2);
        }
        else if(operator == '*') {
            result = fraction1.multiply(fraction2);
        }
        else if(operator == '/') {
            result = fraction1.divide(fraction2);
        }
        else {
            System.out.println("Operation not recognized.");
            return;
        }
        String output = fraction1.toString();
        output += " " + operator + " ";
        output += fraction2;
        output += " = " + result;
        System.out.println(output);
    }

    public static String fractionString(int numerator, int denominator) {
        return numerator + "/" + denominator;
    }

    public static void handleInput(String input) {
        String[] tokens = Utils.split(input, ' ');
        if (tokens.length != 3) {
            System.out.println("ERROR: the input does not contain exactly 3 tokens.");
        } else {
            for (String token : tokens) {
                System.out.println(token);
            }
        }
    }








}

