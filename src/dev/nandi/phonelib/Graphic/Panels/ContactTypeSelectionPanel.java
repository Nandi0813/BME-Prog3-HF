package dev.nandi.phonelib.Graphic.Panels;

import dev.nandi.phonelib.Graphic.Panels.Editor.CompanyPanel;
import dev.nandi.phonelib.Graphic.Panels.Editor.ContactInterface;
import dev.nandi.phonelib.Graphic.Panels.Editor.PersonPanel;
import dev.nandi.phonelib.Graphic.Screens.Main.MainScreen;
import dev.nandi.phonelib.Phonebook.Contact.ContactType;
import dev.nandi.phonelib.Graphic.Screens.Main.PhonebookData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ContactTypeSelectionPanel extends JDialog
{

    /**
     * A telefonkönyv adatai
     */
    private final PhonebookData phonebookData;

    /**
     * A kontakt típusokat tartalmazó legördülő lista
     */
    private final JComboBox<String> contactTypeComboBox = new JComboBox<>();

    /**
     * Konstruktor
     * @param mainScreen A főképernyő
     */
    public ContactTypeSelectionPanel(MainScreen mainScreen)
    {
        super(mainScreen, "Válaszd ki a kontakt típusát", true);

        this.phonebookData = mainScreen.getPhonebookData();

        this.init();
    }

    /**
     * Inicializálja a dialógusablakot
     */
    private void init()
    {
        this.setPreferredSize(new Dimension(300, 150));
        this.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton okButton = new JButton("Tovább");
        buttonPanel.add(okButton);
        okButton.addActionListener(e -> okButton());

        JButton cancelButton = new JButton("Mégse");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(e -> this.dispose());

        for (ContactType contactType : ContactType.values())
            contactTypeComboBox.addItem(contactType.getName());
        mainPanel.add(contactTypeComboBox, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Visszaadja a kiválasztott kontakt típust
     * @return A kiválasztott kontakt típus
     */
    public ContactType getSelectedContactType()
    {
        return ContactType.valueOfName((String) contactTypeComboBox.getSelectedItem());
    }

    /**
     * A dialógusablak "oké"-zésekor hívott metódus.
     * Megnyitja a kiválasztott kontakt típus szerkesztőjét.
     */
    private void okButton()
    {
        ContactInterface contactInterface = null;
        switch (getSelectedContactType())
        {
            case PERSON:
                contactInterface = new PersonPanel(phonebookData, null, true);
                break;
            case COMPANY:
                contactInterface = new CompanyPanel(phonebookData, null, true);
                break;
        }

        if (contactInterface != null)
        {
            this.dispose();
            contactInterface.showDialog();
        }
    }

}