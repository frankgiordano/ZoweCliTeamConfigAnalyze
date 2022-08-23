package zowe.teamconfig.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import zowe.teamconfig.utility.TeamConfigUtils;

import java.util.Map;

public class Profile {

    private final String name;
    private final JSONArray secure;
    private Map<String, String> properties;

    public Profile(String name, JSONObject obj, JSONArray secure) {
        this.name = name;
        this.secure = secure;
        properties = TeamConfigUtils.parseJsonPropsObj(obj);
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
