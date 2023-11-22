package dev.nandi.phonelib.Graphic.Panels.Editor;

import dev.nandi.phonelib.Phonebook.Contact.Company;
import dev.nandi.phonelib.Phonebook.Contact.Contact;
import dev.nandi.phonelib.Phonebook.Contact.Person;
import dev.nandi.phonelib.Graphic.Screens.Main.PhonebookData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Ez az osztály felel az adat hozzáadás, megjelenítés és szerkesztésért.
 * Leszármazik belőle a személy és a cég egyedi adatok megjelenítésért felésős osztályok.
 */
public abstract class ContactInterface extends JDialog
{

    /**
     * Telefonkönyv adatait tartalmazó objektum.
     */
    protected final PhonebookData phonebookData;

    /**
     * Közös objektumok.
     */
    protected JTextField countryField;
    protected JTextField zipCodeField;
    protected JTextField cityField;
    protected JTextField streetField;
    protected JTextField houseNumberField;
    protected JTextField phoneNumberField;

    /**
     * JPanel ami tartalmazza a beviteli mezőket és a hozzájuk tartozó label-eket.
     */
    protected final JPanel inputPanel = new JPanel(new GridLayout(8, 2));


    /**
     * Amennyiben egy névjegyet szerkeszt a felhasználó, ebben az objektumban van eltárolva, hogy melyik van éppen szerkesztés alatt.
     */
    protected Contact editing;

    /**
     * Ha hamis akkor megjelenítji csak az adatokat a panel.
     */
    protected final boolean editable;

    /**
     * @param phonebookData Telefonkönyv adatait tartalmazza a megjelenítéshez.
     * @param editing A szerkesztés alatt lévő kontakt objektum.
     * @param editable Szerkeszthető-e a form.
     */
    protected ContactInterface(PhonebookData phonebookData, Contact editing, boolean editable)
    {
        this.phonebookData = phonebookData;
        this.editing = editing;
        this.editable = editable;

        this.init();
    }

    /**
     * Inicializálja a grafikus felületet.
     */
    public void init()
    {
        JPanel mainPanel = new JPanel(new BorderLayout()); // Tartalmazza a rész-paneleket.
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel buttonPanel = new JPanel(new FlowLayout()); // Gombokat tartalmazza.

        // Jóváhagyás/okézés gomb.
        JButton okButton = new JButton(editing == null ? "Hozzáadás" : (editable ? "Módosítás" : "Bezárás"));
        okButton.addActionListener(e -> okButton());
        buttonPanel.add(okButton);

        // Hozzá adjuk az "al"-paneleket a fő panelhez, majd azt a dialógushoz.
        mainPanel.add(this.inputPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel);

        // Bezárás esetén szüntesse törölje a dialógust.
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    /**
     * Megjeleníti a dialógust.
     * Ha nem szerkeszthető a form, akkor a kikapcsolja a szerkeszthetőségét.
     */
    public void showDialog()
    {
        if (!editable)
        {
            for (JTextField textField : findAllTextFields(this))
                textField.setEditable(false);
        }

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Ellenőrzi, hogy a dialógusban megadott adatok megfelelőek-e.
     * @return A dialógusban megadott adatok alapján létrehozott kontakt objektum.
     */
    public abstract Contact validateContact();

    /**
     * Hozzáad egy kontaktot a telefonkönyvhöz.
     * @param phonebookData Telefonkönyv adatait tartalmazó objektum.
     * @param contact A hozzáadandó kontakt objektum.
     */
    public void addContact(PhonebookData phonebookData, Contact contact)
    {
        phonebookData.getPhonebook().addContact(contact);
        phonebookData.fireTableDataChanged();
    }

    /**
     * Módosít egy kontaktot a telefonkönyvben.
     * @param phonebookData Telefonkönyv adatait tartalmazó objektum.
     * @param oldC A módosítandó kontakt.
     * @param newC Az új kontakt, aminek az adatait átírjuk a régi objektumba.
     */
    public void editContact(PhonebookData phonebookData, Contact oldC, Contact newC)
    {
        oldC.setAddress(newC.getAddress());
        oldC.setPhone(newC.getPhone());

        if (oldC instanceof Person)
        {
            ((Person) oldC).setFirstName(((Person) newC).getFirstName());
            ((Person) oldC).setLastName(((Person) newC).getLastName());
        }
        else if (oldC instanceof Company)
        {
            ((Company) oldC).setName(newC.getName());
            ((Company) oldC).setTaxNumber(((Company) newC).getTaxNumber());
        }

        phonebookData.fireTableDataChanged();
    }

    /**
     * Lekezeli az oké/jóváhagyás gombot.
     */
    private void okButton()
    {
        Contact contact = validateContact();
        if (editing == null)
        {
            if (contact != null)
            {
                addContact(phonebookData, contact);
                this.dispose();

                JOptionPane.showMessageDialog(this, "Új névjegy sikeresen hozzáadva!", "Sikeres", JOptionPane.PLAIN_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this, "Hibás adatok!", "Hiba", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if (editable)
            {
                if (contact != null)
                {
                    editContact(phonebookData, editing, contact);
                    this.dispose();

                    JOptionPane.showMessageDialog(this, "Névjegy sikeresen módosítva!", "Sikeres", JOptionPane.PLAIN_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(this, "Hibás adatok!", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
            else
                this.dispose();
        }
    }

    /**
     * Megkeresi az összes JTextField-et a dialógusban.
     * @param container A dialógus.
     * @return Az összes JTextField.
     */
    private static java.util.List<JTextField> findAllTextFields(Container container)
    {
        java.util.List<JTextField> textFields = new ArrayList<>();
        findTextFields(container, textFields);
        return textFields;
    }

    /**
     * Rekurzívan megkeresi az összes JTextField-et a dialógusban.
     * @param container A dialógus.
     * @param textFields Az összes JTextField.
     */
    private static void findTextFields(Container container, java.util.List<JTextField> textFields)
    {
        Component[] components = container.getComponents();
        for (Component component : components)
        {
            if (component instanceof JTextField)
            {
                textFields.add((JTextField) component);
            }
            else if (component instanceof Container)
            {
                findTextFields((Container) component, textFields);
            }
        }
    }

}
