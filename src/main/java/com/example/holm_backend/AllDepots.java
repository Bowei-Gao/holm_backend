package com.example.holm_backend;

import java.sql.*;
import java.util.ArrayList;

public class AllDepots {
    public AllDepots() {}

    public ArrayList<DepotEntity> getAllDepots() throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        String sql = "SELECT * FROM depots";

        String createDepotsTable = "CREATE TABLE IF NOT EXISTS depots (" +
                "id BIGINT AUTO_INCREMENT NOT NULL, " +
                "names VARCHAR(50) NOT NULL UNIQUE, " +
                "x DOUBLE NOT NULL, " +
                "y DOUBLE NOT NULL, " +
                "capacity INT NOT NULL, " +
                "fixedCost INT NOT NULL, " +
                "PRIMARY KEY(id) ) Engine=INNODB DEFAULT CHARSET=UTF8;";

        ArrayList<DepotEntity> depots = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(createDepotsTable);
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String names = resultSet.getString("names");
                Double x = resultSet.getDouble("x");
                Double y = resultSet.getDouble("y");
                Integer capacities = resultSet.getInt("capacities");
                Integer fixed_costs = resultSet.getInt("fixed_costs");

                depots.add(new DepotEntity(names, x, y, capacities, fixed_costs));
            }

        }
        return depots;
    }
}
