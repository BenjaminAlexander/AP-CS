package com.fraccalc.test;

import com.fraccalc.main.MainWithFractionObjects;
import org.junit.Assert;
import org.junit.Test;
import com.fraccalc.main.Fraction;

public class FractionTest {

    static void assertEquals(String expected, Fraction actual) {
        Assert.assertEquals(expected, actual.toString());
    }

    static void assertCalculateEquals(String expected, String actualExpression) {
        Assert.assertEquals(expected, MainWithFractionObjects.calculate(actualExpression).toString());
    }

    @Test
    public void toStringTest() {
        assertEquals("1/2", new Fraction(1, 2));
        assertEquals("0", new Fraction(0, 2));
        assertEquals("-1/2", new Fraction(-1, 2));
        assertEquals("-1/2", new Fraction(1, -2));
        assertEquals("1/2", new Fraction(-1, -2));

        assertEquals("3/5", new Fraction(12, 20));
        assertEquals("-3/5", new Fraction(-12, 20));
        assertEquals("-3/5", new Fraction(12, -20));
        assertEquals("3/5", new Fraction(-12, -20));

        assertEquals("2_3/5", new Fraction(52, 20));
        assertEquals("-2_3/5", new Fraction(-52, 20));
        assertEquals("-2_3/5", new Fraction(52, -20));
        assertEquals("2_3/5", new Fraction(-52, -20));

        assertEquals("2", new Fraction(40, 20));
        assertEquals("-2", new Fraction(-40, 20));
        assertEquals("-2", new Fraction(40, -20));
        assertEquals("2", new Fraction(-40, -20));

        assertEquals("0", new Fraction(0));
        assertEquals("13", new Fraction(13));
        assertEquals("-13", new Fraction(-13));

        assertEquals("1_1/2", new Fraction(1, 1, 2));
        assertEquals("-1/2", new Fraction(-1, 1, 2));
        assertEquals("1/2", new Fraction(1, -1, 2));
        assertEquals("1/2", new Fraction(1, 1, -2));

        assertEquals("1", new Fraction(1,0, 2));
        assertEquals("0", new Fraction(0,0, 2));
        assertEquals("1/2", new Fraction(1,-1, 2));

        assertEquals("8", new Fraction(6, 4, 2));
        assertEquals("4", new Fraction(6, -4, 2));
    }

    @Test
    public void calculateTest() {
        assertCalculateEquals("3", "1 + 2");
        assertCalculateEquals("2_1/2", "1/2 + 2");
        assertCalculateEquals("1", "2/3 + 1/3");
        assertCalculateEquals("2_7/8", "1_5/8 + 1_1/4");
        assertCalculateEquals("3_3/4", "2_3/24 + 1_15/24");
        assertCalculateEquals("3_12/17", "2_9/34 + 1_15/34");
        assertCalculateEquals("1_1/6", "3_1/3 - 2_1/6");
        assertCalculateEquals("-100", "200 - 300");
        assertCalculateEquals("-3", "-2 - 1");
        assertCalculateEquals("-1/4", "1/2 - 3/4");
        assertCalculateEquals("-1_2/3", "2_1/3 - 4");
        assertCalculateEquals("1", "3_1/5 - 2_1/5");
        assertCalculateEquals("100", "10 * 10");
        assertCalculateEquals("2", "1/2 * 4");
        assertCalculateEquals("1_1/10", "2_1/5 * 1/2");
        assertCalculateEquals("5", "3_1/3 * 1_1/2");
        assertCalculateEquals("2/7", "2 * 1/7");
        assertCalculateEquals("100", "1000 / 10");
        assertCalculateEquals("1_1/2", "3 / 2");
        assertCalculateEquals("10", "2_1/2 / 1/4");
        assertCalculateEquals("2/3", "3 / 4_1/2");
        assertCalculateEquals("1/2", "2_1/2 / 5");
        assertCalculateEquals("100", "10 * 10");
        assertCalculateEquals("0", "-2 - -2");
        assertCalculateEquals("-8_3/4", "-3_1/2 * 2_1/2");
        assertCalculateEquals("1_1/4", "-2_1/2 / -2");
        assertCalculateEquals("0", "-2_1/2 + 2_1/2");

        assertCalculateEquals("5_5/34", "2_9/34 + 1_15/34 * 2");
        assertCalculateEquals("5_305/1156", "2_9/34 * 1_15/34 + 2");
        assertCalculateEquals("-18_177/8092", "2_9/34 * 1_15/34 + 2 + -23_2/7");
        assertCalculateEquals("-16_3067/4046", "2_9/34 * 1_15/34 * 2 + -23_2/7");
        assertCalculateEquals("-21_10581/16184", "2_9/34 * 1_15/34 / 2 + -23_2/7");
        assertCalculateEquals("3_65899/188428", "2_9/34 * 1_15/34 - 2 / -23_2/7");
        assertCalculateEquals("19_3/68", "2_9/34 - 1_15/34 / 2 * -23_2/7");
    }

}
