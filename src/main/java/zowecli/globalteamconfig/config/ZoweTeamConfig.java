package zowecli.globalteamconfig.config;

import zowecli.globalteamconfig.sections.*;

import java.util.List;

public class ZoweTeamConfig {

    private List<Partition> partitions;
    private Schema schema;
    private List<Profile> profiles;
    private Defaults defaults;
    private AutoStore autoStore;

    public ZoweTeamConfig(List<Partition> partitions, Schema schema, List<Profile> profiles,
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
