package com.epam.birthday.view;

import java.time.LocalDate;

/**
 * Interface for outputting the results
 */
public interface BirthdayInfoOutput {

    /**
     * Outputs the results of the program
     *
     * @param birthday LocalDate
     */
    void outputBirthdayInfo(LocalDate birthday);
}
