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
        for (final String keySectionVal : jsonSectionKeys) {
            if (SectionType.$SCHEMA.getValue().equals(keySectionVal)) {
                schema = (String) jsonObj.get(SectionType.$SCHEMA.getValue());
            } else if (SectionType.PROFILES.getValue().equals(keySectionVal)) {
                final JSONObject jsonProfileObj = (JSONObject) jsonObj.get(SectionType.PROFILES.getValue());
                final Set<String> jsonProfileKeys = jsonProfileObj.keySet();
                for (final String profileKeyVal : jsonProfileKeys) {
                    final JSONObject profileTypeJsonObj = (JSONObject) jsonProfileObj.get(profileKeyVal);
                    Set<String> isEmbeddedKeyProfile = profileTypeJsonObj.keySet();
                    if (isPartition(isEmbeddedKeyProfile)) {
                        final var partitionName = profileKeyVal;
                        System.out.println("Partition found name " + partitionName + " containing: " + profileTypeJsonObj);
                        // TODO
                    } else {
                        Profile profile = new Profile((String) profileTypeJsonObj.get("type"),
                                (JSONObject) profileTypeJsonObj.get("properties"),
                                (JSONArray) profileTypeJsonObj.get("secure"));
                        profiles.add(profile);
                    }
                }
            } else if (SectionType.DEFAULTS.getValue().equals(keySectionVal)) {
                final JSONObject keyValues = (JSONObject) jsonObj.get(SectionType.DEFAULTS.getValue());
                for (final Object defaultKeyVal : keyValues.keySet()) {
                    final String key = (String) defaultKeyVal;
                    final String value = (String) keyValues.get(key);
                    defaults.put(key, value);
                }
            } else if (SectionType.AUTOSTORE.getValue().equals(keySectionVal)) {
                autoStore = (Boolean) jsonObj.get(SectionType.AUTOSTORE.getValue());
            }
        }
        return new ConfigContainer(partitions, schema, profiles, defaults, autoStore);
    }

    private boolean isPartition(Set<String> profileKeyObj) throws Exception {
        final Iterator<String> itr = profileKeyObj.iterator();
        if (itr.hasNext()) {
            String keyVal = itr.next();
            if (SectionType.PROFILES.getValue().equals(keyVal)) {
                return true;
            }
            return false;
        } else {
            throw new Exception("Profile type detail missing in profile section.");
        }
    }

}
