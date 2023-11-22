package dev.nandi.phonelib.Graphic.Screens.Main;

import dev.nandi.phonelib.Phonebook.Contact.Contact;
import dev.nandi.phonelib.Phonebook.Phonebook;
import dev.nandi.phonelib.Util;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Date;
import java.util.List;

public class PhonebookData extends AbstractTableModel
{

    private final Phonebook phonebook;
    private final List<Contact> contacts;

    public PhonebookData(Phonebook phonebook)
    {
        this.phonebook = phonebook;
        this.contacts = this.phonebook.getContacts();
    }

    public Phonebook getPhonebook() { return this.phonebook; }

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
            default: return Util.formatDate(contact.getAddedAt(), "yyyy-MM-dd HH:mm");
        }
    }

    public RowSorter<PhonebookData> search(String searchFor)
    {
        RowFilter<PhonebookData, Integer> contactFilter = new RowFilter<PhonebookData, Integer>()
        {
            @Override
            public boolean include(Entry<? extends PhonebookData, ? extends Integer> entry)
            {
                Contact contact = contacts.get(entry.getIdentifier());
                return
                        contact.getContactType().getName().contains(searchFor) ||
                        contact.getName().contains(searchFor) ||
                        contact.getAddress().toString().contains(searchFor);
            }
        };

        TableRowSorter<PhonebookData> sorter = new TableRowSorter<>(this);
        sorter.setRowFilter(contactFilter);

        return sorter;
    }

}
