package zowecli.globalteamconfig.sections;

import zowecli.globalteamconfig.types.ProfileType;

public class Profile {

    private ProfileType type;
    private ProfileProperties profileProperties;
    private String secure;

    public Profile(ProfileType type, ProfileProperties profileProperties, String secure) {
        this.type = type;
        this.profileProperties = profileProperties;
        this.secure = secure;
    }

    public ProfileType getType() {
        return type;
    }

    public ProfileProperties getProfileProperties() {
        return profileProperties;
    }

    public String getSecure() {
        return secure;
    }

}
