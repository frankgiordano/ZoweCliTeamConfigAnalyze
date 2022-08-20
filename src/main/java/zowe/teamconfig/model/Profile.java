package zowe.teamconfig.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private final String name;
    private final JSONObject jsonPropsObj;
    private final JSONArray secure;
    private Map<String, String> properties = new HashMap<>();

    public Profile(String name, JSONObject jsonPropsObj, JSONArray secure) {
        this.name = name;
        this.jsonPropsObj = jsonPropsObj;
        this.secure = secure;
        this.parseJsonPropsObj(this.jsonPropsObj);
    }

    private void parseJsonPropsObj(JSONObject jsonPropsObj) {
        if (jsonPropsObj == null) {
            return;
        }
        // example of props json value to parse properties='{"rejectUnauthorized":false,"host":"mvsxe47.lvn.company.net"}'
        for (Object keyValObj : jsonPropsObj.keySet()) {
            String key = (String) keyValObj;
            String value = null;
            try {
                value = (String) jsonPropsObj.get(key);
            } catch (Exception e) {
                if (e.getMessage().contains("java.lang.Long")) {
                    value = String.valueOf(jsonPropsObj.get(key));
                }
            }
            properties.put(key, value);
        }
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public JSONArray getSecure() {
        return secure;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", secure=" + secure +
                ", properties=" + properties +
                '}';
    }

}
