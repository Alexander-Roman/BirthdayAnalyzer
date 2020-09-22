package com.epam.birthday.data;

public class ConsoleDateProviderFactory implements DateProviderFactory {

    @Override
    public DateProvider createDateProvider() {
        return new ConsoleDateProvider();
    }
}
