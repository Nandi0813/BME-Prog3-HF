package dev.nandi.phonelib;


import dev.nandi.phonelib.Phonebook.PhonebookManager;
import dev.nandi.phonelib.Screens.LoadScreen;

import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    private static LoadScreen loadScreen;
    public static final File directory = getDirectory();

    private static PhonebookManager phonebookManager;

    public static void main(String[] args)
    {
        phonebookManager = new PhonebookManager();

        loadScreen = new LoadScreen();
        loadScreen.loadDefaultScreen();

        phonebookManager = new PhonebookManager();

        if (!phonebookManager.loadPhonebooks())
            loadScreen.showMessage(Language.LOAD_SCREEN_ZERO_LOAD.getMessage(), Color.ORANGE);

        loadScreen.setVisible(true);
    }

    private static File getDirectory()
    {
        File dir = new File("phonebooks");

        if (!dir.exists() && dir.mkdir())
        {
            logger.log(Level.INFO, Language.DIRECTORY_CREATED.getMessage());
        }

        return dir;
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