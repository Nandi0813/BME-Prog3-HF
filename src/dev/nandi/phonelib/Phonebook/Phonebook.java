package dev.nandi.phonelib.Phonebook;

import dev.nandi.phonelib.Main;
import dev.nandi.phonelib.Phonebook.Contact.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Telefonkönyv osztály.
 */
public class Phonebook implements Serializable
{

    /**
     * Serializációhoz szükséges egyedi azonosító.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telefonkönyv neve.
     */
    private final String name;

    /**
     * Telefonkönyvben tárolt kontaktok.
     */
    private final List<Contact> contacts = new ArrayList<>();

    /**
     * Telefonkönyv osztály konstruktora.
     * @param name Telefonkönyv neve.
     */
    public Phonebook(String name)
    {
        this.name = name;
    }

    /**
     * Getterek
     */
    public String getName() { return name; }
    public List<Contact> getContacts() { return contacts; }

    /**
     * Kontakt hozzáadása a telefonkönyvhöz.
     * @param contact Hozzáadandó kontakt.
     */
    public void addContact(Contact contact)
    {
        this.contacts.add(contact);
        Main.getMainScreen().getSearchBar().updatePlaceholder();
    }

    /**
     * Kontakt eltávolítása a telefonkönyvből.
     * @param contact Eltávolítandó kontakt.
     */
    public void removeContact(Contact contact)
    {
        this.contacts.remove(contact);
        Main.getMainScreen().getSearchBar().updatePlaceholder();
    }

}
