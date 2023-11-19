package dev.nandi.phonelib.Phonebook.Contact;

import dev.nandi.phonelib.Phonebook.Contact.Attr.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attr.Phone;

import java.io.Serializable;
import java.util.Date;

public abstract class Contact implements Serializable
{

    protected final ContactType contactType;
    protected Address address;
    protected Phone phone;
    protected Date addedAt;

    protected Contact(ContactType contactType, Address address, Phone phone, Date addedAt)
    {
        this.contactType = contactType;
        this.address = address;
        this.phone = phone;
        this.addedAt = addedAt;
    }

    public abstract String getName();

    public ContactType getContactType() { return contactType; }
    public Address getAddress() { return address; }
    public Phone getPhone() { return phone; }
    public Date getAddedAt() { return addedAt; }

    public void setAddress(Address address) { this.address = address; }
    public void setPhone(Phone phone) { this.phone = phone; }
    public void setAddedAt(Date addedAt) { this.addedAt = addedAt; }

}
