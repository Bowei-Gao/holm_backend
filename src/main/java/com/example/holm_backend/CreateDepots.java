package com.example.holm_backend;

import javax.xml.transform.dom.DOMSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CreateDepots {
    private String[] names = new String[]{};
    private Double[] x = new Double[]{};

    private Double[] y = new Double[]{};

    private Integer[] capacities = new Integer[]{};

    private Integer[] fixed_costs = new Integer[]{};

    public CreateDepots(String[] names, Double[] x, Double[] y, Integer[] capacities, Integer[] fixed_costs) {
        this.names = names;
        this.x = x;
        this.y = y;
        this.capacities = capacities;
        this.fixed_costs = fixed_costs;
    }

    public int UpdateDatabase() throws Exception {
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        String sql = "INSERT INTO depots (names, x, y, capacity, fixedCost) VALUES (?, ?, ?, ?, ?)";

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
                for (int i = 0; i < this.names.length; i++) {
                    ps.setString(1, this.names[i]);
                    ps.setDouble(2, this.x[i]);
                    ps.setDouble(3, this.y[i]);
                    ps.setInt(4, this.capacities[i]);
                    ps.setInt(5, this.fixed_costs[i]);
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
