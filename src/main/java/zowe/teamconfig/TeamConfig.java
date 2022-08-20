package zowe.teamconfig;

import zowe.teamconfig.model.Profile;
import zowe.teamconfig.model.ProfileDao;
import zowe.teamconfig.service.KeyTarService;
import zowe.teamconfig.service.TeamConfigService;

import java.util.Optional;
import java.util.function.Predicate;

public class TeamConfig {

    private final TeamConfigService teamConfigService;
    private final KeyTarService keyTarService;

    public TeamConfig(KeyTarService keyTarService, TeamConfigService teamConfigService) {
        this.keyTarService = keyTarService;
        this.teamConfigService = teamConfigService;
    }

    public ProfileDao getDefaultProfileByName(String name) throws Exception {
        final var keyTarConfig = keyTarService.getKeyTarConfig();
        final var teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        final var defaultName = Optional.ofNullable(teamConfig.getDefaults().get(name));
        final Predicate<Profile> isProfileName = i -> i.getName().equals(defaultName.orElse(name));
        final Predicate<Profile> isBaseProfile = i -> i.getName().equals("base");
        final var target = teamConfig.getProfiles().stream().filter(isProfileName).findFirst();
        final var base = teamConfig.getProfiles().stream().filter(isBaseProfile).findFirst();
        System.out.println(keyTarConfig);
        System.out.println(teamConfig);

        if (target.isEmpty()) {
            throw new Exception("No Zowe team config " + name + " profile found");
        }
        if (base.isEmpty()) {
            throw new Exception("No Zowe team config base profile found");
        }

        // check profile properties hashmap variable for host and port values
        // if they don't exist there, then check the base profile properties variable
        final var targetProps = Optional.ofNullable(target.get().getProperties());
        final var baseProps = Optional.ofNullable(base.get().getProperties());
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
                host.orElse(null), port.orElse(null));
    }

    public ProfileDao getDefaultProfileFromPartitionByName(String profileName, String partitionName) {
        // TODO
        return null;
    }

}
