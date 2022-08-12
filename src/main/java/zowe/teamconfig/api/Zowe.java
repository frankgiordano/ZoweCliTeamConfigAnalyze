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
        Optional<Profile> targetProfile = teamConfig.getProfiles().stream().filter(i -> name.equals(i.getName())).findFirst();
        Optional<Profile> baseProfile = teamConfig.getProfiles().stream().filter(i -> "base".equals(i.getName())).findFirst();
        String host = null;
        String port = null;
        // check profile properties hashmap variable for host, name, and port values
        // if they don't exist there, then check the base profile properties variable
        ProfileDao profileDao = new ProfileDao(targetProfile.orElseThrow(() -> new RuntimeException("No profile found")),
                keyTarConfig.getUserName(), keyTarConfig.getPassword(), host, port);
        return profileDao;
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
