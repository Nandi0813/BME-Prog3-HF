package dev.nandi.phonelib.Phonebook.Contact.Attr;

import java.io.Serializable;

public class Phone implements Serializable
{

    private int countryCode;
    private int number;

    public Phone(int countryCode, int number)
    {
        this.countryCode = countryCode;
        this.number = number;
    }

    public int getCountryCode() { return countryCode; }
    public int getNumber() { return number; }

    public void setCountryCode(int countryCode) { this.countryCode = countryCode; }
    public void setNumber(int number) { this.number = number; }

    public String toString()
    {
        return "+" + countryCode + number;
    }

}
