package zowecli.globalteamconfig.sections;

public class Defaults {

    private String zosmf;
    private String tso;
    private String ssh;
    private String sysview;
    private String sysviewFormat;
    private String base;

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
