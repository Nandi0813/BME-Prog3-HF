package dev.nandi.phonelib.Phonebook;

import java.util.HashMap;
import java.util.Map;

public class PhonebookManager
{

    private final Map<String, Phonebook> phonebooks = new HashMap<>();

    /*
     * @return true ha legalább egy telefonkönyv betöltésre került
     */
    public boolean loadPhonebooks()
    {
        return false;
    }

    public Map<String, Phonebook> getPhonebooks()
    {
        return phonebooks;
    }

}
