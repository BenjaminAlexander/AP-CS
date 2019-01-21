package com.fraccalc.main;

import java.util.ArrayList;

public class Fraction {
    final int numerator;
    final int denominator;

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

    public static boolean isFractionParseable(String fraction)
    {
        String whole = getWholeString(fraction);
        String numerator = getNumeratorString(fraction);
        String denominator = getDenominatorString(fraction);

        boolean isParseable = whole != null || numerator != null || denominator != null;
        isParseable = isParseable && (whole == null || Utils.isInt(whole, true));
        isParseable = isParseable && (numerator == null || (whole == null && Utils.isInt(numerator, true)) || (whole != null && Utils.isInt(numerator, false)));
        isParseable = isParseable && (denominator == null || Utils.isInt(denominator, false));

        return isParseable;
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

    public Fraction(int numerator, int denominator) {
        int sign = 1;
        if(numerator != 0) {
            sign = (Math.abs(numerator) / numerator) * (Math.abs(denominator) / denominator);
        }
        ArrayList<Integer> numeratorFactors = Utils.factor(Math.abs(numerator));
        ArrayList<Integer> denominatorFactors = Utils.factor(Math.abs(denominator));

        //TODO: remove common factors and multiply together
        if(numeratorFactors.get(0) == 0) {
            this.numerator = 0;
            this.denominator = Math.abs(denominator);
            return;
        }

        int numIndex = 0;
        int denomIndex = 0;

        while(numIndex < numeratorFactors.size() && denomIndex < denominatorFactors.size()) {
            if(numeratorFactors.get(numIndex) == denominatorFactors.get(denomIndex)) {
                numeratorFactors.remove(numIndex);
                denominatorFactors.remove(denomIndex);
            }
            else if(numeratorFactors.get(numIndex) < denominatorFactors.get(denomIndex)) {
                numIndex++;
            }
            else if(numeratorFactors.get(numIndex) > denominatorFactors.get(denomIndex)) {
                denomIndex++;
            }
        }

        numerator = 1;
        for(int i : numeratorFactors) {
            numerator = numerator * i;
        }

        denominator = 1;
        for(int i : denominatorFactors) {
            denominator = denominator * i;
        }

        this.numerator = sign * Math.abs(numerator);
        this.denominator = Math.abs(denominator);
    }

    public Fraction(int whole, int numerator, int denominator) {
        this(whole * denominator + numerator, denominator);
    }

    public Fraction(int whole) {
        this(whole, 0, 1);
    }

    public Fraction(String fraction) {
        this(parseWhole(fraction), parseNumerator(fraction), parseDenominator(fraction));
    }

    public int getSign() {
        if(numerator != 0) {
            return (Math.abs(numerator) / numerator) * (Math.abs(denominator) / denominator);
        }
        else {
            return 1;
        }

    }

    public int getProperWhole() {
        int numeratorAbsoluteValue = Math.abs(numerator);
        int denominatorAbsoluteValue = Math.abs(denominator);
        int wholeAbsoluteValue = 0;
        while(numeratorAbsoluteValue >= denominatorAbsoluteValue) {
            numeratorAbsoluteValue = numeratorAbsoluteValue - denominatorAbsoluteValue;
            wholeAbsoluteValue += 1;
        }
        return getSign() * wholeAbsoluteValue;
    }

    public int getProperNumerator() {
        int numeratorAbsoluteValue = Math.abs(numerator);
        int denominatorAbsoluteValue = Math.abs(denominator);
        while(numeratorAbsoluteValue >= denominatorAbsoluteValue) {
            numeratorAbsoluteValue = numeratorAbsoluteValue - denominatorAbsoluteValue;
        }
        return getSign() * numeratorAbsoluteValue;
    }

    public int getProperDenominator() {
        return Math.abs(denominator);
    }

    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction subtract(Fraction other) {
        return this.add(other.multiply(-1));
    }

    public Fraction multiply(int number) {
        return new Fraction(this.numerator * number, this.denominator);
    }

    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction divide(Fraction other) {
        return this.multiply(other.reciprocal());
    }

    public String toString() {
        int properWhole = getProperWhole();
        int properNumerator = getProperNumerator();

        if(properWhole != 0 && properNumerator != 0) {
            return properWhole + "_" + Math.abs(properNumerator) + "/" + getProperDenominator();
        }
        else if(properWhole != 0 && properNumerator == 0) {
            return Integer.toString(properWhole);
        }
        else if(properWhole == 0 && properNumerator != 0) {
            return Math.abs(properNumerator) + "/" + getProperDenominator();
        }
        else {
            return "0";
        }
    }
}
