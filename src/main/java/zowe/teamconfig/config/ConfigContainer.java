package zowe.teamconfig.config;

import zowe.teamconfig.sections.*;

import java.util.List;

public class ConfigContainer {

    private final List<Partition> partitions;
    private final Schema schema;
    private final List<Profile> profiles;
    private final Defaults defaults;
    private final AutoStore autoStore;

    public ConfigContainer(List<Partition> partitions, Schema schema, List<Profile> profiles,
                           Defaults defaults, AutoStore autoStore) {
        this.partitions = partitions;
        this.schema = schema;
        this.profiles = profiles;
        this.defaults = defaults;
        this.autoStore = autoStore;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public Schema getSchema() {
        return schema;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public AutoStore getAutoStore() {
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
