package dev.nandi.phonelib.Graphic.Screens;

import dev.nandi.phonelib.Main;
import dev.nandi.phonelib.Phonebook.Phonebook;
import dev.nandi.phonelib.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

/**
 * A program betöltő képernyőjét reprezentáló osztály.
 */
public class LoadScreen extends JFrame
{

    /**
     * A válasz üzenetet tartalmazó mező
     */
    private final JLabel responseLabel;

    /**
     * Telefonkönyv nevét tartalmazó input mező
     */
    private final JTextField libNameField;
    private final String loadScreenLabelString = "Írd ide a telefonkönyv nevét";

    /**
     * Konsturktor.
     */
    public LoadScreen()
    {
        this.setTitle("Telefonkönyv betöltése");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Főpanel inicializálása GridBagLayout-tal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        // Input panel inicializálása
        JPanel inputPanel = new JPanel(new FlowLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Válasz label inicializálása
        responseLabel = new JLabel(" ");
        mainPanel.add(responseLabel, gbc);

        // Fájlnév beviteli mező inicializálása
        libNameField = new JTextField(loadScreenLabelString);
        libNameField.setPreferredSize(new Dimension(400, 40));
        libNameField.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(libNameField);

        // FocusListener hozzáadása, hogy eltűnjön a szöveg, amikor a felhasználó rákattint az input mezőre
        libNameField.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (libNameField.getText().equals(loadScreenLabelString))
                {
                    libNameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (libNameField.getText().isEmpty())
                {
                    libNameField.setText(loadScreenLabelString);
                }
            }
        });

        // Gombokat tartalmazó panel inicializálása
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // Betöltés gomb inicializálása
        JButton loadButton = new JButton("Betöltés");
        loadButton.addActionListener(e -> loadDirectory());
        buttonsPanel.add(loadButton);

        // Új könyvtár létrehozása gomb inicializálása
        JButton createButton = new JButton("Új könyvtár");
        createButton.addActionListener(e -> createNewDirectory());
        buttonsPanel.add(createButton);

        // Input panel hozzáadása a főpanelhez
        gbc.gridy = 1;
        mainPanel.add(inputPanel, gbc);

        // Gombokat tartalmazó panel hozzáadása a főpanelhez
        gbc.gridy = 2;
        mainPanel.add(buttonsPanel, gbc);

        // Frame beállítása
        add(mainPanel);
        pack();
    }

    private void loadDirectory()
    {
        String phonebookName = libNameField.getText();

        if (phonebookName.isEmpty() || phonebookName.equalsIgnoreCase(loadScreenLabelString))
        {
            showMessage("Adj meg egy telefonkönyv nevet!", Color.RED);
        }
        else
        {
            if (Main.getPhonebookManager().getPhonebooks().containsKey(phonebookName))
            {
                showMessage("A telefonkönyv betöltése folyamatban..", Color.GREEN);

                Util.scheduleTask(() ->
                {
                    setVisible(false);

                    Main.setMainScreen(Util.createMainScreen(Main.getPhonebookManager().getPhonebooks().get(phonebookName)));
                }, 2);
            }
            else
            {
                showMessage("A telefonkönyv nem létezik, használd az új könyvtár gombot.", Color.RED);
            }
        }
    }

    private void createNewDirectory()
    {
        String libName = libNameField.getText();

        if (libName.isEmpty() || libName.equalsIgnoreCase(loadScreenLabelString))
        {
            showMessage("Adj meg egy telefonkönyv nevet!", Color.RED);
        }
        else if (libName.contains(" "))
        {
            showMessage("A telefonkönyv neve nem tartalmazhat szóközt!", Color.RED);
        }
        else if (libName.length() > 10)
        {
            showMessage("A telefonkönyv neve maximum 10 karakter lehet!", Color.RED);
        }
        else
        {
            File file = new File(Main.directory, libName + ".txt");

            if (file.exists())
            {
                showMessage("A telefonkönyv már létezik, használd a betöltés gombot.", Color.RED);
            }
            else
            {
                showMessage("A telefonkönyv létrehozása folyamatban " + libName + " néven..", Color.GREEN);

                Util.scheduleTask(() ->
                {
                    setVisible(false);

                    Phonebook phonebook = new Phonebook(libName);
                    Main.getPhonebookManager().getPhonebooks().put(libName, phonebook);
                    Main.setMainScreen(Util.createMainScreen(phonebook));
                }, 2);
            }
        }
    }

    public void loadDefaultScreen()
    {
        responseLabel.setText(" ");
        libNameField.setText(loadScreenLabelString);
    }

    public void showMessage(final String message, Color color)
    {
        responseLabel.setText(message);

        if (color != null)
            responseLabel.setForeground(color);
    }

    public void showScreen()
    {
        this.setVisible(true);
        this.requestFocus();
    }

}
