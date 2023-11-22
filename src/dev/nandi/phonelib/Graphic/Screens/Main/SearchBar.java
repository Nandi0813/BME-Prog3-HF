package dev.nandi.phonelib.Graphic.Screens.Main;

import dev.nandi.phonelib.Phonebook.Contact.Contact;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

/**
 * A keresőmezőt reprezentáló osztály.
 */
public class SearchBar extends JTextField
{

    /**
     * A keresőmező placeholder szövege.
     */
    private String placeholder;

    /**
     * A keresőmezőhöz tartozó névjegyek listája.
     */
    private final List<Contact> contacts;

    /**
     * A keresőmezőt létrehozó konstruktor.
     * @param contacts A keresőmezőhöz tartozó névjegyek listája.
     */
    public SearchBar(List<Contact> contacts)
    {
        this.contacts = contacts;

        this.updatePlaceholder();
        this.addFocusListener(new SearchBarFocusListener());
    }

    /**
     * A keresőmező placeholder szövegének frissítése.
     */
    public void updatePlaceholder()
    {
        this.placeholder = "Keresés " + contacts.size() + " névjegy között";
        this.setText(this.placeholder);
    }

    /**
     * @return A keresőmező placeholder szövege.
     */
    public String getPlaceholder()
    {
        return this.placeholder;
    }

    /**
     * A keresőmező fókuszváltását kezelő osztály.
     */
    private class SearchBarFocusListener implements FocusListener
    {
        /**
         * A keresőmező fókuszba kerülésekor a placeholder szöveg törlése.
         */
        @Override
        public void focusGained(FocusEvent e)
        {
            if (SearchBar.this.getText().equals(placeholder))
                SearchBar.this.setText("");
        }

        /**
         * A keresőmező fókuszból való kikerülésekor a placeholder szöveg visszaállítása.
         */
        @Override
        public void focusLost(FocusEvent e)
        {
            if (SearchBar.this.getText().equals(placeholder) || SearchBar.this.getText().isEmpty())
                SearchBar.this.updatePlaceholder();
        }
    }

}
