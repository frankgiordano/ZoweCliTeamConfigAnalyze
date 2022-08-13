package zowe.teamconfig.model;

import java.util.List;
import java.util.Map;

public class Partition {

    private final String name;
    private final Map<String, String> profileProperties;
    private final List<Profile> profiles;

    public Partition(String name, Map<String, String> profileProperties, List<Profile> profiles) {
        this.name = name;
        this.profileProperties = profileProperties;
        this.profiles = profiles;
    }

    public Map<String, String> getProfileProperties() {
        return profileProperties;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public String toString() {
        return "Partition{" +
                "name='" + name + '\'' +
                ", profileProperties=" + profileProperties +
                ", profiles=" + profiles +
                '}';
    }

}
