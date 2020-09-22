package com.epam.birthday.view;

import com.epam.birthday.logic.BirthdayAnalyzer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

/**
 * The class determines the results output to the console
 */
public class ConsoleBirthdayInfoOutput implements BirthdayInfoOutput {

    /**
     * The logger is used to print information to the console
     */
    static Logger logger = LogManager.getLogger();

    /**
     * BirthdayAnalyzer instance for retrieving data
     */
    private final BirthdayAnalyzer birthdayAnalyzer;

    {
        birthdayAnalyzer = new BirthdayAnalyzer();
    }

    /**
     * Prints the full date of birth to the console
     *
     * @param birthday LocalDate
     */
    public void outputBirthdayDate(LocalDate birthday) {
        logger.log(Level.INFO, "Your date of birth: " + birthday.toString());
    }

    /**
     * Prints the day of the week of the specified date
     *
     * @param birthday LocalDate
     */
    public void outputDayOfWeek(LocalDate birthday) {
        logger.log(Level.INFO, "Day of the week: " + birthday.getDayOfWeek());
    }

    /**
     * Prints the age of the person to the console or
     * prints an alert if the person hasn't been born yet
     *
     * @param birthday LocalDate
     */
    public void outputAge(LocalDate birthday) {
        if (birthdayAnalyzer.wasBorn(birthday)) {
            int age = birthdayAnalyzer.determineAge(birthday);
            logger.log(Level.INFO, "Your age is " + age);
        } else {
            logger.log(Level.INFO, "You haven't been born yet!");
        }
    }

    /**
     * Prints congratulations to the console if a birthday is celebrated today.
     * Otherwise does nothing
     *
     * @param birthday LocalDate
     */
    public void wishHappyBirthday(LocalDate birthday) {
        int age = birthdayAnalyzer.determineAge(birthday);
        if (birthdayAnalyzer.isToday(birthday) && age >= 0) {
            logger.log(Level.INFO, "Happy Birthday!");
        }
    }

    /**
     * Prints a complete set of results to the console
     *
     * @param birthday LocalDate
     */
    @Override
    public void outputBirthdayInfo(LocalDate birthday) {
        outputBirthdayDate(birthday);
        outputDayOfWeek(birthday);
        outputAge(birthday);
        wishHappyBirthday(birthday);
    }
}
