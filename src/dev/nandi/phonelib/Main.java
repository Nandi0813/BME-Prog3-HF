package dev.nandi.phonelib;


import dev.nandi.phonelib.Phonebook.PhonebookManager;

import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Main
{

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
            loadScreen.showMessage("Egy telefonkönyv se került betöltésre.", Color.ORANGE);

        loadScreen.setVisible(true);
    }

    private static void initializePhonebooksDirectory()
    {
        phonebooksDirectory = new File("phonebooks");

        if (!phonebooksDirectory.exists() && phonebooksDirectory.mkdir())
        {
            System.out.println(Language.DIRECTORY_CREATED.getMessage());
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