package zowecli.globalteamconfig.sections;

import zowecli.globalteamconfig.types.ProfileType;

import java.util.Map;

public class Profile {

    private ProfileType type;
    private Map<String, String> profileProperties;
    private String secure;

    public Profile(ProfileType type, Map<String, String> profileProperties, String secure) {
        this.type = type;
        this.profileProperties = profileProperties;
        this.secure = secure;
    }

    public ProfileType getType() {
        return type;
    }

    public Map<String, String> getProfileProperties() {
        return profileProperties;
    }

    public String getSecure() {
        return secure;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "type=" + type +
                ", profileProperties=" + profileProperties +
                ", secure='" + secure + '\'' +
                '}';
    }

}
