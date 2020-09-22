package com.epam.birthday.data;

import com.epam.birthday.data.ConsoleDateProvider;
import com.epam.birthday.data.DateProvider;
import com.epam.birthday.data.DateProviderFactory;

public class ConsoleDateProviderFactory implements DateProviderFactory {

    @Override
    public DateProvider createDateProvider() {
        return new ConsoleDateProvider();
    }
}
