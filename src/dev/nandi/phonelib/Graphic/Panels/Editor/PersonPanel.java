package dev.nandi.phonelib.Graphic.Panels.Editor;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;
import dev.nandi.phonelib.Phonebook.Contact.Person;
import dev.nandi.phonelib.Graphic.Screens.Main.PhonebookData;

import javax.swing.*;
import java.util.Date;

public class PersonPanel extends ContactInterface
{

    private final JTextField lastNameField;
    private final JTextField firstNameField;
    private final JTextField countryField;
    private final JTextField zipCodeField;
    private final JTextField cityField;
    private final JTextField streetField;
    private final JTextField houseNumberField;
    private final JTextField phoneNumberField;

    public PersonPanel(PhonebookData phonebookData, Person person, boolean editable)
    {
        super(phonebookData, person, editable);
        this.setTitle(person == null ? "Személy hozzáadása" : (editable ? "Személy módosítása" : "Személy részletei"));

        this.inputPanel.add(new JLabel("Vezetéknév:"));
        lastNameField = new JTextField((person != null) ? person.getLastName() : "");
        this.inputPanel.add(lastNameField);

        this.inputPanel.add(new JLabel("Keresztnév:"));
        firstNameField = new JTextField((person != null) ? person.getFirstName() : "");
        this.inputPanel.add(firstNameField);

        this.inputPanel.add(new JLabel("Ország:"));
        countryField = new JTextField((person != null) ? person.getAddress().getCountry() : "");
        this.inputPanel.add(countryField);

        this.inputPanel.add(new JLabel("Irányítószám:" + (editable ? " (1234)" : "")));
        zipCodeField = new JTextField(String.valueOf((person != null) ? person.getAddress().getPostalCode() : ""));
        this.inputPanel.add(zipCodeField);

        this.inputPanel.add(new JLabel("Város:"));
        cityField = new JTextField((person != null) ? person.getAddress().getCity() : "");
        this.inputPanel.add(cityField);

        this.inputPanel.add(new JLabel("Utca:"));
        streetField = new JTextField((person != null) ? person.getAddress().getStreet() : "");
        this.inputPanel.add(streetField);

        this.inputPanel.add(new JLabel("Házszám:" + (editable ? " (123)" : "")));
        houseNumberField = new JTextField(String.valueOf((person != null) ? person.getAddress().getHouseNumber() : ""));
        this.inputPanel.add(houseNumberField);

        this.inputPanel.add(new JLabel("Telefonszám:" + (editable ? " (+36 123456789)" : "")));
        phoneNumberField = new JTextField(
                (person != null) ? "+" + person.getPhone().getCountryCode() + " " + person.getPhone().getNumber() : "+36 "
        );
        this.inputPanel.add(phoneNumberField);
    }

    @Override
    public Person validateContact()
    {
        String lastName = lastNameField.getText();
        if (lastName.isEmpty() || lastName.contains(" "))
            return null;

        String firstName = firstNameField.getText();
        if (firstName.isEmpty() || firstName.contains(" "))
            return null;

        String country = countryField.getText();
        if (country.isEmpty())
            return null;

        int zipCode;
        try {
            zipCode = Integer.parseInt(zipCodeField.getText());
            if (zipCode < 1000 || zipCode > 9999)
                return null;
        } catch (NumberFormatException e) {
            return null;
        }

        String city = cityField.getText();
        if (city.isEmpty())
            return null;

        String street = streetField.getText();
        if (street.isEmpty())
            return null;

        int houseNumber;
        try {
            houseNumber = Integer.parseInt(houseNumberField.getText());
            if (houseNumber <= 0)
                return null;
        } catch (NumberFormatException e) {
            return null;
        }

        String phoneNumber = phoneNumberField.getText();
        int phoneNumberPart1;
        int phoneNumberPart2;

        String[] phoneNumberParts = phoneNumber.split(" ");
        if (phoneNumberParts.length != 2)
            return null;

        try {
            phoneNumberPart1 = Integer.parseInt(phoneNumberParts[0].substring(1));
            phoneNumberPart2 = Integer.parseInt(phoneNumberParts[1]);
            if (phoneNumberPart1 < 10 || phoneNumberPart1 > 99 || phoneNumberPart2 < 100000000 || phoneNumberPart2 > 999999999)
                return null;
        } catch (NumberFormatException e) {
            return null;
        }

        return new Person(lastName, firstName,
                new Address(country, zipCode, city, street, houseNumber),
                new Phone(phoneNumberPart1, phoneNumberPart2),
                new Date(System.currentTimeMillis()));
    }

}