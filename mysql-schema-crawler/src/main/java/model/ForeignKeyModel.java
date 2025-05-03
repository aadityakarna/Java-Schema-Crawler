package model;

public class ForeignKeyModel {
    private String columnName;
    private String referencedTable;
    private String referencedColumn;

    public ForeignKeyModel(String columnName, String referencedTable, String referencedColumn) {
        this.columnName = columnName;
        this.referencedTable = referencedTable;
        this.referencedColumn = referencedColumn;
    }

    public String getColumnName() { return columnName; }
    public String getReferencedTable() { return referencedTable; }
    public String getReferencedColumn() { return referencedColumn; }
}