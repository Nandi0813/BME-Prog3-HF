package dev.nandi.phonelib.Phonebook.Contact.Attributes;

import java.io.Serializable;

public class Address implements Serializable
{

    private final String country;
    private final int postalCode;
    private final String city;
    private final String street;
    private final int houseNumber;

    public Address(String country, int postalCode, String city, String street, int houseNumber)
    {
        this.country = country;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCountry() { return country; }
    public int getPostalCode() { return postalCode; }
    public String getCity() { return city; }
    public String getStreet() { return street; }
    public int getHouseNumber() { return houseNumber; }

    @Override
    public String toString()
    {
        return country + ", " + postalCode + " " + city + ", " + street + " " + houseNumber;
    }

}
