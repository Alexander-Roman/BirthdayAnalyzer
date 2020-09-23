package com.epam.birthday.view;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ConsoleBirthdayInfoOutputMockTest {

    private static ConsoleBirthdayInfoOutput consoleBirthdayInfoOutput;
    private static Logger mockLogger;

    @BeforeClass
    public static void setup() {
        consoleBirthdayInfoOutput = new ConsoleBirthdayInfoOutput();
        mockLogger = Mockito.mock(Logger.class);
        ConsoleBirthdayInfoOutput.logger = mockLogger;
    }

    @AfterClass
    public static void tearDown() {
        consoleBirthdayInfoOutput = null;
    }

    @Before
    public void setupEach() {
        Mockito.reset(mockLogger);
    }

    @Test
    public void outputBirthdayDateTestShouldPrintToConsoleBirthdayDate() {
        LocalDate birthday = LocalDate.of(1988, 5, 19);
        consoleBirthdayInfoOutput.outputBirthdayDate(birthday);

        Mockito.verify(mockLogger).log(Level.INFO, "Your date of birth: 1988-05-19");
    }

    @Test
    public void outputDayOfWeekTestShouldPrintToConsoleDayOfWeek() {
        LocalDate birthday = LocalDate.of(1988, 5, 19);
        consoleBirthdayInfoOutput.outputDayOfWeek(birthday);

        Mockito.verify(mockLogger).log(Level.INFO, "Day of the week: THURSDAY");
    }

    @Test
    public void outputAgeTestShouldPrintAgeIfAgeIsPositive() {
        LocalDate birthday = LocalDate.now().minusYears(42);
        consoleBirthdayInfoOutput.outputAge(birthday);

        Mockito.verify(mockLogger).log(Level.INFO, "Your age is 42");
    }

    @Test
    public void outputAgeTestShouldPrintAlertIfAgeIsNegative() {
        LocalDate birthday = LocalDate.now().plusDays(1);
        consoleBirthdayInfoOutput.outputAge(birthday);

        Mockito.verify(mockLogger).log(Level.INFO, "You haven't been born yet!");
    }

    @Test
    public void wishHappyBirthdayTestShouldPrintCongratulationIfBirthdayToday() {
        LocalDate birthday = LocalDate.now();
        consoleBirthdayInfoOutput.wishHappyBirthday(birthday);

        Mockito.verify(mockLogger).log(Level.INFO, "Happy Birthday!");
    }

    @Test
    public void wishHappyBirthdayTestShouldDoNothingIfBirthdayIsNotToday() {
        LocalDate birthday = LocalDate.now().minusDays(42);
        consoleBirthdayInfoOutput.wishHappyBirthday(birthday);

        Mockito.verify(mockLogger, Mockito.never()).log(Mockito.any(), Mockito.anyString());
    }

}
