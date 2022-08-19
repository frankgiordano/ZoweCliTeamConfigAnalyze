package zowe.teamconfig.keytar;

import com.starxg.keytar.KeytarException;

import java.util.List;

public interface IKeyTar {

    void processKey() throws KeytarException;

    List<KeyTarConfig> getKeyConfigs() throws Exception;

    String getKeyTarValue() throws Exception;

}
