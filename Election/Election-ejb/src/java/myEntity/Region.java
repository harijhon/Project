package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Region {

    private String idRegion;
    private String nomRegion;

    // Constructeur par défaut
    public Region() {
    }

    // Constructeur avec tous les attributs
    public Region(String idRegion, String nomRegion) {
        this.idRegion = idRegion;
        this.nomRegion = nomRegion;
    }

    // Getters and Setters
    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    // Méthode pour créer une nouvelle région
    void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO Region (id_region, nom_region) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, idRegion);
            preparedStatement.setString(2, nomRegion);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour le nom d'une région
    void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE Region SET nom_region = ? WHERE id_region = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomRegion);
            preparedStatement.setString(2, idRegion);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer une région
    void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM Region WHERE id_region = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, idRegion);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher les détails d'une région
    void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM Region WHERE id_region = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, idRegion);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.idRegion = resultSet.getString("id_region");
                    this.nomRegion = resultSet.getString("nom_region");
                }
            }
        }
    }


}
