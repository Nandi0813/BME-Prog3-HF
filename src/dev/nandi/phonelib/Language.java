package dev.nandi.phonelib;

public enum Language
{

    DIRECTORY_CREATED("Telefonkönyv könyvtár létrehozva."),

    LOAD_SCREEN_TITLE("Telefonkönyv betöltése"),
    LOAD_SCREEN_LOAD_BUTTON("Betöltés"),
    LOAD_SCREEN_NEW_BUTTON("Új könyvtár"),
    LOAD_SCREEN_LABEL("Írd ide a telefonkönyv nevét"),
    LOAD_SCREEN_EMPTY_INPUT("Adj meg egy telefonkönyv nevet!"),
    LOAD_SCREEN_ALREADY_EXISTS("A telefonkönyv már létezik, használd a betöltés gombot."),
    LOAD_SCREEN_NO_SPACE_CHAR("A telefonkönyv neve nem tartalmazhat szóközt!"),
    LOAD_SCREEN_MAX_CHAR("A telefonkönyv neve maximum 10 karakter lehet!"),
    LOAD_SCREEN_CREATING_PHONEBOOK("A telefonkönyv létrehozása folyamatban %name% néven.."),
    LOAD_SCREEN_PHONEBOOK_NOT_EXISTS("A telefonkönyv nem létezik, használd az új könyvtár gombot."),
    LOAD_SCREEN_PHONEBOOK_LOADING("A telefonkönyv betöltése folyamatban.."),
    ;

    private final String message;

    Language(final String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

}
