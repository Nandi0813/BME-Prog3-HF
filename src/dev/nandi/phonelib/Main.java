package dev.nandi.phonelib;


import dev.nandi.phonelib.Phonebook.PhonebookManager;
import dev.nandi.phonelib.Graphic.Screens.LoadScreen;
import dev.nandi.phonelib.Graphic.Screens.Main.MainScreen;

import java.awt.*;
import java.io.File;
import java.util.logging.Logger;

public class Main
{

    public static final Logger logger = Logger.getLogger(Main.class.getName());
    public static final File directory = Util.getDirectory();

    private static PhonebookManager phonebookManager;
    private static LoadScreen loadScreen;
    private static MainScreen mainScreen;

    public static void main(String[] args)
    {
        phonebookManager = new PhonebookManager();

        loadScreen = new LoadScreen();
        loadScreen.loadDefaultScreen();

        if (!phonebookManager.loadPhonebooks())
            loadScreen.showMessage("Egy telefonkönyv se került betöltésre.", Color.ORANGE);

        loadScreen.showScreen();
    }

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

    public static void setMainScreen(MainScreen mainScreen)
    {
        Main.mainScreen = mainScreen;
    }

}