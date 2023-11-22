package dev.nandi.phonelib.Phonebook.Contact;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;

import java.io.Serializable;
import java.util.Date;

/**
 * Absztrakt osztály a kontaktok tárolására.
 */
public abstract class Contact implements Serializable
{

    /**
     * A kontakt típusa.
     */
    protected final ContactType contactType;

    /**
     * A kontakt címe.
     */
    protected Address address;

    /**
     * A kontakt telefonszáma.
     */
    protected Phone phone;

    /**
     * A kontakt hozzáadásának dátuma.
     */
    protected final Date addedAt;

    /**
     * Konstruktor.
     * @param contactType A kontakt típusa.
     * @param address A kontakt címe.
     * @param phone A kontakt telefonszáma.
     * @param addedAt A kontakt hozzáadásának dátuma.
     */
    protected Contact(ContactType contactType, Address address, Phone phone, Date addedAt)
    {
        this.contactType = contactType;
        this.address = address;
        this.phone = phone;
        this.addedAt = addedAt;
    }

    /**
     * @return A kontakt teljes neve.
     */
    public abstract String getName();

    /**
     * Getterek
     */
    public ContactType getContactType() { return contactType; }
    public Address getAddress() { return address; }
    public Phone getPhone() { return phone; }
    public Date getAddedAt() { return addedAt; }

    /**
     * Setterek
     */
    public void setAddress(Address address) { this.address = address; }
    public void setPhone(Phone phone) { this.phone = phone; }

}
