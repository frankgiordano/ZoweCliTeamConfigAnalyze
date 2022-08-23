package zowe.teamconfig.utility;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TeamConfigUtils {

    public static Map<String, String> parseJsonPropsObj(JSONObject obj) {
        // example of props json value to parse properties='{"rejectUnauthorized":false,"host":"mvsxe47.lvn.company.net"}'
        Map<String, String> props = new HashMap<>();
        if (obj == null) {
            return props;
        }
        for (final Object keyValObj : obj.keySet()) {
            final String key = (String) keyValObj;
            String value = null;
            try {
                value = (String) obj.get(key);
            } catch (Exception e) {
                if (e.getMessage().contains("java.lang.Long")) {
                    value = String.valueOf(obj.get(key));
                }
            }
            props.put(key, value);
        }
        return props;
    }

}
