package dev.nandi.phonelib.Graphic.Screens.Main;

import dev.nandi.phonelib.Graphic.Panels.Editor.CompanyPanel;
import dev.nandi.phonelib.Graphic.Panels.Editor.ContactInterface;
import dev.nandi.phonelib.Graphic.Panels.Editor.PersonPanel;
import dev.nandi.phonelib.Graphic.Panels.ContactTypeSelectionPanel;
import dev.nandi.phonelib.Main;
import dev.nandi.phonelib.Phonebook.Contact.Company;
import dev.nandi.phonelib.Phonebook.Contact.Contact;
import dev.nandi.phonelib.Phonebook.Contact.Person;
import dev.nandi.phonelib.Phonebook.Phonebook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainScreen extends JFrame
{

    private final PhonebookData phonebookData;
    private final JTable contactTable = new JTable();
    private final SearchBar searchBar;

    public MainScreen(Phonebook phonebook)
    {
        super(phonebook.getName() + " - Telefonkönyv");
        this.phonebookData = new PhonebookData(phonebook);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenSize = new Dimension(1280, 720);
        this.setMinimumSize(screenSize);
        this.setPreferredSize(screenSize);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        contactTable.setModel(phonebookData);

        contactTable.setAutoCreateRowSorter(true);
        contactTable.setIntercellSpacing(new Dimension(10, 2));
        contactTable.setRowHeight(20);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Rendererek beállítása
        JTableHeader header = contactTable.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 15));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(contactTable);

        /*
         * Navigációs panel
         */
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBorder(BorderFactory.createTitledBorder("Navigáció"));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));

        // Mentés
        JButton savePhonebook = new JButton("Telefonkönyv mentése");
        savePhonebook.addActionListener(e -> savePhonebook(phonebook));
        navigationPanel.add(savePhonebook);

        // Mentés és bezárás
        JButton backToSelector = new JButton("Mentés és vissza");
        backToSelector.addActionListener(e -> backToSelector(phonebook));
        navigationPanel.add(backToSelector);

        // Telefonkönyv törlése
        JButton deletePhonebook = new JButton("Telefonkönyv törlése");
        deletePhonebook.addActionListener(e -> deletePhonebook(phonebook));
        navigationPanel.add(deletePhonebook);

        /*
         * Névjegy panel
         */
        JPanel contactPanel = new JPanel();
        contactPanel.setBorder(BorderFactory.createTitledBorder("Kontaktok"));
        contactPanel.setLayout(new BorderLayout());

        // komponenspanel a táblázat fölé
        JPanel contactNorthPanel = new JPanel();
        contactNorthPanel.setLayout(new BoxLayout(contactNorthPanel, BoxLayout.X_AXIS));

        // Kontakt hozzáadása
        JButton addContactButton = new JButton("Kontakt hozzáadása");
        addContactButton.addActionListener(e -> new ContactTypeSelectionPanel(this));
        contactNorthPanel.add(addContactButton);

        // Kontant részletek
        JButton showContactButton = new JButton("Kontakt részletei");
        showContactButton.addActionListener(e -> showContact());
        contactNorthPanel.add(showContactButton);

        // Kontant szerkesztése
        JButton editContactButton = new JButton("Kontakt szerkesztése");
        editContactButton.addActionListener(e -> editContact());
        contactNorthPanel.add(editContactButton);

        // Kontakt törlése
        JButton deleteContactButton = new JButton("Kontakt törlése");
        deleteContactButton.addActionListener(e -> deleteContact());
        contactNorthPanel.add(deleteContactButton);

        this.searchBar = new SearchBar(phonebook.getContacts());
        searchBar.getDocument().addDocumentListener(new SearchBarListener());
        contactNorthPanel.add(searchBar);

        contactPanel.add(contactNorthPanel, BorderLayout.NORTH);
        contactPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(navigationPanel, BorderLayout.NORTH);
        this.add(contactPanel);

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Main.getPhonebookManager().savePhonebook(phonebook);
            }
        });
    }

    private static void savePhonebook(Phonebook phonebook)
    {
        Main.getPhonebookManager().savePhonebook(phonebook);

        JOptionPane.showMessageDialog(
                null,
                "Telefonkönyv sikeresen mentve!",
                "Telefonkönyv sikeresen mentve!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void backToSelector(Phonebook phonebook)
    {
        Main.getPhonebookManager().savePhonebook(phonebook);
        Main.getMainScreen().dispose();
        Main.getLoadScreen().loadDefaultScreen();
        Main.getLoadScreen().setVisible(true);
    }

    private static void deletePhonebook(Phonebook phonebook)
    {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Biztosan törölni szeretnéd a telefonkönyvet?\n" + phonebook.getName(),
                "Törlés megerősítése",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION)
        {
            Main.getMainScreen().dispose();
            Main.getPhonebookManager().deletePhonebook(phonebook);
            Main.getLoadScreen().loadDefaultScreen();
            Main.getLoadScreen().setVisible(true);
        }
    }

    private void showContact()
    {
        Contact contact = this.getSelectedContact();
        if (contact != null)
        {
            ContactInterface contactInterface = null;
            switch (contact.getContactType())
            {
                case PERSON:
                    contactInterface = new PersonPanel(phonebookData, (Person) contact, false);
                    break;
                case COMPANY:
                    contactInterface = new CompanyPanel(phonebookData, (Company) contact, false);
                    break;
            }

            if (contactInterface != null)
                contactInterface.showDialog();
        }
    }

    private void editContact()
    {
        Contact contact = this.getSelectedContact();
        if (contact != null)
        {
            ContactInterface contactInterface = null;
            switch (contact.getContactType())
            {
                case PERSON:
                    contactInterface = new PersonPanel(phonebookData, (Person) contact, true);
                    break;
                case COMPANY:
                    contactInterface = new CompanyPanel(phonebookData, (Company) contact, true);
                    break;
            }

            if (contactInterface != null)
                contactInterface.showDialog();
        }
    }

    private void deleteContact()
    {
        Contact contact = this.getSelectedContact();
        if (contact != null)
        {
            int result = JOptionPane.showConfirmDialog(
    this,
                    "Biztosan törölni szeretnéd a névjegyet?\n" + contact.getName(),
                    "Törlés megerősítése",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION)
            {
                phonebookData.getPhonebook().removeContact(contact);
                phonebookData.fireTableDataChanged();
            }
        }
    }

    public class SearchBarListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e)
        {
            String searchBarText = searchBar.getText();
            if (!searchBarText.equals(searchBar.getPlaceholder()))
            {
                contactTable.setRowSorter(phonebookData.search(searchBarText));
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            contactTable.setRowSorter(phonebookData.search(searchBar.getText()));
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            contactTable.setRowSorter(phonebookData.search(searchBar.getText()));
        }
    }

    private Contact getSelectedContact()
    {
        int selectedRow = contactTable.convertRowIndexToModel(contactTable.getSelectedRow());
        if (selectedRow != -1)
            return phonebookData.getPhonebook().getContacts().get(selectedRow);
        return null;
    }

    public PhonebookData getPhonebookData() { return phonebookData; }
    public SearchBar getSearchBar() { return searchBar; }

}
