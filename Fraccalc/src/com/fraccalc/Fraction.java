package com.fraccalc;

public class Fraction {
    int numerator;
    int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int whole, int numerator, int denominator) {
        this(whole * denominator + numerator, denominator);
    }

    public String toString() {
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
}
