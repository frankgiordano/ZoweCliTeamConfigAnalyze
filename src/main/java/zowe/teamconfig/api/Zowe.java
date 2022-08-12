package zowe.teamconfig.api;

import zowe.service.KeyTarService;
import zowe.service.TeamConfigService;
import zowe.teamconfig.config.TeamConfig;
import zowe.teamconfig.sections.Profile;
import zowe.teamconfig.types.ProfileType;
import zowe.keytar.KeyTarConfig;
import zowe.model.ProfileDao;

import java.util.Optional;

public class Zowe {

    private TeamConfigService teamConfigService;
    private KeyTarService keyTarService;

    public Zowe(KeyTarService keyTarService, TeamConfigService teamConfigService) {
        this.keyTarService = keyTarService;
        this.teamConfigService = teamConfigService;
    }

    public ProfileDao getDefaultProfileByName(String name) throws Exception {
        KeyTarConfig keyTarConfig = keyTarService.getKeyTar();
        System.out.println(keyTarConfig);
        TeamConfig teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        System.out.println(teamConfig);
        Optional<Profile> target = teamConfig.getProfiles().stream().filter(i -> name.equals(i.getName())).findFirst();
        Optional<Profile> base = teamConfig.getProfiles().stream().filter(i -> "base".equals(i.getName())).findFirst();
        if (target.isEmpty()) {
            throw new Exception("No Zowe team config profile found");
        }

        // check profile properties hashmap variable for host and port values
        // if they don't exist there, then check the base profile properties variable
        var targetProps = Optional.ofNullable(target.get().getProperties());
        Optional<String> host = Optional.empty();
        Optional<String> port = Optional.empty();
        if (targetProps.isPresent()) {
            host = Optional.ofNullable(targetProps.get().get("host"));
            port = Optional.ofNullable(targetProps.get().get("port"));
        }
        if (host.isEmpty()) {
            if (base.isEmpty()) {
                throw new Exception("No Zowe team config base profile and host property found");
            }
            var baseProps = Optional.ofNullable(base.get().getProperties());
            if (baseProps.isPresent()) {
                host = Optional.ofNullable(baseProps.get().get("host"));
            }
        }
        if (port.isEmpty()) {
            if (base.isEmpty()) {
                throw new Exception("No Zowe team config base profile and port property found");
            }
            var baseProps = Optional.ofNullable(base.get().getProperties());
            if (baseProps.isPresent()) {
                port = Optional.ofNullable(baseProps.get().get("port"));
            }
        }

        return new ProfileDao(target.get(), keyTarConfig.getUserName(), keyTarConfig.getPassword(),
                host.orElseThrow(() -> new RuntimeException("No host found for profile")),
                port.orElseThrow(() -> new RuntimeException("No port found for profile")));
    }

    public ProfileDao getDefaultProfileBType(ProfileType type) {
        // TODO
        return null;
    }

    public ProfileDao getDefaultPartitionProfileByName(String partitionName, String profileName) {
        // TODO
        return null;
    }

    public ProfileDao getDefaultPartitionProfileBType(String partitionName, ProfileType type) {
        // TODO
        return null;
    }


}
