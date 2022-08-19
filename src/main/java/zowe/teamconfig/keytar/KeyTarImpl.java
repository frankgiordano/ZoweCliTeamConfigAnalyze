package zowe.teamconfig.keytar;

import com.starxg.keytar.Keytar;
import com.starxg.keytar.KeytarException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

public class KeyTarImpl implements IKeyTar {

    private final String serviceName;
    private final String accountName;
    private String keyString;
    private final List<KeyTarConfig> keyTarConfigs = new ArrayList<>();

    public KeyTarImpl(String serviceName, String accountName) {
        this.serviceName = serviceName;
        this.accountName = accountName;
    }

    public KeyTarImpl(String serviceName, String accountName, String keyString) {
        this.serviceName = serviceName;
        this.accountName = accountName;
        this.keyString = keyString;
    }

    @Override
    public void processKey() throws KeytarException {
        Keytar instance = Keytar.getInstance();
        String encodedString = instance.getPassword(serviceName, accountName);
        System.out.println(encodedString);
        if (encodedString == null) {
            throw new NullPointerException("Unknown service name or account name");
        }
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        this.keyString = new String(decodedBytes);
    }

    @Override
    public List<KeyTarConfig> getKeyConfigs() throws Exception {
        if (!keyTarConfigs.isEmpty()) {
            return keyTarConfigs;
        }
        if (keyString == null) {
            throw new Exception("keyString is null, perform processKey first");
        }
        if (keyString.isEmpty()) {
            throw new Exception("keyString is empty");
        }
        return parseJson();
    }

    private List<KeyTarConfig> parseJson() throws ParseException {
        JSONObject jsonKeyTar = (JSONObject) new JSONParser().parse(keyString);
        Set<String> keyTarKeys = jsonKeyTar.keySet();
        for (String keyVal : keyTarKeys) {
            JSONObject jsonVal = (JSONObject) jsonKeyTar.get(keyVal);
            keyTarConfigs.add(new KeyTarConfig(keyVal,
                    (String) jsonVal.get("profiles.base.properties.user"),
                    (String) jsonVal.get("profiles.base.properties.password")));
        }

        return keyTarConfigs;
    }

    @Override
    public String getKeyTarValue() throws Exception {
        if (keyString == null) {
            throw new Exception("keyString is null, perform processKey first");
        }
        if (keyString.isEmpty()) {
            throw new Exception("keyString is empty");
        }
        return keyString;
    }

}
