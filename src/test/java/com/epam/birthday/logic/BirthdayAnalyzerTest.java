package com.epam.birthday.logic;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BirthdayAnalyzerTest {

    private static BirthdayAnalyzer birthdayAnalyzer;

    @BeforeClass
    public static void setup() {
        birthdayAnalyzer = new BirthdayAnalyzer();
    }

    @AfterClass
    public static void tearDown() {
        birthdayAnalyzer = null;
    }

    @Test
    public void determineDayOfWeekTestShouldReturnNotNull() {
        LocalDate localDate = LocalDate.now();
        DayOfWeek actual = birthdayAnalyzer.determineDayOfWeek(localDate);

        Assert.assertNotNull(actual);
    }

    @Test
    public void determineDayOfWeekTestShouldReturnCorrectDayOfWeek() {
        LocalDate localDate = LocalDate.of(2020, 2, 29);
        DayOfWeek expected = DayOfWeek.SATURDAY;
        DayOfWeek actual = birthdayAnalyzer.determineDayOfWeek(localDate);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void wasBornTestShouldReturnTrueIfDateHasCome() {
        LocalDate localDate = LocalDate.now();
        boolean actual = birthdayAnalyzer.wasBorn(localDate);

        Assert.assertTrue(actual);
    }

    @Test
    public void wasBornTestShouldReturnFalseIfDateHasNotCome() {
        LocalDate localDate = LocalDate.now().plusDays(1);
        boolean actual = birthdayAnalyzer.wasBorn(localDate);

        Assert.assertFalse(actual);
    }

    @Test
    public void determineAgeTestShouldReturnZeroIfLessThanYearHasPassed() {
        LocalDate localDate = LocalDate.now().minusYears(1).plusDays(1);
        int expected = 0;
        int actual = birthdayAnalyzer.determineAge(localDate);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void determineAgeTestShouldReturnOneIfYearHasPassed() {
        LocalDate localDate = LocalDate.now().minusYears(1);
        int expected = 1;
        int actual = birthdayAnalyzer.determineAge(localDate);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isTodayTestShouldReturnTrueIfDayAndMonthAreSame() {
        LocalDate localDate = LocalDate.now().minusYears(42);
        boolean actual = birthdayAnalyzer.isToday(localDate);

        Assert.assertTrue(actual);
    }

    @Test
    public void isTodayTestShouldReturnFalseIfDayAndMonthAreNotSame() {
        LocalDate localDate = LocalDate.now().minusYears(42).plusDays(2);
        boolean actual = birthdayAnalyzer.isToday(localDate);

        Assert.assertFalse(actual);
    }

}
