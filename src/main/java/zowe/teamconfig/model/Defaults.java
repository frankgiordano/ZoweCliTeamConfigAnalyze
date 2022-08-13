package zowe.teamconfig.model;

public class Defaults {

    private final String zosmf;
    private final String tso;
    private final String ssh;
    private final String sysview;
    private final String sysviewFormat;
    private final String base;

    public Defaults(String zosmf, String tso, String ssh, String sysview, String sysviewFormat, String base) {
        this.zosmf = zosmf;
        this.tso = tso;
        this.ssh = ssh;
        this.sysview = sysview;
        this.sysviewFormat = sysviewFormat;
        this.base = base;
    }

    public String getZosmf() {
        return zosmf;
    }

    public String getTso() {
        return tso;
    }

    public String getSsh() {
        return ssh;
    }

    public String getSysview() {
        return sysview;
    }

    public String getSysviewFormat() {
        return sysviewFormat;
    }

    public String getBase() {
        return base;
    }

    @Override
    public String toString() {
        return "Defaults{" +
                "zosmf='" + zosmf + '\'' +
                ", tso='" + tso + '\'' +
                ", ssh='" + ssh + '\'' +
                ", sysview='" + sysview + '\'' +
                ", sysviewFormat='" + sysviewFormat + '\'' +
                ", base='" + base + '\'' +
                '}';
    }

}
