package com.fraccalc;

import java.util.Scanner;
import java.util.ArrayList;

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

            /*
            if(isFractionFormattedProperly(input)) {
                System.out.println(parseWhole(input));
                System.out.println(parseNumerator(input));
            }
            else {
                System.out.println("ERROR: fraction is improperly formatted.");
            }*/

            //start the loop again
            System.out.println("Enter something: ");
            input = userInput.nextLine();
        }
    }

    public static String[] split(String string, char c) {
        ArrayList<String> tokens = new ArrayList<String>();
        int currentIndex = 0;
        int charIndex = string.indexOf(c);

        if(charIndex == -1) {
            String[] array = {string};
            return array;
        }

        while(currentIndex < string.length()) {

            String token;
            if(charIndex == -1) {
                token = string.substring(currentIndex);
                currentIndex = string.length();
                tokens.add(token);
            }
            else if(currentIndex == charIndex) {
                currentIndex = charIndex + 1;
            }
            else if(currentIndex < charIndex) {
                token = string.substring(currentIndex, charIndex);
                currentIndex = charIndex + 1;
                tokens.add(token);
            }

            charIndex = string.indexOf(c, currentIndex);
        }

        return tokens.toArray(new String[0]);
    }

    public static boolean isInt(String string)
    {
        int i = 0;
        if(string.charAt(0) == '-') {
            i++;
        }

        for(; i < string.length(); i++) {
            if(!isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(char c) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for(char digit : digits) {
            if( digit == c) {
                return true;
            }
        }
        return false;
    }

    public static void handleInput(String input) {
        String test = "";
        for (String token : test.split(" ")) {
            System.out.println(token);
        }

        String[] tokens = split(input, ' ');
        //String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            System.out.println("ERROR: the input does not contain exactly 3 tokens.");
        } else {
            for (String token : tokens) {
                System.out.println(token);
            }
            //parseWhole(tokens[0]);
        }
    }

    public static boolean isFractionWholeAndFraction(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        boolean rtn = 0 < underscoreIndex; //there is underscore and it must not be the first character
        rtn = rtn && underscoreIndex + 1 < slashIndex; //following the underscore and at least one character, there must be a slash
        rtn = rtn && slashIndex < fraction.length() - 1; //the slash must not be the last character
        rtn = rtn && fraction.indexOf("_", underscoreIndex + 1) == -1; //there cannot be a second underscore
        rtn = rtn && fraction.indexOf("/", slashIndex + 1) == -1; //there cannot be a second slash
        return rtn;
    }

    public static boolean isFractionOnlyFraction(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        boolean rtn = underscoreIndex < 0; //there must be no underscore
        rtn = rtn && 0 < slashIndex; //there must be a slash and it must not be the first character
        rtn = rtn && slashIndex < fraction.length() - 1; //it must not be the last character
        rtn = rtn && fraction.indexOf("/", slashIndex + 1) == -1; //there cannot be a second slash
        return rtn;
    }

    public static boolean isFractionOnlyWhole(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");
        return underscoreIndex < 0 && slashIndex < 0;
    }

    public static String getWholeString(String fraction) {
        int underscoreIndex = fraction.indexOf("_");

        if(isFractionWholeAndFraction(fraction)) {
            //it has a whole, numerator, and denominator
            return fraction.substring(0, underscoreIndex);
        }
        else if(isFractionOnlyWhole(fraction)) {
            //its a whole with no fraction
            return fraction;
        }
        return "";
    }

    public static String getNumeratorString(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        if(isFractionWholeAndFraction(fraction)) {
            return fraction.substring(underscoreIndex+1, slashIndex);
        }
        else if(isFractionOnlyFraction(fraction)) {
            return fraction.substring(0, slashIndex);
        }
        return "";
    }

    public static String getDenominatorString(String fraction) {
        int slashIndex = fraction.indexOf("/");

        if(isFractionWholeAndFraction(fraction) || isFractionOnlyFraction(fraction))
        {
            return fraction.substring(slashIndex+1);
        }
        return "";
    }

        //Checks to make sure the underscore and slash in the fraction string are in valid locations.
    //does not check if what is between the underscore and slash is parseable
    public static boolean isFractionFormattedProperly(String fraction)
    {
        if(isFractionWholeAndFraction(fraction)) {
            //it has a whole, numerator, and denominator
            String whole = getWholeString(fraction);
            String numerator = getNumeratorString(fraction);
            String denominator = getDenominatorString(fraction);
            return isInt(whole) && isInt(numerator) && isInt(denominator);
        }
        else if (isFractionOnlyFraction(fraction)) {
            //numerator and denominator with no whole
            String numerator = getNumeratorString(fraction);
            String denominator = getDenominatorString(fraction);
            return isInt(numerator) && isInt(denominator);
        }
        else if(isFractionOnlyWhole(fraction)) {
           //its a whole with no fraction
            String whole = getWholeString(fraction);
            return isInt(whole);
        }

        return false;
    }

    //TODO: refactor parse methods
    public static int parseWhole(String fraction)
    {
        int underscoreIndex = fraction.indexOf("_");

        if(isFractionWholeAndFraction(fraction)) {
            //it has a whole, numerator, and denominator
            String wholeString = fraction.substring(0, underscoreIndex);
            return Integer.parseInt(wholeString);
        }
        else if(isFractionOnlyWhole(fraction)) {
            //its a whole with no fraction
            return Integer.parseInt(fraction);
        }
        return 0;
    }

    public static int parseNumerator(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        if(isFractionWholeAndFraction(fraction)) {
            String numeratorString = fraction.substring(underscoreIndex+1, slashIndex);
            int numberatorAbsoluteValue = Integer.parseInt(numeratorString);
            int whole = parseWhole(fraction);
            return(whole / Math.abs(whole)) * numberatorAbsoluteValue;
        }
        else if(isFractionOnlyFraction(fraction)) {
            String numeratorString = fraction.substring(0, slashIndex);
            return Integer.parseInt(numeratorString);
        }
        return 0;
    }

    public static int parseDenominator(String fraction) {
        int slashIndex = fraction.indexOf("/");

        if(isFractionWholeAndFraction(fraction) || isFractionOnlyFraction(fraction))
        {
            String denominatorString = fraction.substring(slashIndex+1);
            return Integer.parseInt(denominatorString);
        }
        return 1;
    }
}
