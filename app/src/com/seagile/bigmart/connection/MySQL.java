package com.seagile.bigmart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MySQL {

    private static Connection connection;
    private static final String USER = "root";
    private static final String PASSWORD = "saneth@securedMYSQL";
    private static final String DATABASE_NAME = "bigmart_db";

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ExceptionInInitializerError("Oops! MySQL Connection Faild...!");
        }

    }

    public static void iud(String query) {
        try {
            getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet search(String query) throws SQLException {
        return getConnection().createStatement().executeQuery(query);
    }
}
