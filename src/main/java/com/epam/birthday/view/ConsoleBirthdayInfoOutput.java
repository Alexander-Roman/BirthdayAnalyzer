package com.epam.birthday.view;

import com.epam.birthday.logic.BirthdayAnalyzer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class ConsoleBirthdayInfoOutput implements BirthdayInfoOutput {

    static Logger logger = LogManager.getLogger();
    private final BirthdayAnalyzer birthdayAnalyzer;

    {
        birthdayAnalyzer = new BirthdayAnalyzer();
    }

    public void outputBirthdayDate(LocalDate birthday) {
        logger.log(Level.INFO, "Your date of birth: " + birthday.toString());
    }

    public void outputDayOfWeek(LocalDate birthday) {
        logger.log(Level.INFO, "Day of the week: " + birthday.getDayOfWeek());
    }

    public void outputAge(LocalDate birthday) {
        if (birthdayAnalyzer.wasBorn(birthday)) {
            int age = birthdayAnalyzer.determineAge(birthday);
            logger.log(Level.INFO, "Your age is " + age);
        } else {
            logger.log(Level.INFO, "You haven't been born yet!");
        }
    }

    public void wishHappyBirthday(LocalDate birthday) {
        int age = birthdayAnalyzer.determineAge(birthday);
        if (birthdayAnalyzer.isToday(birthday) && age >= 0) {
            logger.log(Level.INFO, "Happy Birthday!");
        }
    }

    @Override
    public void outputBirthdayInfo(LocalDate birthday) {
        outputBirthdayDate(birthday);
        outputDayOfWeek(birthday);
        outputAge(birthday);
        wishHappyBirthday(birthday);
    }
}
