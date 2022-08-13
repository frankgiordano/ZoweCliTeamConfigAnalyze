package zowe.teamconfig.model;

public class Schema {

    private final String schema;

    public Schema(String schema) {
        this.schema = schema;
    }

    public String getSchema() {
        return schema;
    }

    @Override
    public String toString() {
        return "Schema{" +
                "schema='" + schema + '\'' +
                '}';
    }

}
