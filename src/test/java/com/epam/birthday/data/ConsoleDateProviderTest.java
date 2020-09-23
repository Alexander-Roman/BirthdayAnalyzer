package com.epam.birthday.data;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Scanner;

public class ConsoleDateProviderTest {

    private static ConsoleDateProvider consoleDateProvider;

    @BeforeClass
    public static void setUp() {
        consoleDateProvider = new ConsoleDateProvider();
    }

    @AfterClass
    public static void tearDown() {
        consoleDateProvider = null;
    }

    /**
     * Replaces the original Scanner that reads keyboard input from System.in InputStream
     * The new scanner uses ByteArrayInputStream.
     * String is used as sequence of values to be retrieved, separated from each other by spaces.
     *
     * @param source values to be retrieved by new Scanner
     */
    public void setupStringSourceScanner(String source) {
        byte[] inputBuffer = source.getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputBuffer);
        Scanner fakeScanner = new Scanner(inputStream);
        consoleDateProvider.setScanner(fakeScanner);
    }

    @Test
    public void getIntTestShouldReturnInt() {
        setupStringSourceScanner("String 42.0 42");
        int expected = 42;
        int actual = consoleDateProvider.getInt();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIntWithinTestShouldReturnIntInGivenInterval() {
        setupStringSourceScanner("String 42.0 42 -1 0 31");
        int expected = 31;
        int actual = consoleDateProvider.getIntWithin(1, 31);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIntWithinTestShouldReturnIntInGivenIntervalIncludingBottomLimit() {
        setupStringSourceScanner("String 42.0 42 -1 0 31");
        int expected = 31;
        int actual = consoleDateProvider.getIntWithin(31, 41);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getYearTestShouldReturnInt() {
        setupStringSourceScanner("String 42.0 42");
        int expected = 42;
        int actual = consoleDateProvider.getInt();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMonthTestShouldReturnNotNull() {
        setupStringSourceScanner("String 42.0 42 -1 0 13 5");
        Month actual = consoleDateProvider.getMonth();

        Assert.assertNotNull(actual);
    }

    @Test
    public void getMonthTestShouldReturnCorrectMonth() {
        setupStringSourceScanner("String 42.0 42 -1 0 13 9");
        Month expected = Month.SEPTEMBER;
        Month actual = consoleDateProvider.getMonth();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMonthTestShouldReturnJanuaryAsMonth1() {
        setupStringSourceScanner("String 42.0 42 -1 0 13 1");
        Month expected = Month.JANUARY;
        Month actual = consoleDateProvider.getMonth();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMonthTestShouldReturnDecemberAsMonth12() {
        setupStringSourceScanner("String 42.0 42 -1 0 13 12");
        Month expected = Month.DECEMBER;
        Month actual = consoleDateProvider.getMonth();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getYearMonthTestShouldReturnNotNull() {
        setupStringSourceScanner("String 42.0 42 String 42.0 -1 0 13 5");
        YearMonth actual = consoleDateProvider.getYearMonth();

        Assert.assertNotNull(actual);
    }

    @Test
    public void getYearMonthTestShouldReturnCorrectYearMonth() {
        setupStringSourceScanner("String 42.0 2020 String 42.0 -1 0 13 9");
        YearMonth expected = YearMonth.of(2020, Month.SEPTEMBER);
        YearMonth actual = consoleDateProvider.getYearMonth();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getDayOfMonthTestShouldReturnIntAboveZero() {
        setupStringSourceScanner("String 42.0 2020 -1 0 13 9");
        int actual = consoleDateProvider.getDayOfMonth(YearMonth.now());

        Assert.assertTrue(actual > 0);
    }

    @Test
    public void getDayOfMonthTestShouldReturnIntLessThan32InJanuary() {
        setupStringSourceScanner("String 42.0 2020 -1 0 32 31");
        YearMonth yearMonth = YearMonth.of(2020, Month.JANUARY);
        int actual = consoleDateProvider.getDayOfMonth(yearMonth);

        Assert.assertTrue(actual < 32);
    }

    @Test
    public void getDayOfMonthTestShouldReturnIntLessThan31InNovember() {
        setupStringSourceScanner("String 42.0 2020 -1 0 32 31 30");
        YearMonth yearMonth = YearMonth.of(2020, Month.NOVEMBER);
        int actual = consoleDateProvider.getDayOfMonth(yearMonth);

        Assert.assertTrue(actual < 31);
    }

    @Test
    public void getDayOfMonthTestShouldReturnMax29InFebruaryLeapYear() {
        setupStringSourceScanner("String 42.0 2020 -1 0 32 31 30 29 28");
        YearMonth yearMonth = YearMonth.of(2400, Month.FEBRUARY);
        int expected = 29;
        int actual = consoleDateProvider.getDayOfMonth(yearMonth);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getDayOfMonthTestShouldReturnMax28InFebruaryCommonYear() {
        setupStringSourceScanner("String 42.0 2020 -1 0 32 31 30 29 28");
        YearMonth yearMonth = YearMonth.of(2100, Month.FEBRUARY);
        int expected = 28;
        int actual = consoleDateProvider.getDayOfMonth(yearMonth);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLocalDateTestShouldReturnNotNull() {
        setupStringSourceScanner("String 42.0 2020 String 42.0 -1 0 13 7 String 42.0 -1 0 32 31 30");
        LocalDate actual = consoleDateProvider.getLocalDate();

        Assert.assertNotNull(actual);
    }

    @Test
    public void getLocalDateTestShouldReturnCorrectLocalDate() {
        setupStringSourceScanner("String 42.0 2100 String 42.0 -1 0 13 6 String 42.0 -1 0 32 31 30");
        LocalDate expected = LocalDate.of(2100, Month.JUNE, 30);
        LocalDate actual = consoleDateProvider.getLocalDate();

        Assert.assertEquals(expected, actual);
    }

}
