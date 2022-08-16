package zowe.teamconfig.service;

import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.keytar.KeyTarImpl;

import java.util.ArrayList;
import java.util.List;

public class KeyTarService {

    private final List<String> serviceNames = List.of("Zowe", "Zowe-Plugin");

    public KeyTarConfig getKeyTarConfig(String keyString) throws Exception {
        KeyTarImpl keyTarImpl = new KeyTarImpl("", "secure_config_props", keyString);
        return keyTarImpl.getKeyConfigs().get(0);
    }

    public KeyTarConfig getKeyTarConfig() throws Exception {
        List<KeyTarConfig> keyTarConfigs = new ArrayList<>();
        for (String serviceName : serviceNames) {
            KeyTarImpl keyTarImpl = new KeyTarImpl(serviceName, "secure_config_props");
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
            throw new Exception("No zowe configuration information available");
        }
        return keyTarConfigs.get(0);
    }

}
