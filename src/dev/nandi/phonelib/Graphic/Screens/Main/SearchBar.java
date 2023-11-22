package dev.nandi.phonelib.Graphic.Screens.Main;

import dev.nandi.phonelib.Phonebook.Contact.Contact;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class SearchBar extends JTextField
{

    private String placeholder;
    private final List<Contact> contacts;

    public SearchBar(List<Contact> contacts)
    {
        this.contacts = contacts;

        this.updatePlaceholder();
        this.addFocusListener(new SearchBarFocusListener());
    }

    public void updatePlaceholder()
    {
        this.placeholder = "Keresés " + contacts.size() + " névjegy között";
        this.setText(this.placeholder);
    }

    public String getPlaceholder()
    {
        return this.placeholder;
    }

    private class SearchBarFocusListener implements FocusListener
    {
        @Override
        public void focusGained(FocusEvent e)
        {
            if (SearchBar.this.getText().equals(placeholder))
                SearchBar.this.setText("");
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            if (SearchBar.this.getText().equals(placeholder) || SearchBar.this.getText().isEmpty())
                SearchBar.this.updatePlaceholder();
        }
    }

}
