package com.ucc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        //mi base de datos mySQL 
    private static final String USER =
        System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
    private static final String PASS =
        System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "Azb02042001*";

    private static Connection myConn;


    public static Connection getInstanceConnection() throws SQLException {
        if (myConn == null || myConn.isClosed()) {

            myConn = DriverManager.getConnection(URL, USER, PASS);
        }
        return myConn;
    }


    public static void close() {
        try {
            if (myConn != null && !myConn.isClosed()) {
                myConn.close();
            }
        } catch (SQLException ignored) {
        } finally {
            myConn = null;
        }
    }
}
