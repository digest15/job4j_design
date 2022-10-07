package ru.job4j.postgresql.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private static final String DB_CONNECTION_PROPERTY_PATH = "./data/pg.properties";
    private static final String CONNECTION_URL = "connection.url";
    private static final String CONNECTION_USERNAME = "connection.username";
    private static final String CONNECTION_PASSWORD = "connection.password";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config(DB_CONNECTION_PROPERTY_PATH);
        config.load();
        String url = config.value(CONNECTION_URL);
        String login = config.value(CONNECTION_USERNAME);
        String password = config.value(CONNECTION_PASSWORD);

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
