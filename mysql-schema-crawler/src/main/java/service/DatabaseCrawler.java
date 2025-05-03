package service;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCrawler {
    private final Connection connection;

    public DatabaseCrawler(Connection connection) {
        this.connection = connection;
    }

    public List<TableModel> extractSchema() throws SQLException {
        List<TableModel> tables = new ArrayList<>();
        DatabaseMetaData meta = connection.getMetaData();

        ResultSet rsTables = meta.getTables(null, null, "%", new String[]{"TABLE"});
        while (rsTables.next()) {
            String tableName = rsTables.getString("TABLE_NAME");
            TableModel table = new TableModel(tableName);

            ResultSet rsColumns = meta.getColumns(null, null, tableName, null);
            while (rsColumns.next()) {
                table.addColumn(new ColumnModel(
                    rsColumns.getString("COLUMN_NAME"),
                    rsColumns.getString("TYPE_NAME"),
                    rsColumns.getInt("COLUMN_SIZE"),
                    rsColumns.getInt("NULLABLE") == DatabaseMetaData.columnNullable
                ));
            }

            ResultSet rsPK = meta.getPrimaryKeys(null, null, tableName);
            while (rsPK.next()) {
                table.addPrimaryKey(rsPK.getString("COLUMN_NAME"));
            }

            ResultSet rsFK = meta.getImportedKeys(null, null, tableName);
            while (rsFK.next()) {
                table.addForeignKey(new ForeignKeyModel(
                    rsFK.getString("FKCOLUMN_NAME"),
                    rsFK.getString("PKTABLE_NAME"),
                    rsFK.getString("PKCOLUMN_NAME")
                ));
            }

            tables.add(table);
        }
        return tables;
    }
}