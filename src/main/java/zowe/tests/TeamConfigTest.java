package zowe.tests;

import zowe.teamconfig.TeamConfig;
import zowe.teamconfig.model.ProfileDao;
import zowe.teamconfig.service.KeyTarService;
import zowe.teamconfig.service.TeamConfigService;

public class TeamConfigTest {

    public static void main(String[] args) throws Exception {
        TeamConfig zowe = new TeamConfig(new KeyTarService(), new TeamConfigService());
        ProfileDao profileDao = zowe.getDefaultProfileByName("zosmf");
        System.out.println(profileDao);
    }

}
