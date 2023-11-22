package dev.nandi.phonelib.Phonebook.Contact;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;

import java.util.Date;

public class Person extends Contact
{

    /**
     * A személy keresztneve.
     */
    private String firstName;

    /**
     * A személy vezetékneve.
     */
    private String lastName;

    /**
     * A személy konstruktora.
     * @param firstName A személy keresztneve.
     * @param lastName A személy vezetékneve.
     * @param address A személy címe.
     * @param phone A személy telefonszáma.
     * @param addedAt A személy hozzáadásának dátuma.
     */
    public Person(String firstName, String lastName, Address address, Phone phone, Date addedAt)
    {
        super(ContactType.PERSON, address, phone, addedAt);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getterek
     */
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    @Override
    public String getName() { return firstName + " " + lastName; }

    /**
     * Setterek
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

}
