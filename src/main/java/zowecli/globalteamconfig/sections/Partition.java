package zowecli.globalteamconfig.sections;

import java.util.List;

public class Partition {

    private String name;
    private ProfileProperties profileProperties;
    private List<Profile> profiles;

    public Partition(String name, ProfileProperties profileProperties, List<Profile> profiles) {
        this.name = name;
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
