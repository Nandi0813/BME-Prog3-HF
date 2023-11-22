package dev.nandi.phonelib.Phonebook;

import dev.nandi.phonelib.Main;
import dev.nandi.phonelib.Phonebook.Contact.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Phonebook implements Serializable
{

    private static final long serialVersionUID = 1L;

    private final String name;

    private final List<Contact> contacts = new ArrayList<>();

    public Phonebook(String name)
    {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Contact> getContacts() { return contacts; }

    public void addContact(Contact contact)
    {
        this.contacts.add(contact);
        Main.getMainScreen().getSearchBar().updatePlaceholder();
    }

    public void removeContact(Contact contact)
    {
        this.contacts.remove(contact);
        Main.getMainScreen().getSearchBar().updatePlaceholder();
    }

}
