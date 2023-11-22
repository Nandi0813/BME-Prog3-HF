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

    /**
     * A telefonkönyv, amelynek adatait megjeleníti
     */
    private final Phonebook phonebook;

    /**
     * A telefonkönyv adatait tartalmazó objektum
     */
    private final PhonebookData phonebookData;

    /**
     * A táblázat, amely a telefonkönyv adatait jeleníti meg
     */
    private final JTable contactTable = new JTable();

    /**
     * A keresőmező
     */
    private final SearchBar searchBar;

    /**
     * A főképernyő konstruktora
     * @param phonebook A telefonkönyv, amelynek adatait megjeleníti
     */
    public MainScreen(Phonebook phonebook)
    {
        super(phonebook.getName() + " - Telefonkönyv");
        this.phonebook = phonebook;
        this.phonebookData = new PhonebookData(phonebook);

        // Alap dolgok beállítása a főképernyőhöz
        Dimension screenSize = new Dimension(1280, 720);
        this.setMinimumSize(screenSize);
        this.setPreferredSize(screenSize);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Táblázat alapbeállításai
        contactTable.setModel(phonebookData);
        contactTable.setAutoCreateRowSorter(true);
        contactTable.setIntercellSpacing(new Dimension(10, 2));
        contactTable.setRowHeight(20);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Rendererek beállítása az oszlopok címeire
        JTableHeader header = contactTable.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 15));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        /*
         * Navigációs panel
         */
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBorder(BorderFactory.createTitledBorder("Navigáció"));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        addButtonsToNavigationPanel(navigationPanel);

        /*
         * Névjegy kezelő panel
         */
        JPanel contactPanel = new JPanel();
        contactPanel.setBorder(BorderFactory.createTitledBorder("Kontaktok"));
        contactPanel.setLayout(new BorderLayout());

        // Button panel a táblázat fölé
        JPanel contactNorthPanel = new JPanel();
        contactNorthPanel.setLayout(new BoxLayout(contactNorthPanel, BoxLayout.X_AXIS));
        addButtonsToContactPanel(contactNorthPanel);

        // Keresőmező
        this.searchBar = new SearchBar(phonebook.getContacts());
        searchBar.getDocument().addDocumentListener(new SearchBarListener());
        contactNorthPanel.add(searchBar);

        contactPanel.add(contactNorthPanel, BorderLayout.NORTH);
        contactPanel.add(new JScrollPane(contactTable), BorderLayout.CENTER);

        this.add(navigationPanel, BorderLayout.NORTH);
        this.add(contactPanel);

        // Ablak bezárásakor mentés
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Main.getPhonebookManager().savePhonebook(phonebook);
            }
        });
    }

    /**
     * Gombok hozzáadása a navigációs panelhez
     * @param jPanel A navigációs panel
     */
    private void addButtonsToNavigationPanel(JPanel jPanel)
    {
        // Mentés
        JButton savePhonebook = new JButton("Telefonkönyv mentése");
        savePhonebook.addActionListener(e -> savePhonebook(phonebook));
        jPanel.add(savePhonebook);

        // Mentés és bezárás
        JButton backToSelector = new JButton("Mentés és vissza");
        backToSelector.addActionListener(e -> backToSelector(phonebook));
        jPanel.add(backToSelector);

        // Telefonkönyv törlése
        JButton deletePhonebook = new JButton("Telefonkönyv törlése");
        deletePhonebook.addActionListener(e -> deletePhonebook(phonebook));
        jPanel.add(deletePhonebook);
    }

    /**
     * Gombok hozzáadása a névjegy kezelő panelhez
     * @param jPanel A névjegy kezelő panel
     */
    private void addButtonsToContactPanel(JPanel jPanel)
    {
        // Kontakt hozzáadása
        JButton addContactButton = new JButton("Kontakt hozzáadása");
        addContactButton.addActionListener(e -> new ContactTypeSelectionPanel(this));
        jPanel.add(addContactButton);

        // Kontant részletek
        JButton showContactButton = new JButton("Kontakt részletei");
        showContactButton.addActionListener(e -> showContact());
        jPanel.add(showContactButton);

        // Kontant szerkesztése
        JButton editContactButton = new JButton("Kontakt szerkesztése");
        editContactButton.addActionListener(e -> editContact());
        jPanel.add(editContactButton);

        // Kontakt törlése
        JButton deleteContactButton = new JButton("Kontakt törlése");
        deleteContactButton.addActionListener(e -> deleteContact());
        jPanel.add(deleteContactButton);
    }

    /**
     * Telefonkönyv mentése gomb kezelése
     * @param phonebook A telefonkönyv, amelyet menteni kell
     */
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

    /**
     * Vissza a telefonkönyv kiválasztó képernyőre gomb kezelése
     * @param phonebook A telefonkönyv, amelyet menteni kell
     */
    private static void backToSelector(Phonebook phonebook)
    {
        Main.getPhonebookManager().savePhonebook(phonebook);
        Main.getMainScreen().dispose();
        Main.getLoadScreen().loadDefaultScreen();
        Main.getLoadScreen().setVisible(true);
    }

    /**
     * Telefonkönyv törlése gomb kezelése
     * @param phonebook A telefonkönyv, amelyet törölni kell
     */
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

    /**
     * Kontakt megtekintése gomb kezelése
     */
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

    /**
     * Kontakt szerkesztése gomb kezelése
     */
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

    /**
     * Kontakt törlése gomb kezelése
     */
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

    /**
     * Keresőmező figyelője
     */
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

    /**
     * A kiválasztott kontakt lekérése a táblázatból
     * @return A kiválasztott kontakt
     */
    private Contact getSelectedContact()
    {
        int selectedRow = contactTable.convertRowIndexToModel(contactTable.getSelectedRow());
        if (selectedRow != -1)
            return phonebookData.getPhonebook().getContacts().get(selectedRow);
        return null;
    }

    /**
     * Getterek
     */
    public PhonebookData getPhonebookData() { return phonebookData; }
    public SearchBar getSearchBar() { return searchBar; }

}
