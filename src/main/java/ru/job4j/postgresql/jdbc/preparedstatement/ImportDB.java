package ru.job4j.postgresql.jdbc.preparedstatement;

import ru.job4j.postgresql.jdbc.statement.TableEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(dump))) {
            users = reader.lines()
                    .map(str -> str.split(";"))
                    .map(array -> new User(array[0], array[1]))
                    .collect(Collectors.toList());
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("connection.driver_class"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("connection.url"),
                cfg.getProperty("connection.username"),
                cfg.getProperty("connection.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("pg.properties")) {
            cfg.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(cfg)) {
            String tableName = "users";
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, "name", "varchar(255)");
            tableEditor.addColumn(tableName, "email", "varchar(255)");
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}
