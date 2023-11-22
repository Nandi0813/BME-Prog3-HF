package dev.nandi.phonelib.Phonebook.Contact;

/**
 * A kapcsolat típusát reprezentáló enum.
 */
public enum ContactType
{

    PERSON("Személy"),
    COMPANY("Cég");

    private final String name;

    /**
     * Konstruktor
     * @param name A kapcsolat típusának neve
     */
    ContactType(final String name)
    {
        this.name = name;
    }

    /**
     * Getter
     */
    public String getName()
    {
        return name;
    }

    /**
     * Visszaadja a kapcsolat típusát a neve alapján
     * @param name A kapcsolat típusának neve
     * @return A kapcsolat típusa
     */
    public static ContactType valueOfName(String name)
    {
        for (ContactType contactType : ContactType.values())
            if (contactType.getName().equalsIgnoreCase(name))
                return contactType;
        return null;
    }

}
