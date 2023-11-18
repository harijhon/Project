/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ismae
 */
public class MyCon {
    public static Connection getConnection() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/varotra";
        String username = "postgres";
        String password = "root";
        
        Connection connection = null;

        try {
            // Chargez le pilote JDBC
            Class.forName("org.postgresql.Driver");

            // Établissez la connexion à la base de données
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du pilote JDBC : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }

        return connection;
    }
}
