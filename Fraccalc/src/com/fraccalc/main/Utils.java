package com.fraccalc.main;

import java.util.ArrayList;

public class Utils {

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

    public static boolean isOperator(String string) {
        if(string == null) {
            return false;
        }
        boolean isOperator = string.equals("*");
        isOperator = isOperator || string.equals("/");
        isOperator = isOperator || string.equals("+");
        isOperator = isOperator || string.equals("-");
        return isOperator;
    }

    public static boolean isProductOperator(String string) {
        if(string == null) {
            return false;
        }
        boolean isOperator = string.equals("*");
        isOperator = isOperator || string.equals("/");
        return isOperator;
    }

    public static ArrayList<Integer> factor(int number) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        if(number == 0 || number == 1) {
            factors.add(number);
            return factors;
        }

        for(int i = 2; i < number; i++) {
            while(number%i == 0) {
                factors.add(i);
                number = number/i;
            }
        }
        if(number > 1) {
            factors.add(number);
        }
        return factors;
    }
}
