/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEntity;

import Util.MyCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ismae
 */
public class Omby extends Mere {

//    Attribut
    private String idOmby;
    private String nom;
    private double poids;
    private Localisation localisation;
    private Olona proprioActuel;
//    Getters and Setters

    public String getIdOmby() {
        return idOmby;
    }

    public void setIdOmby(String idOmby) {
        this.idOmby = idOmby;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Olona getProprioActuel() {
        return proprioActuel;
    }

    public void setProprioActuel(Olona proprioActuel) {
        this.proprioActuel = proprioActuel;
    }

    public Omby(String idOmby, String nom, double poids, Localisation localisation, Olona proprioActuel) {
        this.idOmby = idOmby;
        this.nom = nom;
        this.poids = poids;
        this.localisation = localisation;
        this.proprioActuel = proprioActuel;
    }

    public Omby() {
    }

    public Omby(String idOmby, String nom, double poids) {
        this.idOmby = idOmby;
        this.nom = nom;
        this.poids = poids;
    }
    
    
    
    @Override
    void create() throws Exception {
        try {
            Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
            PreparedStatement statement = connection.prepareStatement("INSERT INTO omby (idOmby, nom, poids) VALUES (?, ?, ?)");
            statement.setString(1, idOmby);
            statement.setString(2, nom);
            statement.setDouble(3, poids);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new Exception("Error creating Omby: " + e.getMessage());
        }
    }


    @Override
    void update() throws Exception {
        try {
            Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
            PreparedStatement statement = connection.prepareStatement("UPDATE omby SET nom=?, poids=? WHERE idOmby=?");
            statement.setString(1, nom);
            statement.setDouble(2, poids);
            statement.setString(3, idOmby);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new Exception("Error updating Omby: " + e.getMessage());
        }
    }


    @Override
    void delete() throws Exception {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        PreparedStatement statement = connection.prepareStatement("DELETE FROM omby WHERE idOmby=?");
        statement.setString(1, idOmby);
        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        throw new Exception("Error deleting Omby: " + e.getMessage());
    }
}

    @Override
    void details() throws Exception {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        PreparedStatement statement = connection.prepareStatement("SELECT idOmby, nom, poids FROM omby WHERE idOmby = ?");
        statement.setString(1, idOmby);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            idOmby = resultSet.getString("idOmby");
            nom = resultSet.getString("nom");
            poids = resultSet.getDouble("poids");
            
            // You can print or display the details as needed
            System.out.println("ID: " + idOmby);
            System.out.println("Nom: " + nom);
            System.out.println("Poids: " + poids);
        } else {
            throw new Exception("Omby not found in the database.");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        throw new Exception("Error fetching Omby details: " + e.getMessage());
    }
}

    
    public static void reassignAll(String[] ombyIds, Olona newOlona) {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        
        // Iterate through the array of Omby IDs and update their owner (proprioActuel) to the new Olona
        for (String ombyId : ombyIds) {
            PreparedStatement statement = connection.prepareStatement("UPDATE omby SET proprioActuel = ? WHERE idOmby = ?");
            statement.setString(1, newOlona.getIdOlona()); // Assuming you have a method like getIdOlona() in the Olona class
            statement.setString(2, ombyId);
            statement.executeUpdate();
            statement.close();
        }
    } catch (SQLException e) {
        // Handle any database-related exceptions here
        e.printStackTrace(); // You can replace this with proper error handling
    }
}

    
}
