package model;

import java.util.ArrayList;
import java.util.List;

public class TableModel {
    private String name;
    private List<ColumnModel> columns = new ArrayList<>();
    private List<String> primaryKeys = new ArrayList<>();
    private List<ForeignKeyModel> foreignKeys = new ArrayList<>();

    public TableModel(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<ColumnModel> getColumns() { return columns; }
    public List<String> getPrimaryKeys() { return primaryKeys; }
    public List<ForeignKeyModel> getForeignKeys() { return foreignKeys; }

    public void addColumn(ColumnModel column) {
        columns.add(column);
    }

    public void addPrimaryKey(String pk) {
        primaryKeys.add(pk);
    }

    public void addForeignKey(ForeignKeyModel fk) {
        foreignKeys.add(fk);
    }
}