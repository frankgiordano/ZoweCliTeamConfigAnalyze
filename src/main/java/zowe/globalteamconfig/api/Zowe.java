package zowe.globalteamconfig.api;

import zowe.globalteamconfig.config.TeamConfig;
import zowe.globalteamconfig.types.ProfileType;
import zowe.model.ProfileDao;

public class Zowe {

    private TeamConfig teamConfig;

    public Zowe(){

    }

    public ProfileDao getDefaultProfileByName(String name) {
        // TODO
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
