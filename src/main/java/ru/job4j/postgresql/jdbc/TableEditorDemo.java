package ru.job4j.postgresql.jdbc;

import java.io.InputStream;
import java.util.Properties;

public class TableEditorDemo {
    private static final String DB_CONNECTION_PROPERTY_PATH = "pg.properties";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditorDemo.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PROPERTY_PATH)) {
            properties.load(in);
        }

        String tableName = "demo";
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, "one", "varchar(255)");
            tableEditor.renameColumn(tableName, "one", "two");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropColumn(tableName, "two");
            tableEditor.dropTable(tableName);
        }
    }
}
