package zowe.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zowe.teamconfig.config.TeamConfig;
import zowe.teamconfig.mockdata.TeamConfigMockData;
import zowe.teamconfig.sections.*;
import zowe.teamconfig.types.ProfileType;
import zowe.teamconfig.types.SectionType;
import zowe.keytar.KeyTarConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TeamConfigService {

    public TeamConfig getTeamConfig(KeyTarConfig config) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader(config.getLocation()));
        } catch (IOException | ParseException e) {
            obj = parser.parse(TeamConfigMockData.getTeamConfigJsonString());
        }
        JSONObject jsonObject = (JSONObject) obj;
        return parseJson(jsonObject);
    }

    private TeamConfig parseJson(JSONObject jsonObj) throws Exception {
        Schema schema = null;
        List<Profile> profiles = new ArrayList<>();
        Defaults defaults = null;
        AutoStore autoStore = null;
        List<Partition> partitions = new ArrayList<>();
        Set<String> jsonSectionKeys = jsonObj.keySet();
        for (String keyVal : jsonSectionKeys) {
            if (SectionType.$SCHEMA.getValue().equals(keyVal)) {
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
        return new TeamConfig(partitions, schema, profiles, defaults, autoStore);
    }

    private boolean isPartition(Set<String> profileKeyObj) throws Exception {
        Iterator<String> itr = profileKeyObj.iterator();
        if (itr.hasNext()) {
            String keyVal = itr.next();
            isPartition(keyVal);
        } else {
            throw new Exception("Profile type detail missing in profile section.");
        }
        return false;
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
