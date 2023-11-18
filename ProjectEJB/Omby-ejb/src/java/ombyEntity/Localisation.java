/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEntity;

/**
 *
 * @author ismae
 */
import Util.MyCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Localisation extends Mere {
    private String idLoc;
    private String nom;

    public Localisation(String idLoc, String nom) {
        this.idLoc = idLoc;
        this.nom = nom;
    }

    public String getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(String idLoc) {
        this.idLoc = idLoc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode pour créer une nouvelle localisation
    public void create() throws SQLException {
        Connection connection = MyCon.getConnection();
        String insertQuery = "INSERT INTO localisation (idLoc, nom) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, this.idLoc);
            preparedStatement.setString(2, this.nom);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour une localisation
    public void update() throws SQLException {
        Connection connection = MyCon.getConnection();
        String updateQuery = "UPDATE localisation SET nom = ? WHERE idLoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, this.nom);
            preparedStatement.setString(2, this.idLoc);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer une localisation
    public void delete() throws SQLException {
        Connection connection = MyCon.getConnection();
        String deleteQuery = "DELETE FROM localisation WHERE idLoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, this.idLoc);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour récupérer une localisation par son ID
    public static Localisation getById(String idLoc) throws SQLException {
        Connection connection = MyCon.getConnection();
        String selectQuery = "SELECT * FROM localisation WHERE idLoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, idLoc);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Localisation(resultSet.getString("idLoc"), resultSet.getString("nom"));
                }
            }
        }
        return null;
    }

    // Méthode pour récupérer toutes les localisations
    public static List<Localisation> getAll() throws SQLException {
    Connection connection = MyCon.getConnection();
    List<Localisation> localisations = new ArrayList<>();
    String selectQuery = "SELECT * FROM localisation";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            localisations.add(new Localisation(resultSet.getString("idLoc"), resultSet.getString("nom")));
        }
    }
    return localisations;
}



    // Méthode pour afficher les détails de la localisation
    public void details() {
    try {
        Connection connection = MyCon.getConnection();
        String selectQuery = "SELECT * FROM localisation WHERE idLoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, this.idLoc);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("ID de la localisation : " + resultSet.getString("idLoc"));
                    System.out.println("Nom de la localisation : " + resultSet.getString("nom"));
                    // Ajoutez d'autres colonnes et détails si nécessaire
                }
            }
        }
    } catch (SQLException e) {
        // Gérez les exceptions appropriées ici
        e.printStackTrace();
    }
}

}

    

