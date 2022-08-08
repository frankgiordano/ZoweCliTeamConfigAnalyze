package zowecli.globalteamconfig.types;

public enum ProfileType {

    ZOSMF("zosmf"),
    TSO("tso"),
    SSH("ssh"),
    SYSVIEW("sysview"),
    SYSVIEWFORMAT("sysview-format"),
    BASE("base");

    private ProfileType(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

}
