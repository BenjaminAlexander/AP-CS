package com.fraccalc.main;

import java.util.Scanner;

public class MainWithFractionObjects {

    public static void main(String[] args) {
        //looper();
        parser();
        //littleCalc(1,-2, '*', 3, 5);
        //fracCalc();
        System.out.println("Program ended");
    }

    public static void looper() {
        Scanner userInput = new Scanner(System.in);
        String input;

        //initial input request
        System.out.print("Enter something: ");
        input = userInput.nextLine();

        while(!input.equals("quit")) {
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

    public static void fracCalc() {
        Scanner userInput = new Scanner(System.in);
        String input;

        //initial input request
        System.out.print("Enter something: ");
        input = userInput.nextLine();

        while(!input.equals("quit")) {
            if (!isExpressionValid(input)) {
                System.out.println("ERROR: the input does not contain a valid expression.");
            } else {
                System.out.println(calculate(input));
            }

            //start the loop again
            System.out.print("Enter something: ");
            input = userInput.nextLine();
        }
    }

    public static boolean isExpressionValid(String expression) {
        String[] tokens = Utils.split(expression, ' ');
        int currentToken = 0;
        while(currentToken < tokens.length) {
            if(Fraction.isFractionParseable(tokens[currentToken]) &&
                    (currentToken + 1 == tokens.length || (currentToken + 2 < tokens.length && Utils.isOperator(tokens[currentToken + 1])))) {
                //next
                currentToken += 2;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static Fraction calculate(String expression) {
        String[] tokens = Utils.split(expression, ' ');
        int currentToken = 0;
        Fraction sum = new Fraction(0);
        String sumOperator = "+";

        while(currentToken < tokens.length) {
            Fraction product = new Fraction((tokens[currentToken]));
            while (currentToken + 2 < tokens.length && Utils.isProductOperator(tokens[currentToken + 1])) {
                String productOperator = tokens[currentToken + 1];
                Fraction nextFraction = new Fraction(tokens[currentToken + 2]);

                if (productOperator.equals("*")) {
                    product = product.multiply(nextFraction);
                }
                else if (productOperator.equals("/")) {
                    product = product.divide(nextFraction);
                }
                currentToken +=2;
            }

            if(sumOperator.equals("+")) {
                sum = sum.add(product);
            }
            else if(sumOperator.equals("-")) {
                sum = sum.subtract(product);
            }

            if(currentToken + 2 < tokens.length) {
                sumOperator = tokens[currentToken + 1];
            }
            currentToken += 2;
        }
        return sum;
    }
}

