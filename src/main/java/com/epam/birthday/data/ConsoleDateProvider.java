package com.epam.birthday.data;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Scanner;

public class ConsoleDateProvider implements DateProvider {

    private static final int FIRST_DAY_OF_EACH_MONTH = 1;
    static Logger logger = LogManager.getLogger();

    private Scanner scanner;

    {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }

        return scanner.nextInt();
    }

    public int getIntWithin(int from, int to) {
        int result;

        do {
            result = getInt();
        } while (result < from || to < result);

        return result;
    }

    public int getYear() {
        logger.log(Level.INFO, "Enter the year:");
        int year = getInt();
        logger.log(Level.INFO, "Value received: " + year);
        return year;
    }

    public Month getMonth() {
        logger.log(Level.INFO, "Enter the month:");
        int month = getIntWithin(Month.JANUARY.getValue(), Month.DECEMBER.getValue());
        logger.log(Level.INFO, "Value received: " + month);
        return Month.of(month);
    }

    public YearMonth getYearMonth() {
        int year = getYear();
        Month month = getMonth();
        return YearMonth.of(year, month);
    }

    public int getDayOfMonth(YearMonth yearMonth) {
        logger.log(Level.INFO, "Enter the day of the month:");
        int day = getIntWithin(FIRST_DAY_OF_EACH_MONTH, yearMonth.lengthOfMonth());
        logger.log(Level.INFO, "Value received: " + day);
        return day;
    }

    @Override
    public LocalDate getLocalDate() {
        YearMonth yearMonth = getYearMonth();
        int year = yearMonth.getYear();
        Month month = yearMonth.getMonth();
        int day = getDayOfMonth(yearMonth);

        return LocalDate.of(year, month, day);
    }
}
