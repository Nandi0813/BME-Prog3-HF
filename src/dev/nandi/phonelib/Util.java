package dev.nandi.phonelib;

import dev.nandi.phonelib.Phonebook.Phonebook;
import dev.nandi.phonelib.Graphic.Screens.Main.MainScreen;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class Util
{

    private Util() { } // Prevents instantiation (static class

    public static File getDirectory()
    {
        File dir = new File("phonebooks");

        if (!dir.exists() && dir.mkdir())
        {
            Main.logger.log(Level.INFO, "Telefonkönyv könyvtár létrehozva.");
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

    public static MainScreen createMainScreen(final Phonebook phonebook)
    {
        MainScreen mainScreen = new MainScreen(phonebook);

        mainScreen.setVisible(true);

        return mainScreen;
    }

    public static String formatDate(Date date, String pattern)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

}
