package zowe.teamconfig.api;

import zowe.service.KeyTarService;
import zowe.service.TeamConfigService;
import zowe.teamconfig.types.ProfileType;
import zowe.keytar.KeyTarConfig;
import zowe.model.ProfileDao;

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
        return null;
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
