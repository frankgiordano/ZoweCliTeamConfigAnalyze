package zowe.teamconfig.model;

public class ProfileDao {

    private final Profile profile;
    private final String user;
    private final String password;
    private final String host;
    private final String port;

    public ProfileDao(Profile profile, String user, String password, String host, String port) {
        this.profile = profile;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return "ProfileDao{" +
                "profile=" + profile +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

}
