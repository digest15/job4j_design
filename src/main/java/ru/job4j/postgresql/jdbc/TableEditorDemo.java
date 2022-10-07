package ru.job4j.postgresql.jdbc;

import java.io.FileInputStream;
import java.util.Properties;

public class TableEditorDemo {
    private static final String DB_CONNECTION_PROPERTY_PATH = "./data/pg.properties";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DB_CONNECTION_PROPERTY_PATH));

        String tableName = "demo";

        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "one", "varchar(255)");
        tableEditor.renameColumn(tableName, "one", "two");
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.dropColumn(tableName, "two");
        tableEditor.dropTable(tableName);
    }
}
