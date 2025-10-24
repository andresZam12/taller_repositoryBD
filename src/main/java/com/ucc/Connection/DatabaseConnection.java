package com.ucc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL con base de datos (sakila) y parámetros útiles para MySQL 8
    // Cambia "sakila" si tu esquema es otro
    private static final String URL =
        "jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // Usa variables de entorno si están disponibles; si no, usa los defaults
    // Recomendado crear un usuario de app: bd_java / TU_PASSWORD
    private static final String USER =
        System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
    private static final String PASS =
        System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "Azb02042001*";

    private static Connection myConn;

    /**
     * Devuelve una conexión lista para usar.
     * Si no existe o está cerrada, crea una nueva.
     */
    public static Connection getInstanceConnection() throws SQLException {
        if (myConn == null || myConn.isClosed()) {
            // Si tu driver lo requiere explícitamente, descomenta:
            // try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (ClassNotFoundException ignore) {}
            myConn = DriverManager.getConnection(URL, USER, PASS);
        }
        return myConn;
    }

    /** Cierra la conexión compartida si está abierta (opcional). */
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
