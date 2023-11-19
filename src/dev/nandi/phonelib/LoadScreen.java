package dev.nandi.phonelib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

public class LoadScreen extends JFrame
{

    private final JTextField libNameField;
    private final JLabel responseLabel;

    public LoadScreen()
    {
        setTitle(Language.LOAD_SCREEN_TITLE.getMessage());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

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
        libNameField = new JTextField(Language.LOAD_SCREEN_LABEL.getMessage());
        libNameField.setPreferredSize(new Dimension(400, 40));
        libNameField.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(libNameField);

        // FocusListener hozzáadása, hogy eltűnjön a szöveg, amikor a felhasználó rákattint az input mezőre
        libNameField.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (libNameField.getText().equals(Language.LOAD_SCREEN_LABEL.getMessage()))
                {
                    libNameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (libNameField.getText().isEmpty())
                {
                    libNameField.setText(Language.LOAD_SCREEN_LABEL.getMessage());
                }
            }
        });

        // Gombokat tartalmazó panel inicializálása
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // Betöltés gomb inicializálása
        JButton loadButton = new JButton(Language.LOAD_SCREEN_LOAD_BUTTON.getMessage());
        loadButton.addActionListener(e -> loadDirectory());
        buttonsPanel.add(loadButton);

        // Új könyvtár létrehozása gomb inicializálása
        JButton createButton = new JButton(Language.LOAD_SCREEN_NEW_BUTTON.getMessage());
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
        String fileName = libNameField.getText();

        if (fileName.isEmpty() || fileName.equalsIgnoreCase(Language.LOAD_SCREEN_LABEL.getMessage()))
        {
            showMessage(Language.LOAD_SCREEN_EMPTY_INPUT.getMessage(), Color.RED);
        }
        else
        {
            File file = new File(Main.phonebooksDirectory, fileName + ".txt");

            if (file.exists())
            {
                showMessage(Language.LOAD_SCREEN_PHONEBOOK_LOADING.getMessage(), Color.GREEN);

                Main.scheduleTask(() -> {
                    setVisible(false);
                }, 2);
            }
            else
            {
                showMessage(Language.LOAD_SCREEN_PHONEBOOK_NOT_EXISTS.getMessage(), Color.RED);
            }
        }
    }

    private void createNewDirectory()
    {
        String libName = libNameField.getText();

        if (libName.isEmpty() || libName.equalsIgnoreCase(Language.LOAD_SCREEN_LABEL.getMessage()))
        {
            showMessage(Language.LOAD_SCREEN_EMPTY_INPUT.getMessage(), Color.RED);
        }
        else if (libName.contains(" "))
        {
            showMessage(Language.LOAD_SCREEN_NO_SPACE_CHAR.getMessage(), Color.RED);
        }
        else if (libName.length() > 10)
        {
            showMessage(Language.LOAD_SCREEN_MAX_CHAR.getMessage(), Color.RED);
        }
        else
        {
            File file = new File(Main.phonebooksDirectory, libName + ".txt");

            if (file.exists())
            {
                showMessage(Language.LOAD_SCREEN_ALREADY_EXISTS.getMessage(), Color.RED);
            }
            else
            {
                showMessage(Language.LOAD_SCREEN_CREATING_PHONEBOOK.getMessage().replace("%name%", libName), Color.GREEN);

                Main.scheduleTask(() ->
                {
                    setVisible(false);
                }, 2);
            }
        }
    }

    public void loadDefaultScreen()
    {
        responseLabel.setText(" ");
        libNameField.setText(Language.LOAD_SCREEN_LABEL.getMessage());
    }

    public void showMessage(final String message, Color color)
    {
        responseLabel.setText(message);

        if (color != null)
            responseLabel.setForeground(color);
    }

}
