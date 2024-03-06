package com.example.holm_backend;

import javax.xml.transform.dom.DOMSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        String sql = "INSERT INTO depots (name, x, y, capacity, fixedCost) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM depots")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        String names = rs.getString("names");
                        float x = rs.getFloat("x");
                        float y = rs.getFloat("y");
                        int capacity = rs.getInt("capacity");
                        int fixedCost = rs.getInt("fixedCost");
                        System.out.println(id);
                        System.out.println(names + ' ' + x + ' ' + y + ' ' + capacity + ' ' + fixedCost);
                    }
                }
            }
        }

        return 0;
    }
}
