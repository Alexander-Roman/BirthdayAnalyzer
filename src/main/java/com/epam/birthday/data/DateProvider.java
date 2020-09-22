package com.epam.birthday.data;

import java.time.LocalDate;

/**
 * Interface for receiving incoming data
 */
public interface DateProvider {

    /**
     * Getting an LocalDate object
     *
     * @return LocalDate
     */
    LocalDate getLocalDate();
}
