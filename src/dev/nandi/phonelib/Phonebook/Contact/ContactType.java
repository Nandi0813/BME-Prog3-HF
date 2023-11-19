package dev.nandi.phonelib.Phonebook.Contact;

public enum ContactType
{

    PERSON("Személy"),
    COMPANY("Cég");

    private final String name;

    ContactType(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}
