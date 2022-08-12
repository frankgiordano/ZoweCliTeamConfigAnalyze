package zowe.keytar;

import com.starxg.keytar.KeytarException;

import java.util.List;

public interface IKeyContainer {

    void processKey() throws KeytarException;

    List<KeyTarConfig> getKeyConfigs() throws Exception;

    String getKeyValue() throws Exception;

}
