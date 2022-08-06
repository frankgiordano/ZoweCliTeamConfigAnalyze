package zowecli.globalteamconfig.sections;

import java.util.List;

public class Partition {

    private ProfileProperties profileProperties;
    private List<Profile> profiles;

    public Partition(ProfileProperties profileProperties, List<Profile> profiles) {
        this.profileProperties = profileProperties;
        this.profiles = profiles;
    }

    public ProfileProperties getProfileProperties() {
        return profileProperties;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

}
