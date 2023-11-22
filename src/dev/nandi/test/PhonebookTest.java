package dev.nandi.test;

import dev.nandi.phonelib.Phonebook.Contact.Contact;
import dev.nandi.phonelib.Phonebook.Contact.Person;
import dev.nandi.phonelib.Phonebook.Phonebook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhonebookTest 
{
    
    private Phonebook phonebook;
    private Contact contact;

    @Before
    public void setUp() {
        phonebook = new Phonebook("TestPhonebook");
        contact = new Person("John", "Doe", null, null, null);
    }

    @Test
    public void testGetName()
    {
        Assert.assertEquals("TestPhonebook", phonebook.getName());
    }

    @Test
    public void testGetContacts()
    {
        Assert.assertEquals(0, phonebook.getContacts().size());
    }

    @Test
    public void testAddContact()
    {
        phonebook.addContact(contact);
        Assert.assertEquals(1, phonebook.getContacts().size());
        Assert.assertEquals(contact, phonebook.getContacts().get(0));
    }

    @Test
    public void testRemoveContact()
    {
        phonebook.addContact(contact);
        phonebook.removeContact(contact);
        Assert.assertEquals(0, phonebook.getContacts().size());
    }
    
}
