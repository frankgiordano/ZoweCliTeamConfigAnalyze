package zowecli.keytar;

import java.util.List;

public class KeyTarContainerTest {

    public static void main(String[] args) throws Exception {
        KeyTarContainer keyTarContainer = new KeyTarContainer("Zowe-Plugin", "secure_config_props");
        try {
            keyTarContainer.processKey();
        } catch (Exception e) {
            keyTarContainer = new KeyTarContainer("Zowe", "secure_config_props");
            keyTarContainer.processKey();
        }
        System.out.println(keyTarContainer.getKeyValue());
        List<KeyTarConfig> keyTarConfigs = keyTarContainer.getKeyConfigs();
        keyTarConfigs.forEach(System.out::println);
    }

}
