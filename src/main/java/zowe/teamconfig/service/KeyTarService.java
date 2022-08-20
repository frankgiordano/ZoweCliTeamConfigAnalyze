package zowe.teamconfig.service;

import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.keytar.KeyTarImpl;

import java.util.ArrayList;
import java.util.List;

public class KeyTarService {

    private final List<String> serviceNames = List.of("Zowe", "Zowe-Plugin");
    private final String ACCOUNT_NAME = "secure_config_props";
    private final String ERROR_MSG = "No zowe configuration information available";

    public KeyTarConfig getKeyTarConfig(String keyString) throws Exception {
        final KeyTarImpl keyTarImpl = new KeyTarImpl("", ACCOUNT_NAME, keyString);
        return keyTarImpl.getKeyConfigs().get(0);
    }

    public KeyTarConfig getKeyTarConfig() throws Exception {
        List<KeyTarConfig> keyTarConfigs = new ArrayList<>();
        for (final String serviceName : serviceNames) {
            final KeyTarImpl keyTarImpl = new KeyTarImpl(serviceName, ACCOUNT_NAME);
            try {
                keyTarImpl.processKey();
            } catch (Exception e) {
                continue;
            }
            System.out.println(keyTarImpl.getKeyTarValue());
            keyTarConfigs = keyTarImpl.getKeyConfigs();
            break;
        }
        if (keyTarConfigs.isEmpty()) {
            throw new Exception(ERROR_MSG);
        }
        return keyTarConfigs.get(0);
    }

}
