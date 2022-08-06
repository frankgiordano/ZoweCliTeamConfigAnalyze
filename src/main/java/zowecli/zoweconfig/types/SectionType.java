package zowecli.zoweconfig.types;

public enum SectionType {

    $SCHEMA("$schema"),
    PROFILES("profiles"),
    DEFAULTS("defaults"),
    AUTOSTORE("autoStore");

    private SectionType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}


