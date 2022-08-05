package zowecli;

import com.starxg.keytar.Keytar;
import com.starxg.keytar.KeytarException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

public class ConfigKeyTar {

    public static void main(String[] args) throws KeytarException, ParseException {

        Keytar instance = Keytar.getInstance();

        // the following is for zowe v2 with Global Team Configuration use "zowe" service name
        // String encodedString = instance.getPassword("Zowe", "secure_config_props");

        // from V1 to V2 upgrade use "zowe-Plugin" service name
        String encodedString = instance.getPassword("Zowe-Plugin", "secure_config_props");
        if (encodedString != null) {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            System.out.println();
            System.out.println(encodedString);
            System.out.println();
            String decodedString = new String(decodedBytes);
            processJson(decodedString);
        }
        System.out.println();
        processJson(getMultipleJsonString());
        System.out.println();
        processJson(getSingleJsonString());
        System.out.println();
    }

    public static List<Config> processJson(String jsonStr) throws ParseException {
        System.out.println(jsonStr);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonStr);
        JSONObject jsonObject = (JSONObject) obj;

        List<Config> configList = new ArrayList<>();
        Set<Object> keyObj = jsonObject.keySet();
        Iterator<Object> itr = keyObj.iterator();
        while (itr.hasNext()) {
            String locationKeyValue = (String) itr.next();
            JSONObject valObj = (JSONObject) jsonObject.get(locationKeyValue);
            configList.add(new Config(locationKeyValue, (String) valObj.get("profiles.base.properties.user"),
                    (String) valObj.get("profiles.base.properties.password")));
        }

        configList.forEach(System.out::println);
        return configList;
    }

    public static String getMultipleJsonString() {
        //        {
        //            "C:\\Users\\fg892105\\zowe.config.json": {
        //            "profiles.base.properties.user": "CCSAUTO",
        //                    "profiles.base.properties.password": "CCSAUTO"
        //        },
        //            "C:\\Users\\fg892105\\IdeaProjects\\ZoweCCSSVCSymptomsReport\\zowe.config.json": {
        //            "profiles.base.properties.user": "fg892105",
        //                    "profiles.base.properties.password": "fakepw"
        //        }
        //        }
        return "{\"C:\\\\Users\\\\fg892105\\\\zowe.config.json\":{\"profiles.base.properties.user\":" +
                "\"CCSAUTO\",\"profiles.base.properties.password\":\"fakepw\"}," +
                "\"C:\\\\Users\\\\fg892105\\\\IdeaProjects\\\\ZoweCCSSVCSymptomsReport\\\\zowe.config.json\":" +
                "{\"profiles.base.properties.user\":\"fg892105\",\"profiles.base.properties.password\":\"fakepw\"}}";
    }

    public static String getSingleJsonString() {
        //        {
        //            "C:\\Users\\fg892105\\zowe.config.json": {
        //            "profiles.base.properties.user": "CCSAUTO",
        //                    "profiles.base.properties.password": "CCSAUTO"
        //        }
        return "{\"C:\\\\Users\\\\fg892105\\\\zowe.config.json\":{\"profiles.base.properties.user\":" +
                "\"CCSAUTO\",\"profiles.base.properties.password\":\"fakepw\"}}";
    }

}
