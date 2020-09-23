/*
 * Создать приложение, получающее дату рождения (день, месяц и год) и определяющее в какой день недели Вы родились,
 * сколько Вам полных лет и поздравляет с днем рождения, если оно сегодня.
 */
package com.epam.birthday.main;

import com.epam.birthday.data.DateProvider;
import com.epam.birthday.data.DateProviderFactory;
import com.epam.birthday.factory.InputType;
import com.epam.birthday.factory.InputTypeFactory;
import com.epam.birthday.factory.OutputType;
import com.epam.birthday.factory.OutputTypeFactory;
import com.epam.birthday.view.BirthdayInfoOutput;
import com.epam.birthday.view.BirthdayInfoOutputFactory;

import java.time.LocalDate;

public class Runner {

    public static void main(String[] args) {

        String ioType = args[0];
        //String ioType = "CONSOLE";

        InputType inputType = InputType.valueOf(ioType);
        DateProviderFactory dateProviderFactory = InputTypeFactory.createDateProviderFactoryByInputType(inputType);
        DateProvider dateProvider = dateProviderFactory.createDateProvider();

        LocalDate birthday = dateProvider.getLocalDate();

        OutputType outputType = OutputType.valueOf(ioType);
        BirthdayInfoOutputFactory birthdayInfoOutputFactory = OutputTypeFactory.createBirthdayInfoOutputFactoryByOutputType(outputType);
        BirthdayInfoOutput birthdayInfoOutput = birthdayInfoOutputFactory.createBirthdayInfoOutput();

        birthdayInfoOutput.outputBirthdayInfo(birthday);
    }

}
