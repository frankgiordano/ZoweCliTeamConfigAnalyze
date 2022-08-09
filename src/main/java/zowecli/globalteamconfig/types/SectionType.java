package zowecli.globalteamconfig.types;

public enum SectionType {

    $SCHEMA("$schema"),
    PROFILES("profiles"),
    DEFAULTS("defaults"),
    AUTOSTORE("autoStore");

    SectionType(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

}


