package zowe.teamconfig.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zowe.mockdata.TeamConfigMockData;
import zowe.teamconfig.keytar.KeyTarConfig;
import zowe.teamconfig.model.ConfigContainer;
import zowe.teamconfig.model.Partition;
import zowe.teamconfig.model.Profile;
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
        final JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);
        return parseJson(jsonObject);
    }

    private ConfigContainer parseJson(JSONObject jsonObj) throws Exception {
        String schema = null;
        final List<Profile> profiles = new ArrayList<>();
        final Map<String, String> defaults = new HashMap<>();
        Boolean autoStore = null;
        final List<Partition> partitions = new ArrayList<>();
        final Set<String> jsonSectionKeys = jsonObj.keySet();
        for (String keyVal : jsonSectionKeys) {
            if (SectionType.$SCHEMA.getValue().equals(keyVal)) {
                schema = (String) jsonObj.get(SectionType.$SCHEMA.getValue());
            } else if (SectionType.PROFILES.getValue().equals(keyVal)) {
                // At this point, the JSON will consist of a bunch of profile type sections.
                // The first section may not be of a profile type. Let's check the first profile
                // section and determine if it contains a profile type value, if not then it is a
                // partition section, and we need to parse each partition and its profiles.
                final JSONObject jsonProfileObj = (JSONObject) jsonObj.get(SectionType.PROFILES.getValue());
                final Set<String> jsonProfileKeys = jsonProfileObj.keySet();
                final boolean isPartition = isPartition(jsonProfileKeys);
                if (!isPartition) {
                    for (String profileKeyVal : jsonProfileKeys) {
                        final JSONObject profileTypeJsonObj = (JSONObject) jsonProfileObj.get(profileKeyVal);
                        Profile profile = new Profile((String) profileTypeJsonObj.get("type"),
                                (JSONObject) profileTypeJsonObj.get("properties"),
                                (JSONArray) profileTypeJsonObj.get("secure"));
                        profiles.add(profile);
                    }
                } else {
                    // TODO
                }
            } else if (SectionType.DEFAULTS.getValue().equals(keyVal)) {
                final JSONObject keyValues = (JSONObject) jsonObj.get(SectionType.DEFAULTS.getValue());
                for (Object defaultKeyVal : keyValues.keySet()) {
                    final String key = (String) defaultKeyVal;
                    final String value = (String) keyValues.get(key);
                    defaults.put(key, value);
                }
            } else if (SectionType.AUTOSTORE.getValue().equals(keyVal)) {
                autoStore = (Boolean) jsonObj.get(SectionType.AUTOSTORE.getValue());
            }
        }
        return new ConfigContainer(partitions, schema, profiles, defaults, autoStore);
    }

    private boolean isPartition(Set<String> profileKeyObj) throws Exception {
        final Iterator<String> itr = profileKeyObj.iterator();
        if (itr.hasNext()) {
            String keyVal = itr.next();
//            return isPartition(keyVal);
            return false;
        } else {
            throw new Exception("Profile type detail missing in profile section.");
        }
    }

}
