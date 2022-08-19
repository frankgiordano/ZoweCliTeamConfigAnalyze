package zowe.teamconfig.model;

import java.util.List;
import java.util.Map;

public class ConfigContainer {

    private final List<Partition> partitions;
    private final String schema;
    private final List<Profile> profiles;
    private final Map<String, String> defaults;
    private final Boolean autoStore;

    public ConfigContainer(List<Partition> partitions, String schema, List<Profile> profiles,
                           Map<String, String> defaults, Boolean autoStore) {
        this.partitions = partitions;
        this.schema = schema;
        this.profiles = profiles;
        this.defaults = defaults;
        this.autoStore = autoStore;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public String getSchema() {
        return schema;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Map<String, String> getDefaults() {
        return defaults;
    }

    public Boolean getAutoStore() {
        return autoStore;
    }

    @Override
    public String toString() {
        return "ZoweTeamConfig{" +
                "partitions=" + partitions +
                ", schema=" + schema +
                ", profiles=" + profiles +
                ", defaults=" + defaults +
                ", autoStore=" + autoStore +
                '}';
    }

}
