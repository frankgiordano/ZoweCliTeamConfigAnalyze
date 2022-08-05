package zowecli.keytar;

import com.starxg.keytar.KeytarException;

import java.util.List;

public interface IKeyContainer {

    public void processKey() throws KeytarException;

    public List<KeyTarConfig> getKeyConfigs() throws Exception;

    public String getKeyValue() throws Exception;

}
