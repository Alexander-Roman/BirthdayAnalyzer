package com.epam.birthday.logic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class BirthdayAnalyzer {

    private final static int DAY_OF_YEAR_FEBRUARY_29 = 60;

    public DayOfWeek determineDayOfWeek(LocalDate birthday) {
        return birthday.getDayOfWeek();
    }

    public boolean wasBorn(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return birthday.isBefore(today) || birthday.equals(today);
    }

    public int determineAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthday, today);
        return period.getYears();
    }

    public boolean isToday(LocalDate birthday) {
        LocalDate today = LocalDate.now();

        if (birthday.isLeapYear() && birthday.getDayOfYear() == DAY_OF_YEAR_FEBRUARY_29) {
            return today.getDayOfYear() == DAY_OF_YEAR_FEBRUARY_29;
        }

        return birthday.getMonthValue() == today.getMonthValue() &&
                birthday.getDayOfMonth() == today.getDayOfMonth();
    }

}
