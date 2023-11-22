package dev.nandi.phonelib.Graphic.Panels.Editor;

import dev.nandi.phonelib.Phonebook.Contact.Attributes.Address;
import dev.nandi.phonelib.Phonebook.Contact.Attributes.Phone;
import dev.nandi.phonelib.Phonebook.Contact.Company;
import dev.nandi.phonelib.Graphic.Screens.Main.PhonebookData;

import javax.swing.*;
import java.util.Date;

public class CompanyPanel extends ContactInterface
{

    /**
     * A cég nevét tartalmazó mező.
     */
    private final JTextField nameField;

    /**
     * A cég adószámát tartalmazó mező.
     */
    private final JTextField taxNumberField;

    /**
     * @param phonebookData A telefonkönyv adatait tartalmazó objektum.
     * @param company A cég, amit szerkesztünk. (Ha szerkesztünk és nem hozzáadunk)
     * @param editable Igaz, ha szerkesztünk vagy létrehozunk, hamis, ha megjelenítünk.
     */
    public CompanyPanel(PhonebookData phonebookData, Company company, boolean editable)
    {
        super(phonebookData, company, editable);
        this.setTitle(company == null ? "Cég hozzáadása" : (editable ? "Cég módosítása" : "Cég részletei"));

        this.inputPanel.add(new JLabel("Cégnév:"));
        nameField = new JTextField(company != null ? company.getName() : "");
        this.inputPanel.add(nameField);

        this.inputPanel.add(new JLabel("Ország:"));
        countryField = new JTextField(company != null ? company.getAddress().getCountry() : "");
        this.inputPanel.add(countryField);

        this.inputPanel.add(new JLabel("Irányítószám:" + (editable ? " (1234)" : "")));
        zipCodeField = new JTextField(company != null ? String.valueOf(company.getAddress().getPostalCode()) : "");
        this.inputPanel.add(zipCodeField);

        this.inputPanel.add(new JLabel("Város:"));
        cityField = new JTextField(company != null ? company.getAddress().getCity() : "");
        this.inputPanel.add(cityField);

        this.inputPanel.add(new JLabel("Utca:"));
        streetField = new JTextField(company != null ? company.getAddress().getStreet() : "");
        this.inputPanel.add(streetField);

        this.inputPanel.add(new JLabel("Házszám:" + (editable ? " (123)" : "")));
        houseNumberField = new JTextField(company != null ? String.valueOf(company.getAddress().getHouseNumber()) : "");
        this.inputPanel.add(houseNumberField);

        this.inputPanel.add(new JLabel("Telefonszám:" + (editable ? " (+36 123456789)" : "")));
        phoneNumberField = new JTextField(company != null ? "+" + company.getPhone().getCountryCode() + " " + company.getPhone().getNumber() : "+36 ");
        this.inputPanel.add(phoneNumberField);

        this.inputPanel.add(new JLabel("Adószám:" + (editable ? " (124643)" : "")));
        taxNumberField = new JTextField(company != null ? String.valueOf(company.getTaxNumber()) : "");
        this.inputPanel.add(taxNumberField);
    }

    /**
     * Ellenőrzi, hogy a mezők helyesen vannak-e kitöltve.
     * @return A módosított cég objektum.
     */
    @Override
    public Company validateContact()
    {
        String name = nameField.getText();
        if (name.isEmpty())
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

        int taxNumber;
        try {
            taxNumber = Integer.parseInt(taxNumberField.getText());
            if (taxNumber <= 0)
                return null;
        } catch (NumberFormatException e) {
            return null;
        }

        return new Company(name,
                new Address(country, zipCode, city, street, houseNumber),
                new Phone(phoneNumberPart1, phoneNumberPart2),
                taxNumber,
                new Date(System.currentTimeMillis()));
    }

}
