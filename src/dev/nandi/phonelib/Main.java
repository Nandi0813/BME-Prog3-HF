package dev.nandi.phonelib;

import dev.nandi.phonelib.Phonebook.PhonebookManager;
import dev.nandi.phonelib.Graphic.Screens.LoadScreen;
import dev.nandi.phonelib.Graphic.Screens.Main.MainScreen;

import java.awt.*;
import java.io.File;
import java.util.logging.Logger;

/**
 * A program belépési pontja.
 */
public class Main
{

    /**
     * A program loggerje.
     */
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * A program mappája.
     */
    public static final File directory = Util.getDirectory();

    /**
     * A programban használt telefonkönyv kezelő.
     */
    private static PhonebookManager phonebookManager;

    /**
     * A programban használt képernyők.
     */
    private static LoadScreen loadScreen;
    private static MainScreen mainScreen;

    /**
     * A program belépési pontja.
     * @param args A program argumentumai.
     */
    public static void main(String[] args)
    {
        phonebookManager = new PhonebookManager();

        loadScreen = new LoadScreen();
        loadScreen.loadDefaultScreen();

        if (!phonebookManager.loadPhonebooks())
            loadScreen.showMessage("Egy telefonkönyv se került betöltésre.", Color.ORANGE);

        loadScreen.showScreen();
    }

    /**
     * Getterek
     */
    public static PhonebookManager getPhonebookManager()
    {
        return phonebookManager;
    }
    public static LoadScreen getLoadScreen()
    {
        return loadScreen;
    }
    public static MainScreen getMainScreen()
    {
        return mainScreen;
    }

    /**
     * Setterek
     */
    public static void setMainScreen(MainScreen mainScreen)
    {
        Main.mainScreen = mainScreen;
    }

}