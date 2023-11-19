package dev.nandi.phonelib;


import dev.nandi.phonelib.Phonebook.PhonebookManager;

import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{

    static Logger logger = Logger.getLogger(Main.class.getName());

    private static LoadScreen loadScreen;
    public static File phonebooksDirectory;

    private static PhonebookManager phonebookManager;

    public static void main(String[] args)
    {
        initializePhonebooksDirectory();

        loadScreen = new LoadScreen();
        loadScreen.loadDefaultScreen();

        phonebookManager = new PhonebookManager();

        if (!phonebookManager.loadPhonebooks())
            loadScreen.showMessage(Language.LOAD_SCREEN_ZERO_LOAD.getMessage(), Color.ORANGE);

        loadScreen.setVisible(true);
    }

    private static void initializePhonebooksDirectory()
    {
        phonebooksDirectory = new File("phonebooks");

        if (!phonebooksDirectory.exists() && phonebooksDirectory.mkdir())
        {
            logger.log(Level.INFO, Language.DIRECTORY_CREATED.getMessage());
        }
    }

    public static void scheduleTask(Runnable code, long delayInSeconds)
    {
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                code.run();
            }
        }, delayInSeconds * 1000);
    }

    public static PhonebookManager getPhonebookManager()
    {
        return phonebookManager;
    }

}