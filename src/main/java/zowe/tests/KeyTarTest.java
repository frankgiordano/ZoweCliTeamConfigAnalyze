package zowe.tests;

import zowe.mockdata.KeyTarMockData;
import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.service.KeyTarService;

public class KeyTarTest {

    public static void main(String[] args) throws Exception {
        KeyTarConfig keyTarConfig;
        final KeyTarService keyTarService = new KeyTarService();
        try {
            keyTarConfig = keyTarService.getKeyTarConfig();
        } catch (Exception e) {
            keyTarConfig = keyTarService.getKeyTarConfig(KeyTarMockData.getSingleJsonString());
        }
        System.out.println(keyTarConfig.toString());
    }

}
