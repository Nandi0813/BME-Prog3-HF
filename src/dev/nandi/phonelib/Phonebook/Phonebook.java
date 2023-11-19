package dev.nandi.phonelib.Phonebook;

import dev.nandi.phonelib.Phonebook.Contact.Contact;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Phonebook extends AbstractTableModel implements Serializable
{

    private static final long serialVersionUID = 2932842194912L;

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
        this.fireTableDataChanged();
    }

    public void removeContact(Contact contact)
    {
        this.contacts.remove(contact);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() { return this.contacts.size(); }

    @Override
    public int getColumnCount() { return 5; }

    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "Típus";
            case 1:
                return "Név";
            case 2:
                return "Cím";
            case 3:
                return "Telefonszám";
            default:
                return "Létrehozva";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
            case 3:
                return Integer.class;
            case 4:
                return Date.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Contact contact = this.contacts.get(rowIndex);

        switch (columnIndex)
        {
            case 0: return contact.getContactType().getName();
            case 1: return contact.getName();
            case 2: return contact.getAddress().toString();
            case 3: return contact.getPhone().toString();
            default: return contact.getAddedAt();
        }
    }

}
