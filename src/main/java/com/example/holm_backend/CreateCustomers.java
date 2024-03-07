package com.example.holm_backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateCustomers {
    private String[] names = new String[]{};
    private Double[] x = new Double[]{};

    private Double[] y = new Double[]{};

    private Integer[] demand = new Integer[]{};

    public CreateCustomers(String[] names, Double[] x, Double[] y, Integer[] demand) {
        this.names = names;
        this.x = x;
        this.y = y;
        this.demand = demand;
    }

    public int UpdateDatabase() throws Exception {
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        String sql = "INSERT INTO customers (names, x, y, demand) VALUES (?, ?, ?, ?)";

        int sum = 0;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < this.names.length; i++) {
                    ps.setString(1, this.names[i]);
                    ps.setDouble(2, this.x[i]);
                    ps.setDouble(3, this.y[i]);
                    ps.setInt(4, this.demand[i]);
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
