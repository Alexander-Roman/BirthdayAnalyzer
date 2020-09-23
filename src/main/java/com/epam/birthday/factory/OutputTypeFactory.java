package com.epam.birthday.factory;

import com.epam.birthday.view.BirthdayInfoOutputFactory;
import com.epam.birthday.view.ConsoleBirthdayInfoOutputFactory;

public class OutputTypeFactory {

    public static BirthdayInfoOutputFactory createBirthdayInfoOutputFactoryByOutputType(OutputType outputType) {
        switch (outputType) {
            case CONSOLE:
                return new ConsoleBirthdayInfoOutputFactory();
            default:
                throw new RuntimeException(outputType.name() + " is unknown OutputType");
        }
    }
}
