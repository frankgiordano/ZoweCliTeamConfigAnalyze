package zowecli;

public class Config {

    private String location;
    private String userName;
    private String password;

    public Config(String location, String userName, String password) {
        this.location = location;
        this.userName = userName;
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "teamconfig.Config{" +
                "location='" + location + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
