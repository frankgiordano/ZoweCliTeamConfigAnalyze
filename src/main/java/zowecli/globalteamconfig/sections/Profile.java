package zowecli.globalteamconfig.sections;

import zowecli.globalteamconfig.types.ProfileType;

public class Profile {

    private ProfileType type;
    private ProfileProperties profileProperties;
    private String secure;

    public ProfileType getType() {
        return type;
    }

    public void setType(ProfileType type) {
        this.type = type;
    }

    public ProfileProperties getProfileProperties() {
        return profileProperties;
    }

    public void setProfileProperties(ProfileProperties profileProperties) {
        this.profileProperties = profileProperties;
    }

    public String getSecure() {
        return secure;
    }

    public void setSecure(String secure) {
        this.secure = secure;
    }

}
