package model;

public class ColumnModel {
    private String name;
    private String type;
    private int size;
    private boolean nullable;

    public ColumnModel(String name, String type, int size, boolean nullable) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.nullable = nullable;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getSize() { return size; }
    public boolean isNullable() { return nullable; }

    public String mapToJavaType() {
        switch (type.toUpperCase()) {
            case "INT": case "INTEGER": return "int";
            case "VARCHAR": case "TEXT": return "String";
            case "DATE": return "java.sql.Date";
            case "DATETIME": case "TIMESTAMP": return "java.sql.Timestamp";
            case "DOUBLE": return "double";
            case "FLOAT": return "float";
            case "BOOLEAN": return "boolean";
            default: return "String";
        }
    }
}