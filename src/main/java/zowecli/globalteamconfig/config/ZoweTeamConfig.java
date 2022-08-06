package zowecli.globalteamconfig.config;

import zowecli.globalteamconfig.sections.*;

import java.util.List;

public class ZoweTeamConfig {

    private List<Partition> partitions;
    private Schema schema;
    private List<Profile> profiles;
    private Defaults defaults;
    private AutoStore autoStore;

    public List<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Partition> partitions) {
        this.partitions = partitions;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public void setDefaults(Defaults defaults) {
        this.defaults = defaults;
    }

    public AutoStore getAutoStore() {
        return autoStore;
    }

    public void setAutoStore(AutoStore autoStore) {
        this.autoStore = autoStore;
    }
    
}
