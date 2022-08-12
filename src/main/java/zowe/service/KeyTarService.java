package zowe.service;

import zowe.keytar.KeyTarConfig;
import zowe.keytar.KeyTarContainer;

import java.util.ArrayList;
import java.util.List;

public class KeyTarService {

    private List<String> serviceNames = List.of("Zowe", "Zowe-Plugin");

    public KeyTarConfig getKeyTar(String keyString) throws Exception {
        KeyTarContainer keyTarContainer = new KeyTarContainer("", "secure_config_props", keyString);
        return keyTarContainer.getKeyConfigs().get(0);
    }

    public KeyTarConfig getKeyTar() throws Exception {
        List<KeyTarConfig> keyTarConfigs = new ArrayList<>();
        for (String serviceName : serviceNames) {
            KeyTarContainer keyTarContainer = new KeyTarContainer(serviceName, "secure_config_props");
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
            throw new Exception("No zowe configuration information available");
        }
        return keyTarConfigs.get(0);
    }

}
