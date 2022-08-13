package zowe.mockdata;

public class KeyTarMockData {

    public static String getMultipleJsonString() {
        //        {
        //            "C:\\Users\\fg892105\\zowe.config.json": {
        //                    "profiles.base.properties.user": "CCSAUTO",
        //                    "profiles.base.properties.password": "CCSAUTO"
        //        },
        //            "C:\\Users\\fg892105\\IdeaProjects\\ZoweCCSSVCSymptomsReport\\zowe.config.json": {
        //                    "profiles.base.properties.user": "fg892105",
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
        //                    "profiles.base.properties.user": "CCSAUTO",
        //                    "profiles.base.properties.password": "CCSAUTO"
        //        }
        return "{\"C:\\\\Users\\\\fg892105\\\\zowe.config.json\":{\"profiles.base.properties.user\":" +
                "\"CCSAUTO\",\"profiles.base.properties.password\":\"fakepw\"}}";
    }

}
