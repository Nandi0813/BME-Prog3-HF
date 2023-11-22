package dev.nandi.phonelib.Phonebook.Contact;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;

import java.util.Date;

/**
 * Cég típusú kapcsolatokat reprezentáló osztály.
 */
public class Company extends Contact
{

    /**
     * A cég neve
     */
    private String name;

    /**
     * A cég adó száma
     */
    private int taxNumber;

    /**
     * Konstruktor
     * @param name A cég neve
     * @param address A cég címe
     * @param phone A cég telefonszáma
     * @param taxNumber A cég adószáma
     * @param addedAt A cég hozzáadásának dátuma
     */
    public Company(String name, Address address, Phone phone, int taxNumber, Date addedAt)
    {
        super(ContactType.COMPANY, address, phone, addedAt);

        this.name = name;
        this.taxNumber = taxNumber;
    }

    /**
     * Getterek
     */
    @Override
    public String getName() { return name; }
    public int getTaxNumber() { return taxNumber; }

    /**
     * Setterek
     */
    public void setName(String name) { this.name = name; }
    public void setTaxNumber(int taxNumber) { this.taxNumber = taxNumber; }

}
