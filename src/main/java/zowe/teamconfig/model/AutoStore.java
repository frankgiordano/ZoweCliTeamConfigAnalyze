package zowe.teamconfig.model;

public class AutoStore {

    private final String autoStore;

    public AutoStore(String autoStore) {
        this.autoStore = autoStore;
    }

    public String getAutoStore() {
        return autoStore;
    }

    @Override
    public String toString() {
        return "AutoStore{" +
                "autoStore='" + autoStore + '\'' +
                '}';
    }

}
