package zowe.teamconfig.sections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Map;

public class Profile {

    private final String name;
    private final JSONObject jsonPropsObj;
    private final JSONArray secure;
    private Map<String, String> properties;

    public Profile(String name, JSONObject jsonPropsObj, JSONArray secure) {
        this.name = name;
        this.jsonPropsObj = jsonPropsObj;
        this.secure = secure;
        this.parseJsonPropsObj(this.jsonPropsObj);
    }

    private void parseJsonPropsObj(JSONObject jsonPropsObj) {
        // example of props json value to parse
        // properties='{"rejectUnauthorized":false,"host":"mvsxe47.lvn.company.net"}'
        // parse and populate Map properties variable with key value pairs from json
        // TODO
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
                ", jsonPropsObj=" + jsonPropsObj +
                ", secure=" + secure +
                ", properties=" + properties +
                '}';
    }

}
