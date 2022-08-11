package zowecli;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zowecli.globalteamconfig.config.ZoweTeamConfig;
import zowecli.globalteamconfig.sections.*;
import zowecli.globalteamconfig.types.ProfileType;
import zowecli.keytar.KeyTarConfig;
import zowecli.globalteamconfig.types.SectionType;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TeamConfigTest {

    public static void main(String[] args) throws Exception {
        List<KeyTarConfig> configList = KeyTarTest.processJson(KeyTarTest.getSingleJsonString());
        KeyTarConfig config = configList.get(0);

        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader(config.getLocation()));
        } catch (IOException | ParseException e) {
            obj = parser.parse(getTeamConfigJsonString());
        }
        JSONObject jsonObject = (JSONObject) obj;
        ZoweTeamConfig result = parseJson(jsonObject);
        System.out.println();
        System.out.println(jsonObject);
        System.out.println(result);
    }

    public static ZoweTeamConfig parseJson(JSONObject jsonObj) throws Exception {
        Schema schema = null;
        List<Profile> profiles = new ArrayList<>();
        Defaults defaults = null;
        AutoStore autoStore = null;
        List<Partition> partitions = null;
        Set<String> jsonSectionKeys = jsonObj.keySet();
        for (String keyVal : jsonSectionKeys) {
            if (SectionType.$SCHEMA.getValue().equals(keyVal)) {
                System.out.println("INSIDE " + SectionType.$SCHEMA);
                schema = new Schema((String) jsonObj.get(SectionType.$SCHEMA.getValue()));
            }
            if (SectionType.PROFILES.getValue().equals(keyVal)) {
                System.out.println("INSIDE " + SectionType.PROFILES);
                // At this point, the JSON will consist of a bunch of profile type sections.
                // The first section may not be of a profile type. Let's check the first profile
                // section and determine if it contains a profile type value, if not then it is a
                // partition section, and we need to parse each partition and its profiles.
                JSONObject jsonProfileObj = (JSONObject) jsonObj.get(SectionType.PROFILES.getValue());
                Set<String> jsonProfileKeys = jsonProfileObj.keySet();
                boolean isPartition = isPartition(jsonProfileKeys);
                if (!isPartition) {
                    for (String profileKeyVal : jsonProfileKeys) {
                        JSONObject profileTypeJsonObj = (JSONObject) jsonProfileObj.get(profileKeyVal);
                        Profile profile = new Profile((String) profileTypeJsonObj.get("type"),
                                (JSONObject) profileTypeJsonObj.get("properties"),
                                (JSONArray) profileTypeJsonObj.get("secure"));
                        profiles.add(profile);
                        System.out.println(profile);
                    }
                } else {
                    // TODO
                }
            }
            if (SectionType.DEFAULTS.getValue().equals(keyVal)) {
                System.out.println("INSIDE " + SectionType.DEFAULTS);
                // TODO
            }
            if (SectionType.AUTOSTORE.getValue().equals(keyVal)) {
                System.out.println("INSIDE " + SectionType.AUTOSTORE);
                // TODO
            }
        }
        return new ZoweTeamConfig(partitions, schema, profiles, defaults, autoStore);
    }

    private static boolean isPartition(Set<String> profileKeyObj) throws Exception {
        Iterator<String> itr = profileKeyObj.iterator();
        if (itr.hasNext()) {
            String keyVal = itr.next();
            System.out.println("isPartition Keyval = " + keyVal);
            isPartition(keyVal);
        } else {
            throw new Exception("Profile type detail missing in profile section.");
        }
        return false;
    }

    private static boolean isPartition(String type) {
        return !ProfileType.SSH.getValue().equals(type) &&
                !ProfileType.BASE.getValue().equals(type) &&
                !ProfileType.SYSVIEW.getValue().equals(type) &&
                !ProfileType.SYSVIEWFORMAT.getValue().equals(type) &&
                !ProfileType.TSO.getValue().equals(type) &&
                !ProfileType.ZOSMF.getValue().equals(type);
    }

    public static String getTeamConfigJsonString() {
        //        {
        //            "$schema": "./zowe.schema.json",
        //            "profiles": {
        //                "zosmf": {
        //                    "type": "zosmf",
        //                    "properties": {
        //                        "port": 1443
        //                    },
        //                    "secure": []
        //                },
        //                "tso": {
        //                    "type": "tso",
        //                    "properties": {
        //                        "account": "",
        //                        "codePage": "1047",
        //                        "logonProcedure": "IZUFPROC"
        //                    },
        //                    "secure": []
        //                },
        //                "ssh": {
        //                    "type": "ssh",
        //                    "properties": {
        //                        "port": 22
        //                    },
        //                    "secure": []
        //                },
        //                "sysview": {
        //                    "type": "sysview",
        //                    "properties": {},
        //                    "secure": []
        //                },
        //                "sysview-format": {
        //                    "type": "sysview-format",
        //                    "properties": {},
        //                    "secure": []
        //                },
        //                "base": {
        //                    "type": "base",
        //                    "properties": {
        //                        "host": "mvsxe47.lvn.broadcom.net",
        //                        "rejectUnauthorized": false
        //                    },
        //                    "secure": [
        //                        "user",
        //                        "password"
        //                    ]
        //                }
        //            },
        //            "defaults": {
        //                "zosmf": "zosmf",
        //                "tso": "tso",
        //                "ssh": "ssh",
        //                "sysview": "sysview",
        //                "sysview-format": "sysview-format",
        //                "base": "base"
        //            },
        //            "autoStore": true
        //        }
        return "{\"$schema\":\".\\/zowe.schema.json\",\"defaults\":{\"sysview\":\"sysview\",\"tso\":\"tso\"," +
                "\"sysview-format\":\"sysview-format\",\"ssh\":\"ssh\",\"zosmf\":\"zosmf\",\"base\":\"base\"}," +
                "\"profiles\":{\"sysview\":{\"type\":\"sysview\",\"secure\":[],\"properties\":{}},\"tso\":" +
                "{\"type\":\"tso\",\"secure\":[],\"properties\":{\"codePage\":\"1047\",\"logonProcedure\":\"IZUFPROC\"," +
                "\"account\":\"\"}},\"sysview-format\":{\"type\":\"sysview-format\",\"secure\":[],\"properties\":{}},\"ssh\":" +
                "{\"type\":\"ssh\",\"secure\":[],\"properties\":{\"port\":22}},\"zosmf\":{\"type\":\"zosmf\",\"secure\":[]," +
                "\"properties\":{\"port\":1443}},\"base\":{\"type\":\"base\",\"secure\":[\"user\",\"password\"]," +
                "\"properties\":{\"rejectUnauthorized\":false,\"host\":\"mvsxe47.lvn.company.net\"}}},\"autoStore\":true}";

    }

}
