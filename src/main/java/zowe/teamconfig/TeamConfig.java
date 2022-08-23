package zowe.teamconfig;

import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.model.ConfigContainer;
import zowe.teamconfig.model.Partition;
import zowe.teamconfig.model.Profile;
import zowe.teamconfig.model.ProfileDao;
import zowe.teamconfig.service.KeyTarService;
import zowe.teamconfig.service.TeamConfigService;

import java.util.Optional;
import java.util.function.Predicate;

public class TeamConfig {

    private final TeamConfigService teamConfigService;
    private final KeyTarService keyTarService;
    private final String BASE_PROFILE_NAME = "base";
    private final Predicate<Profile> isBaseProfile = i -> i.getName().equals(BASE_PROFILE_NAME);
    private final MergeProperties mergeProperties = new MergeProperties();
    private KeyTarConfig keyTarConfig;
    private ConfigContainer teamConfig;

    public TeamConfig(KeyTarService keyTarService, TeamConfigService teamConfigService) throws Exception {
        this.keyTarService = keyTarService;
        this.teamConfigService = teamConfigService;
        config();
    }

    private void config() throws Exception {
        keyTarConfig = keyTarService.getKeyTarConfig();
        teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        System.out.println(keyTarConfig);
        System.out.println(teamConfig);
    }

    public ProfileDao getDefaultProfileByName(String name) throws Exception {
        final var defaultName = Optional.ofNullable(teamConfig.getDefaults().get(name));
        final Predicate<Profile> isProfileName = i -> i.getName().equals(defaultName.orElse(name));
        final var base = teamConfig.getProfiles().stream().filter(isBaseProfile).findFirst();

        final var target = teamConfig.getProfiles().stream().filter(isProfileName).findFirst();
        if (target.isEmpty()) {
            throw new Exception("Profile " + name + " not found");
        }

        merge(target, base);
        return new ProfileDao(target.get(), keyTarConfig.getUserName(), keyTarConfig.getPassword(),
                mergeProperties.getHost().orElse(null), mergeProperties.getPort().orElse(null));
    }

    public ProfileDao getDefaultProfileFromPartitionByName(String profileName, String partitionName) throws Exception {
        final var defaultName = Optional.ofNullable(teamConfig.getDefaults().get(profileName));
        final Predicate<Profile> isProfileName = i -> i.getName().equals(defaultName.orElse(profileName));
        final Predicate<Partition> isPartitionName = i -> i.getName().equals(partitionName);
        final var base = teamConfig.getProfiles().stream().filter(isBaseProfile).findFirst();

        final var partition = teamConfig.getPartitions().stream().filter(isPartitionName).findFirst();
        if (partition.isEmpty()) {
            throw new Exception("Partition " + partitionName + " not found");
        }

        final var target = partition.get().getProfiles().stream().filter(isProfileName).findFirst();
        if (target.isEmpty()) {
            throw new Exception("Profile " + profileName + " within partition not found");
        }

        final var props = partition.get().getProperties();
        mergeProperties.setHost(props.get("host"));
        mergeProperties.setPort(props.get("port"));

        merge(target, base);
        return new ProfileDao(target.get(), keyTarConfig.getUserName(), keyTarConfig.getPassword(),
                mergeProperties.getHost().orElse(null), mergeProperties.getPort().orElse(null));
    }

    private void merge(Optional<Profile> target, Optional<Profile> base) {
        // check profile properties hashmap variable for host and port values
        // if they don't exist there, then check the base profile properties variable
        final var targetProps = Optional.ofNullable(target.get().getProperties());
        final var baseProps = Optional.ofNullable(base.get().getProperties());
        if (mergeProperties.getHost().isEmpty() && targetProps.isPresent()) {
            mergeProperties.setHost(targetProps.get().get("host"));
        }
        if (mergeProperties.getPort().isEmpty() && targetProps.isPresent()) {
            mergeProperties.setPort(targetProps.get().get("port"));
        }
        if (mergeProperties.getHost().isEmpty() && baseProps.isPresent()) {
            mergeProperties.setHost(baseProps.get().get("host"));
        }
        if (mergeProperties.getPort().isEmpty() && baseProps.isPresent()) {
            mergeProperties.setPort(baseProps.get().get("port"));
        }
    }

    class MergeProperties {
        private Optional<String> host = Optional.empty();
        private Optional<String> port = Optional.empty();

        public Optional<String> getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = Optional.ofNullable(host);
        }

        public Optional<String> getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = Optional.ofNullable(port);
        }
    }

}
