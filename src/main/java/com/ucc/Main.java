package com.ucc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "root";
        try(Connection myConn = DriverManager.getConnection(url, user, password);
            Statement myStat = myConn.createStatement();
            ResultSet myRes= myStat.executeQuery("Select * from sakila.actor");
            ){

            while (myRes.next()) {
                System.out.println(myRes.getString("first_name")
                +" "+ myRes.getString("last_name"));
            }

        } catch (Exception e) {
            System.out.println("Conexion Fail");
        } 
    }
}   