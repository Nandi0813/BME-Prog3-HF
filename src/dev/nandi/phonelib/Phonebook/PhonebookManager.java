package dev.nandi.phonelib.Phonebook;

import dev.nandi.phonelib.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class PhonebookManager
{

    private final Map<String, Phonebook> phonebooks = new HashMap<>();

    public boolean loadPhonebooks()
    {
        File[] files = Main.directory.listFiles();
        if (files == null)
            return false;

        for (File file : files)
        {
            if (file.getName().endsWith(".txt"))
                loadPhonebook(file);
        }

        return true;
    }

    public void loadPhonebook(final File file)
    {
        String absolutePath = file.getAbsolutePath();

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(absolutePath))))
        {
            Object readObject = ois.readObject();
            if (readObject instanceof Phonebook)
            {
                Phonebook phonebook = (Phonebook) readObject;
                this.phonebooks.put(phonebook.getName(), phonebook);
            }
        }
        catch (StreamCorruptedException streamCorruptedException) {
            Main.logger.log(Level.SEVERE, "A betölteni kivánt telefonkönyv fájlja sérült. File: " + absolutePath);
        } catch (Exception exception) {
            Main.logger.log(Level.SEVERE, "Hiba történt a fájl beolvasása közben. File: " + absolutePath);
        }
    }

    public void savePhonebook(final Phonebook phonebook)
    {
        File file = new File(Main.directory, phonebook.getName() + ".txt");

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(file.getAbsolutePath()))))
        {
            outputStream.writeObject(phonebook);
        }
        catch (IOException ex) {
            Main.logger.log(Level.SEVERE, "Hiba történt a fájl mentése közben. Név: " + phonebook.getName());
        }
    }

    public void deletePhonebook(final Phonebook phonebook)
    {
        this.phonebooks.remove(phonebook.getName(), phonebook);

        File file = new File(Main.directory, phonebook.getName() + ".txt");
        if (file.exists() && file.delete())
            Main.logger.log(Level.INFO, phonebook.getName() + " nevű telefonkönyv sikeresen törlődött.");
    }

    public Map<String, Phonebook> getPhonebooks() { return phonebooks; }

}
