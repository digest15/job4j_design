package ru.job4j.postgresql.jdbc.statement;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    private static final String CONNECTION_URL = "connection.url";
    private static final String CONNECTION_USERNAME = "connection.username";
    private static final String CONNECTION_PASSWORD = "connection.password";

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        String url = properties.getProperty(CONNECTION_URL);
        String login = properties.getProperty(CONNECTION_USERNAME);
        String password = properties.getProperty(CONNECTION_PASSWORD);

        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        execute(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table if exists %s add if not exists %s %s;",
                tableName,
                columnName,
                type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table if exists %s drop column if exists %s;",
                tableName,
                columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table if exists %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        execute(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void execute(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
