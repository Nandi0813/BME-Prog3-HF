package dev.nandi.phonelib.Phonebook.Contact.Attr;

import java.io.Serializable;

public class Address implements Serializable
{

    private String country;
    private int postalCode;
    private String city;
    private String street;
    private int houseNumber;

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

    public void setCountry(String country) { this.country = country; }
    public void setPostalCode(int postalCode) { this.postalCode = postalCode; }
    public void setCity(String city) { this.city = city; }
    public void setStreet(String street) { this.street = street; }
    public void setHouseNumber(int houseNumber) { this.houseNumber = houseNumber; }

    @Override
    public String toString()
    {
        return country + ", " + postalCode + " " + city + ", " + street + " " + houseNumber;
    }

}
