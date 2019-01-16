package com.fraccalc;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //looper();
        //parser();
        littleCalc(1,-2, '-', 3, 5);
        System.out.println("Program ended");
    }

    public static void looper() {
        Scanner userInput = new Scanner(System.in);
        String input;

        //initial input request
        System.out.print("Enter something: ");
        input = userInput.nextLine();

        while (!input.equals("quit")) {
            String[] tokens = split(input, ' ');
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
            if(isFractionParseable(input)) {
                System.out.println(parseWhole(input));
                System.out.println(parseNumerator(input));
                System.out.println(parseDenominator(input));
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
        int resultNumerator;
        int resultDenominator;

        if(operator == '+') {
            resultNumerator = numerator1 * denominator2 + numerator2 * denominator1;
            resultDenominator = denominator1 * denominator2;
        }
        else if(operator == '-') {
            resultNumerator = numerator1 * denominator2 - numerator2 * denominator1;
            resultDenominator = denominator1 * denominator2;
        }
        else if(operator == '*') {
            resultNumerator = numerator1 * numerator2;
            resultDenominator = denominator1 * denominator2;
        }
        else if(operator == '/') {
            resultNumerator = numerator1 *  denominator2;
            resultDenominator = denominator1 * numerator2;
        }
        else {
            System.out.println("Operation not recognized.");
            return;
        }
        String output = fractionString(numerator1, denominator1);
        output += " " + operator + " ";
        output += fractionString(numerator2, denominator2);
        output += " = " + properFractionString(resultNumerator, resultDenominator);
        System.out.println(output);
    }

    public static String properFractionString(int numerator, int denominator) {
        int sign = (Math.abs(numerator) / numerator) * (Math.abs(denominator) / denominator);
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        int whole = 0;
        while(numerator > denominator) {
            numerator = numerator - denominator;
            whole += 1;
        }

        if(whole == 0) {
            return (sign * numerator) + "/" + denominator;
        }
        else {
            return (sign * whole) + "_" + numerator + "/" + denominator;
        }
    }

    public static String fractionString(int numerator, int denominator) {
        return numerator + "/" + denominator;
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

    public static boolean isInt(String string, boolean canBeNegetive)
    {
        int i = 0;
        if(canBeNegetive && string.charAt(0) == '-') {
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
        String[] tokens = split(input, ' ');
        if (tokens.length != 3) {
            System.out.println("ERROR: the input does not contain exactly 3 tokens.");
        } else {
            for (String token : tokens) {
                System.out.println(token);
            }
        }
    }

    public static boolean isFractionFormatWholeAndFraction(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        boolean rtn = 0 < underscoreIndex; //there is underscore and it must not be the first character
        rtn = rtn && underscoreIndex + 1 < slashIndex; //following the underscore and at least one character, there must be a slash
        rtn = rtn && slashIndex < fraction.length() - 1; //the slash must not be the last character
        rtn = rtn && fraction.indexOf("_", underscoreIndex + 1) == -1; //there cannot be a second underscore
        rtn = rtn && fraction.indexOf("/", slashIndex + 1) == -1; //there cannot be a second slash
        return rtn;
    }

    public static boolean isFractionFormatOnlyFraction(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        boolean rtn = underscoreIndex < 0; //there must be no underscore
        rtn = rtn && 0 < slashIndex; //there must be a slash and it must not be the first character
        rtn = rtn && slashIndex < fraction.length() - 1; //it must not be the last character
        rtn = rtn && fraction.indexOf("/", slashIndex + 1) == -1; //there cannot be a second slash
        return rtn;
    }

    public static boolean isFractionFormatOnlyWhole(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");
        return underscoreIndex < 0 && slashIndex < 0; //there must be no underscore or slash
    }

    public static boolean isFractionParseable(String fraction)
    {
        String whole = getWholeString(fraction);
        String numerator = getNumeratorString(fraction);
        String denominator = getDenominatorString(fraction);

        boolean isParseable = whole != null || numerator != null || denominator != null;
        isParseable = isParseable && (whole == null || isInt(whole, true));
        isParseable = isParseable && (numerator == null || (whole == null && isInt(numerator, true)) || (whole != null && isInt(numerator, false)));
        isParseable = isParseable && (denominator == null || isInt(denominator, false));

        return isParseable;
    }

    public static String getWholeString(String fraction) {
        int underscoreIndex = fraction.indexOf("_");

        if(isFractionFormatWholeAndFraction(fraction)) {
            //it has a whole, numerator, and denominator
            return fraction.substring(0, underscoreIndex);
        }
        else if(isFractionFormatOnlyWhole(fraction)) {
            //its a whole with no fraction
            return fraction;
        }
        return null;
    }

    public static String getNumeratorString(String fraction) {
        int underscoreIndex = fraction.indexOf("_");
        int slashIndex = fraction.indexOf("/");

        if(isFractionFormatWholeAndFraction(fraction)) {
            return fraction.substring(underscoreIndex+1, slashIndex);
        }
        else if(isFractionFormatOnlyFraction(fraction)) {
            return fraction.substring(0, slashIndex);
        }
        return null;
    }

    public static String getDenominatorString(String fraction) {
        int slashIndex = fraction.indexOf("/");

        if(isFractionFormatWholeAndFraction(fraction) || isFractionFormatOnlyFraction(fraction))
        {
            return fraction.substring(slashIndex+1);
        }
        return null;
    }

    public static int parseWhole(String fraction) {
        String wholeString = getWholeString(fraction);
        if( wholeString != null) {
            return Integer.parseInt(wholeString);
        }
        return 0;
    }

    public static int parseNumerator(String fraction) {
        String numeratorString = getNumeratorString(fraction);

        if(numeratorString != null) {
            int whole = parseWhole(fraction);

            int sign = 1;
            if (whole != 0) {
                sign = whole / Math.abs(whole);
            }
            return sign * Integer.parseInt(numeratorString);
        }
        return 0;
    }

    public static int parseDenominator(String fraction) {
        String denominatorString = getDenominatorString(fraction);
        if( denominatorString != null) {
            return Integer.parseInt(denominatorString);
        }
        return 1;
    }
}
