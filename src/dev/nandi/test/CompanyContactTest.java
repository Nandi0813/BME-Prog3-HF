package dev.nandi.test;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;
import dev.nandi.phonelib.Phonebook.Contact.Company;
import org.junit.*;

import java.util.Date;

public class CompanyContactTest
{

    private Company company;

    @Before
    public void setUp()
    {
        Address address = new Address("Hungary", 1234, "Budapest", "Main Street", 1);
        Phone phone = new Phone(36, 123456789);

        company = new Company("TestCompany", address, phone, 123456, new Date(System.currentTimeMillis()));
    }

    @Test
    public void testGetName()
    {
        Assert.assertEquals("TestCompany", company.getName());
    }

    @Test
    public void testGetTaxNumber()
    {
        Assert.assertEquals(123456, company.getTaxNumber());
    }

    @Test
    public void testSetName()
    {
        company.setName("NewTestCompany");
        Assert.assertEquals("NewTestCompany", company.getName());
    }

    @Test
    public void testSetTaxNumber()
    {
        company.setTaxNumber(654321);
        Assert.assertEquals(654321, company.getTaxNumber());
    }

}
