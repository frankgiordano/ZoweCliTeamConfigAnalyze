package zowecli;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zowecli.keytar.KeyTarConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TeamConfigTest {

    public static void main(String[] args) throws ParseException {

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
        printJson(jsonObject);
        System.out.println();
        System.out.println(jsonObject);
    }

    public static void printJson(JSONObject jsonObj) {
        for (Object keyObj : jsonObj.keySet()) {
            String key = (String) keyObj;
            Object valObj = jsonObj.get(key);
            if (valObj instanceof JSONObject) {
                // call printJSON on nested object
                printJson((JSONObject) valObj);
            } else {
                // print key-value pair
                System.out.println("key : " + key);
                System.out.println("value : " + valObj.toString());
            }
        }
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
