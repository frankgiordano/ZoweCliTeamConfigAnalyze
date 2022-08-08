package zowecli.globalteamconfig.sections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Profile {

    private final String name;
    private final JSONObject properties;
    private final JSONArray secure;
    private String propertiesJsonValue;

    public Profile(String name, JSONObject properties, JSONArray secure) {
        this.name = name;
        this.properties = properties;
        this.secure = secure;
    }

    public String getName() {
        return name;
    }

    public JSONObject getProperties() {
        return properties;
    }

    public JSONArray getSecure() {
        return secure;
    }

    public String getPropertiesJsonValue() {
        return propertiesJsonValue;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", properties='" + properties + '\'' +
                ", secure='" + secure + '\'' +
                ", propertiesJsonValue='" + propertiesJsonValue + '\'' +
                '}';
    }

}
