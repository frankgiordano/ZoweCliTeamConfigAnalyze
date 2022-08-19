package zowe.teamconfig.service;

import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.keytar.KeyTarContainer;

import java.util.ArrayList;
import java.util.List;

public class KeyTarService {

    private final List<String> serviceNames = List.of("Zowe", "Zowe-Plugin");
    private final String ACCOUNT_NAME = "secure_config_props";
    private final String ERROR_MSG = "No zowe configuration information available";

    public KeyTarConfig getKeyTar(String keyString) throws Exception {
        KeyTarContainer keyTarContainer = new KeyTarContainer("", ACCOUNT_NAME, keyString);
        return keyTarContainer.getKeyConfigs().get(0);
    }

    public KeyTarConfig getKeyTar() throws Exception {
        List<KeyTarConfig> keyTarConfigs = new ArrayList<>();
        for (String serviceName : serviceNames) {
            KeyTarContainer keyTarContainer = new KeyTarContainer(serviceName, "ACCOUNT_NAME");
            try {
                keyTarContainer.processKey();
            } catch (Exception e) {
                continue;
            }
            System.out.println(keyTarContainer.getKeyValue());
            keyTarConfigs = keyTarContainer.getKeyConfigs();
            break;
        }
        if (keyTarConfigs.isEmpty()) {
            throw new Exception(ERROR_MSG);
        }
        return keyTarConfigs.get(0);
    }

}
