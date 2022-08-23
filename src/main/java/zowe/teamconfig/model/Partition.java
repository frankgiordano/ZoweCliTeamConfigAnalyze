package zowe.teamconfig.model;

import java.util.List;
import java.util.Map;

public class Partition {

    private final String name;
    private final Map<String, String> properties;
    private final List<Profile> profiles;

    public Partition(String name, Map<String, String> properties, List<Profile> profiles) {
        this.name = name;
        this.properties = properties;
        this.profiles = profiles;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public String toString() {
        return "Partition{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                ", profiles=" + profiles +
                '}';
    }

}
