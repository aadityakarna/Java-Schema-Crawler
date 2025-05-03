package service;

import model.ColumnModel;
import model.TableModel;

public class ModelGenerator {
    public static String generateModelCode(TableModel table) {
        StringBuilder sb = new StringBuilder("public class " + capitalize(table.getName()) + " {\n");

        for (ColumnModel column : table.getColumns()) {
            sb.append("    private ")
              .append(column.mapToJavaType())
              .append(" ")
              .append(column.getName())
              .append(";\n");
        }

        sb.append("}\n");
        return sb.toString();
    }

    private static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}