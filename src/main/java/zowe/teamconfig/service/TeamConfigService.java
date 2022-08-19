package zowe.teamconfig.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zowe.mockdata.TeamConfigMockData;
import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.model.*;
import zowe.teamconfig.types.ProfileType;
import zowe.teamconfig.types.SectionType;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TeamConfigService {

    public ConfigContainer getTeamConfig(KeyTarConfig config) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader(config.getLocation()));
        } catch (IOException | ParseException e) {
            obj = parser.parse(TeamConfigMockData.getTeamConfigJsonString());
        }
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);
        return parseJson(jsonObject);
    }

    private ConfigContainer parseJson(JSONObject jsonObj) throws Exception {
        String schema = null;
        List<Profile> profiles = new ArrayList<>();
        Map<String, String> defaults = new HashMap<>();
        String autoStore = null;
        List<Partition> partitions = new ArrayList<>();
        Set<String> jsonSectionKeys = jsonObj.keySet();
        for (String keyVal : jsonSectionKeys) {
            if (SectionType.$SCHEMA.getValue().equals(keyVal)) {
                schema = (String) jsonObj.get(SectionType.$SCHEMA.getValue());
            }
            if (SectionType.PROFILES.getValue().equals(keyVal)) {
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
                    }
                } else {
                    // TODO
                }
            }
            if (SectionType.DEFAULTS.getValue().equals(keyVal)) {
                // TODO
            }
            if (SectionType.AUTOSTORE.getValue().equals(keyVal)) {
                // TODO
            }
        }
        return new ConfigContainer(partitions, schema, profiles, defaults, autoStore);
    }

    private boolean isPartition(Set<String> profileKeyObj) throws Exception {
        Iterator<String> itr = profileKeyObj.iterator();
        if (itr.hasNext()) {
            String keyVal = itr.next();
            return isPartition(keyVal);
        } else {
            throw new Exception("Profile type detail missing in profile section.");
        }
    }

    private boolean isPartition(String type) {
        return !ProfileType.SSH.getValue().equals(type) &&
                !ProfileType.BASE.getValue().equals(type) &&
                !ProfileType.SYSVIEW.getValue().equals(type) &&
                !ProfileType.SYSVIEWFORMAT.getValue().equals(type) &&
                !ProfileType.TSO.getValue().equals(type) &&
                !ProfileType.ZOSMF.getValue().equals(type);
    }


}
