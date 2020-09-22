package com.epam.birthday.data;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Scanner;

/**
 * Class for receiving data using user input from the keyboard
 */
public class ConsoleDateProvider implements DateProvider {

    /**
     * Sequential number of the first day of any month
     */
    private static final int FIRST_DAY_OF_EACH_MONTH = 1;

    /**
     * The logger is used to print prompts to the console
     */
    static Logger logger = LogManager.getLogger();

    /**
     * Scanner is used to read the System.in stream
     */
    private Scanner scanner;

    {
        scanner = new Scanner(System.in);
    }

    /**
     * A regular getter is needed for testing
     *
     * @return Scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * A regular setter is needed to replace the scanner during testing
     *
     * @param scanner replacement
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * The method reads data from the stream until it receives an Integer
     *
     * @return int User entered number
     */
    public int getInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }

        return scanner.nextInt();
    }

    /**
     * The method reads data from the stream until it receives an Integer within the specified range
     *
     * @param from bottom limit of the range inclusive
     * @param to   top limit of the range inclusive
     * @return int User entered number within the range
     */
    public int getIntWithin(int from, int to) {
        int result;

        do {
            result = getInt();
        } while (result < from || to < result);

        return result;
    }

    /**
     * Prompts the user to enter a year and returns the resulting value
     *
     * @return int year entered by user
     */
    public int getYear() {
        logger.log(Level.INFO, "Enter the year:");
        int year = getInt();
        logger.log(Level.INFO, "Value received: " + year);
        return year;
    }

    /**
     * Prompts the user to enter the month number and returns the resulting value as an Month object
     *
     * @return Month by number entered by user
     */
    public Month getMonth() {
        logger.log(Level.INFO, "Enter the month:");
        int month = getIntWithin(Month.JANUARY.getValue(), Month.DECEMBER.getValue());
        logger.log(Level.INFO, "Value received: " + month);
        return Month.of(month);
    }

    /**
     * Uses methods to get the year and month, combining their results into YearMonth object
     *
     * @return YearMonth object based on user input
     */
    public YearMonth getYearMonth() {
        int year = getYear();
        Month month = getMonth();
        return YearMonth.of(year, month);
    }

    /**
     * Prompts the user to select a date within a certain month of the year
     *
     * @param yearMonth month of year to check if the input is correct
     * @return int day of the month entered by the user
     */
    public int getDayOfMonth(YearMonth yearMonth) {
        logger.log(Level.INFO, "Enter the day of the month:");
        int day = getIntWithin(FIRST_DAY_OF_EACH_MONTH, yearMonth.lengthOfMonth());
        logger.log(Level.INFO, "Value received: " + day);
        return day;
    }

    /**
     * Receives data from the user to create an LocalDate object
     *
     * @return LocalDate date entered by user
     */
    @Override
    public LocalDate getLocalDate() {
        YearMonth yearMonth = getYearMonth();
        int year = yearMonth.getYear();
        Month month = yearMonth.getMonth();
        int day = getDayOfMonth(yearMonth);

        return LocalDate.of(year, month, day);
    }
}
