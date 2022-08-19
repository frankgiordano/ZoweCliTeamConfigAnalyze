package zowe.tests;

import zowe.mockdata.KeyTarMockData;
import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.model.ConfigContainer;
import zowe.teamconfig.service.KeyTarService;
import zowe.teamconfig.service.TeamConfigService;

public class ConfigTest {

    public static void main(String[] args) throws Exception {
        KeyTarConfig keyTarConfig;
        final KeyTarService keyTarService = new KeyTarService();
        try {
            keyTarConfig = keyTarService.getKeyTarConfig();
        } catch (Exception e) {
            keyTarConfig = keyTarService.getKeyTarConfig(KeyTarMockData.getSingleJsonString());
        }
        System.out.println(keyTarConfig.toString());
        final TeamConfigService teamConfigService = new TeamConfigService();
        ConfigContainer teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        System.out.println(teamConfig.toString());
    }

}
