package dev.nandi.phonelib.Phonebook.Contact;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;

import java.util.Date;

public class Company extends Contact
{

    private String name;
    private int taxNumber;

    public Company(String name, Address address, Phone phone, int taxNumber, Date addedAt)
    {
        super(ContactType.COMPANY, address, phone, addedAt);

        this.name = name;
        this.taxNumber = taxNumber;
    }

    @Override
    public String getName() { return name; }
    public int getTaxNumber() { return taxNumber; }

    public void setName(String name) { this.name = name; }
    public void setTaxNumber(int taxNumber) { this.taxNumber = taxNumber; }

}
