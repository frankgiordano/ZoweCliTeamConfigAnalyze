package zowe.teamconfig.model;

import zowe.teamconfig.sections.Profile;

public class ProfileDao {

    private Profile profile;
    private String user;
    private String password;
    private String host;
    private String port;

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
