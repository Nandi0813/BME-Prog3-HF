package dev.nandi.phonelib.Phonebook.Contact;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;

import java.util.Date;

public class Person extends Contact
{

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, Address address, Phone phone, Date addedAt)
    {
        super(ContactType.PERSON, address, phone, addedAt);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    @Override
    public String getName() { return firstName + " " + lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

}
