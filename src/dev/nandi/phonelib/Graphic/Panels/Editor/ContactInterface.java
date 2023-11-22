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

public abstract class ContactInterface extends JDialog
{

    protected final PhonebookData phonebookData;

    protected final JPanel inputPanel = new JPanel(new GridLayout(8, 2));
    protected Contact editing;
    protected final boolean editable;

    protected ContactInterface(PhonebookData phonebookData, Contact editing, boolean editable)
    {
        this.phonebookData = phonebookData;
        this.editing = editing;
        this.editable = editable;

        this.init();
    }

    public void init()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton okButton = new JButton(editing == null ? "Hozzáadás" : (editable ? "Módosítás" : "Bezárás"));
        okButton.addActionListener(e -> okButton());
        buttonPanel.add(okButton);

        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel);

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

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

    public abstract Contact validateContact();

    public void addContact(PhonebookData phonebookData, Contact contact)
    {
        phonebookData.getPhonebook().addContact(contact);
        phonebookData.fireTableDataChanged();
    }

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

    private static java.util.List<JTextField> findAllTextFields(Container container)
    {
        java.util.List<JTextField> textFields = new ArrayList<>();
        findTextFields(container, textFields);
        return textFields;
    }

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
