package com.epam.birthday.logic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

/**
 * The class defines logical operations on birthday dates
 */
public class BirthdayAnalyzer {

    /**
     * Sequential number for February 29th in a leap year
     */
    private final static int DAY_OF_YEAR_FEBRUARY_29 = 60;

    /**
     * Returns the day of the week the person was born
     *
     * @param birthday LocalDate
     * @return DayOfWeek day of the week
     */
    public DayOfWeek determineDayOfWeek(LocalDate birthday) {
        return birthday.getDayOfWeek();
    }

    /**
     * Determines whether the birth took place
     *
     * @param birthday LocalDate
     * @return true - if the person has already been born today or earlier
     * false - if the birthday is in the future
     */
    public boolean wasBorn(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return birthday.isBefore(today) || birthday.equals(today);
    }

    /**
     * Calculates and returns the age of a person based on a given birthday
     *
     * @param birthday LocalDate
     * @return int age
     */
    public int determineAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthday, today);
        return period.getYears();
    }

    /**
     * Calculates if a birthday is celebrated today
     * For birthday February 29th returns true on March 1st of a common year
     *
     * @param birthday LocalDate
     * @return true - if birthday is celebrated today
     */
    public boolean isToday(LocalDate birthday) {
        LocalDate today = LocalDate.now();

        if (birthday.isLeapYear() && birthday.getDayOfYear() == DAY_OF_YEAR_FEBRUARY_29) {
            return today.getDayOfYear() == DAY_OF_YEAR_FEBRUARY_29;
        }

        return birthday.getMonthValue() == today.getMonthValue() &&
                birthday.getDayOfMonth() == today.getDayOfMonth();
    }

}
