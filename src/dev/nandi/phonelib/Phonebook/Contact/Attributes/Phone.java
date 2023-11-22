package dev.nandi.phonelib.Phonebook.Contact.Attributes;

import java.io.Serializable;

/**
 * Telefonszám tárolására alkalmas osztály
 */
public class Phone implements Serializable
{

    /**
     * Országot kódját tároló változó
     */
    private final int countryCode;

    /**
     * Telefonszámot tároló változó
     */
    private final int number;

    /**
     * Konstruktor
     * @param countryCode Ország kódja
     * @param number Telefonszám
     */
    public Phone(int countryCode, int number)
    {
        this.countryCode = countryCode;
        this.number = number;
    }

    /**
     * Getterek
     */
    public int getCountryCode() { return countryCode; }
    public int getNumber() { return number; }

    /**
     * Telefonszám formázása
     * @return Formázott telefonszám
     */
    public String toString()
    {
        return "+" + countryCode + number;
    }

}
