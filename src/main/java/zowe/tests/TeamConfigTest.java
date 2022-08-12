package zowe.tests;

import zowe.teamconfig.config.TeamConfig;
import zowe.keytar.KeyTarConfig;
import zowe.keytar.mockdata.KeyTarMockData;
import zowe.service.KeyTarService;
import zowe.service.TeamConfigService;

public class TeamConfigTest {

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
        TeamConfig teamConfig = teamConfigService.getTeamConfig(keyTarConfig);
        System.out.println(teamConfig.toString());
    }

}
