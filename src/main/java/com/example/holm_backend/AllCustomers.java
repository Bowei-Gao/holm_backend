package com.example.holm_backend;

import java.sql.*;
import java.util.ArrayList;

public class AllCustomers {
    public AllCustomers() {}

    public ArrayList<CustomerEntity> getAllCustomers() throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        String sql = "SELECT * FROM customers";

        String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                "id BIGINT AUTO_INCREMENT NOT NULL, " +
                "names VARCHAR(50) NOT NULL UNIQUE, " +
                "x DOUBLE NOT NULL, " +
                "y DOUBLE NOT NULL, " +
                "demand INT NOT NULL, " +
                "PRIMARY KEY(id) ) Engine=INNODB DEFAULT CHARSET=UTF8;";

        ArrayList<CustomerEntity> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(createCustomersTable);
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String names = resultSet.getString("names");
                Double x = resultSet.getDouble("x");
                Double y = resultSet.getDouble("y");
                Integer demand = resultSet.getInt("demand");

                customers.add(new CustomerEntity(names, x, y, demand));
            }

        }
        return customers;
    }
}
