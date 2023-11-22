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

    public static ContactType valueOfName(String name)
    {
        for (ContactType contactType : ContactType.values())
            if (contactType.getName().equalsIgnoreCase(name))
                return contactType;
        return null;
    }

}
