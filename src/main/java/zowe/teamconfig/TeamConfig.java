package zowe.teamconfig;

import zowe.teamconfig.model.ProfileDao;
import zowe.teamconfig.service.KeyTarService;
import zowe.teamconfig.service.TeamConfigService;
import zowe.teamconfig.types.ProfileType;

import java.util.Optional;

public class TeamConfig {

    private final TeamConfigService teamConfigService;
    private final KeyTarService keyTarService;

    public TeamConfig(KeyTarService keyTarService, TeamConfigService teamConfigService) {
        this.keyTarService = keyTarService;
        this.teamConfigService = teamConfigService;
    }

    public ProfileDao getDefaultProfileByName(String name) throws Exception {
        var keyTarConfig = keyTarService.getKeyTarConfig();
        System.out.println(keyTarConfig);
        var teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        System.out.println(teamConfig);
        var target = teamConfig.getProfiles().stream().filter(i -> name.equals(i.getName())).findFirst();
        var base = teamConfig.getProfiles().stream().filter(i -> "base".equals(i.getName())).findFirst();
        // check team config defaults object for default name value
        // TODO
        if (target.isEmpty()) {
            throw new Exception("No Zowe team config " + name + " profile found");
        }
        if (base.isEmpty()) {
            throw new Exception("No Zowe team config base profile found");
        }

        // check profile properties hashmap variable for host and port values
        // if they don't exist there, then check the base profile properties variable
        var targetProps = Optional.ofNullable(target.get().getProperties());
        var baseProps = Optional.ofNullable(base.get().getProperties());
        Optional<String> host = Optional.empty();
        Optional<String> port = Optional.empty();
        if (targetProps.isPresent()) {
            host = Optional.ofNullable(targetProps.get().get("host"));
            port = Optional.ofNullable(targetProps.get().get("port"));
        }
        if (host.isEmpty() && baseProps.isPresent()) {
            host = Optional.ofNullable(baseProps.get().get("host"));
        }
        if (port.isEmpty() && baseProps.isPresent()) {
            port = Optional.ofNullable(baseProps.get().get("port"));
        }

        return new ProfileDao(target.get(), keyTarConfig.getUserName(), keyTarConfig.getPassword(),
                host.orElseThrow(() -> new RuntimeException("No host found for profile")),
                port.orElseThrow(() -> new RuntimeException("No port found for profile")));
    }

    public ProfileDao getDefaultProfileByType(ProfileType type) {
        // TODO
        return null;
    }

    public ProfileDao getDefaultPartitionProfileByName(String partitionName, String profileName) {
        // TODO
        return null;
    }

    public ProfileDao getDefaultPartitionProfileByType(String partitionName, ProfileType type) {
        // TODO
        return null;
    }

}
