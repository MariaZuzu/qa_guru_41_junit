package guru.qa;

public enum Language {
    RU("ru", "Акции"),
    EN("en","Offers");

    public final String langCode;
    public final String expectedMenuItemText;

    Language(String langCode, String expectedMenuItemText) {
        this.langCode = langCode;
        this.expectedMenuItemText = expectedMenuItemText;
    }
}
