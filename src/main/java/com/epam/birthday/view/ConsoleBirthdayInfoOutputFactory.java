package com.epam.birthday.view;

public class ConsoleBirthdayInfoOutputFactory implements BirthdayInfoOutputFactory {

    @Override
    public BirthdayInfoOutput createBirthdayInfoOutput() {
        return new ConsoleBirthdayInfoOutput();
    }
}
