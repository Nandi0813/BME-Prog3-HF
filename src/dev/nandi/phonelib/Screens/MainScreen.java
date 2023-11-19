package dev.nandi.phonelib.Screens;

import dev.nandi.phonelib.Language;
import dev.nandi.phonelib.Main;
import dev.nandi.phonelib.Phonebook.Phonebook;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainScreen extends JFrame
{

    private final JTable contactTable;

    private final Phonebook phonebook;

    private Dimension screenSize = new Dimension(1280, 720);

    public MainScreen(Phonebook phonebook)
    {
        super(Language.MAIN_SCREEN_TITLE.getMessage().replace("%name%", phonebook.getName()));

        this.phonebook = phonebook;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(screenSize);
        this.setPreferredSize(screenSize);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.contactTable = new JTable();
        this.contactTable.setModel(phonebook);
        this.contactTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Main.getPhonebookManager().savePhonebook(phonebook);
            }
        });

        this.contactTable.setIntercellSpacing(new Dimension(10, 5)); // cellák ne folyjanak össze
        this.contactTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(this.contactTable);

        // Panelek
        JPanel contactPanel = new JPanel();
        contactPanel.setBorder(BorderFactory.createTitledBorder("Kontaktok"));
        contactPanel.setLayout(new BorderLayout());

        // komponenspanel a táblázat fölé
        JPanel contactNorthPanel = new JPanel();
        BoxLayout northPanelLayout = new BoxLayout(contactNorthPanel, BoxLayout.X_AXIS);

        contactNorthPanel.setLayout(northPanelLayout);
        JButton addContactButton = new JButton("Kontakt hozzáadása");
        // addBookButton.addActionListener(actionEvent -> showAddBookDialog());
        contactNorthPanel.add(addContactButton);

        contactNorthPanel.add(Box.createHorizontalGlue());

        JButton editContactButton = new JButton("Kontakt szerkesztése");
        /*returnBookButton.addActionListener(actionEvent -> {
            if (bookTable.getSelectedRow() >= 0)
                this.library.getBookData().setValueAt(null, bookTable.convertRowIndexToModel(bookTable.getSelectedRow()), 6);
        });*/
        contactNorthPanel.add(editContactButton);

        contactNorthPanel.add(Box.createHorizontalGlue());

        JButton deleteContactButton = new JButton("Kontakt törlése");
        /*returnBookButton.addActionListener(actionEvent -> {
            if (bookTable.getSelectedRow() >= 0)
                this.library.getBookData().setValueAt(null, bookTable.convertRowIndexToModel(bookTable.getSelectedRow()), 6);
        });*/
        contactNorthPanel.add(deleteContactButton);

        contactNorthPanel.add(Box.createHorizontalGlue());

        contactPanel.add(contactNorthPanel, BorderLayout.NORTH);
        contactPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(contactPanel);
    }

    static class ButtonRenderer extends DefaultTableCellRenderer
    {
        private final JButton button = new JButton();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (value == null) {
                return null;
            }

            button.setText(value.toString());
            return button;
        }
    }

}
