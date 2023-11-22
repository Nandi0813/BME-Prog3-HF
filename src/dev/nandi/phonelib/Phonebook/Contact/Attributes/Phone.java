package dev.nandi.phonelib.Phonebook.Contact.Attributes;

import java.io.Serializable;

public class Phone implements Serializable
{

    private final int countryCode;
    private final int number;

    public Phone(int countryCode, int number)
    {
        this.countryCode = countryCode;
        this.number = number;
    }

    public int getCountryCode() { return countryCode; }
    public int getNumber() { return number; }

    public String toString()
    {
        return "+" + countryCode + number;
    }

}
