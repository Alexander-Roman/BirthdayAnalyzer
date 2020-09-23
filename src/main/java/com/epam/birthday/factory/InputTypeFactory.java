package com.epam.birthday.factory;

import com.epam.birthday.data.ConsoleDateProviderFactory;
import com.epam.birthday.data.DateProviderFactory;

public class InputTypeFactory {

    public static DateProviderFactory createDateProviderFactoryByInputType(InputType inputType) {
        switch (inputType) {
            case CONSOLE:
                return new ConsoleDateProviderFactory();
            default:
                throw new RuntimeException(inputType.name() + " is unknown InputType");
        }
    }
}
