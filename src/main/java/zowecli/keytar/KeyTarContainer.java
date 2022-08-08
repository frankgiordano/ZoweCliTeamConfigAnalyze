package zowecli.keytar;

import com.starxg.keytar.Keytar;
import com.starxg.keytar.KeytarException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

public class KeyTarContainer implements IKeyContainer {

    private final String serviceName;
    private final String accountName;
    private String keyString;
    private final List<KeyTarConfig> keyTarConfigs = new ArrayList<>();

    public KeyTarContainer(String serviceName, String accountName) {
        this.serviceName = serviceName;
        this.accountName = accountName;
    }

    @Override
    public void processKey() throws KeytarException {
        Keytar instance = Keytar.getInstance();
        String encodedString = instance.getPassword(serviceName, accountName);
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
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(keyString);
        JSONObject jsonObject = (JSONObject) obj;

        Set<Object> keyObj = jsonObject.keySet();
        Iterator<Object> itr = keyObj.iterator();
        while (itr.hasNext()) {
            String locationKeyValue = (String) itr.next();
            JSONObject valObj = (JSONObject) jsonObject.get(locationKeyValue);
            keyTarConfigs.add(new KeyTarConfig(locationKeyValue,
                    (String) valObj.get("profiles.base.properties.user"),
                    (String) valObj.get("profiles.base.properties.password")));
        }

        return keyTarConfigs;
    }

    @Override
    public String getKeyValue() throws Exception {
        if (keyString == null) {
            throw new Exception("keyString is null, perform processKey first");
        }
        if (keyString.isEmpty()) {
            throw new Exception("keyString is empty");
        }
        return keyString;
    }

}
