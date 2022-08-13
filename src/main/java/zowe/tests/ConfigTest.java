package zowe.tests;

import zowe.teamconfig.config.ConfigContainer;
import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.keytar.mockdata.KeyTarMockData;
import zowe.teamconfig.service.KeyTarService;
import zowe.teamconfig.service.TeamConfigService;

public class ConfigTest {

    public static void main(String[] args) throws Exception {
        KeyTarConfig keyTarConfig;
        KeyTarService keyTarService = new KeyTarService();
        try {
            keyTarConfig = keyTarService.getKeyTar();
        } catch (Exception e) {
            keyTarConfig = keyTarService.getKeyTar(KeyTarMockData.getSingleJsonString());
        }
        System.out.println(keyTarConfig.toString());
        TeamConfigService teamConfigService = new TeamConfigService();
        ConfigContainer teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        System.out.println(teamConfig.toString());
    }

}
