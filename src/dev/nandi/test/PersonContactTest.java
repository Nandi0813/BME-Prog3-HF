package dev.nandi.test;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;
import dev.nandi.phonelib.Phonebook.Contact.ContactType;
import dev.nandi.phonelib.Phonebook.Contact.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class PersonContactTest 
{

    private Person person;

    @Before
    public void init()
    {
        Address address = new Address("Hungary", 1234, "Budapest", "Main Street", 1);
        Phone phone = new Phone(36, 123456789);

        person = new Person("John", "Doe", address, phone, new Date(System.currentTimeMillis()));
    }

    @Test
    public void testGetFirstName()
    {
        Assert.assertEquals("John", person.getFirstName());
    }

    @Test
    public void testGetLastName()
    {
        Assert.assertEquals("Doe", person.getLastName());
    }

    @Test
    public void testGetName()
    {
        Assert.assertEquals("John Doe", person.getName());
    }

    @Test
    public void testSetFirstName()
    {
        person.setFirstName("Jane");
        Assert.assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void testSetLastName()
    {
        person.setLastName("Smith");
        Assert.assertEquals("Smith", person.getLastName());
    }

    @Test
    public void testGetContactType() {
        Assert.assertEquals(ContactType.PERSON, person.getContactType());
    }

    @Test
    public void testGetAddress() {
        Assert.assertEquals("Hungary", person.getAddress().getCountry());
    }

    @Test
    public void testGetAddedAt()
    {
        Assert.assertNotNull(person.getAddedAt());
    }

    @Test
    public void testSetAddress()
    {
        Address newAddress = new Address("USA", 90210, "Beverly Hills", "Sunset Boulevard", 123);
        person.setAddress(newAddress);
        Assert.assertEquals("USA", person.getAddress().getCountry());
    }
    
}
