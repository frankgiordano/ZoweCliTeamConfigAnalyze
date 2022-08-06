package zowecli.globalteamconfig.sections;

import java.util.List;

public class Partition {

    private ProfileProperties profileProperties;
    private List<Profile> profiles;

    public ProfileProperties getProfileProperties() {
        return profileProperties;
    }

    public void setProfileProperties(ProfileProperties profileProperties) {
        this.profileProperties = profileProperties;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

}
