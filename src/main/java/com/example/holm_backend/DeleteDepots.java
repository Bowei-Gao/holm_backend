package com.example.holm_backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DeleteDepots {
    private String[] names = new String[]{};

    public DeleteDepots(String[] names) {
        this.names = names;
    }

    public int UpdateDatabase() throws Exception {
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        String sql = "DELETE FROM depots WHERE names = ?;";

        String createDepotsTable = "CREATE TABLE IF NOT EXISTS depots (" +
                "id BIGINT AUTO_INCREMENT NOT NULL, " +
                "names VARCHAR(50) NOT NULL UNIQUE, " +
                "x DOUBLE NOT NULL, " +
                "y DOUBLE NOT NULL, " +
                "capacity INT NOT NULL, " +
                "fixedCost INT NOT NULL, " +
                "PRIMARY KEY(id) ) Engine=INNODB DEFAULT CHARSET=UTF8;";

        int sum = 0;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createDepotsTable);
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (String name : this.names) {
                    ps.setString(1, name);
                    ps.addBatch(); // Add to batch
                }

                int[] ns = ps.executeBatch();

                for (int n : ns) {
                    sum += n;
                }
            }
        }

        return sum;
    }
}
