package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidat {

    private int candidatId;
    private String nomCandidat;

    // Constructeurs
    public Candidat() {
    }

    public Candidat(int candidatId, String nomCandidat) {
        this.candidatId = candidatId;
        this.nomCandidat = nomCandidat;
    }

    // Getters and Setters
    public int getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(int candidatId) {
        this.candidatId = candidatId;
    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    // CRUD Methods

    public void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO Candidat (nom_candidat) VALUES (?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nomCandidat);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.candidatId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("La création du candidat a échoué, aucun ID généré.");
                }
            }
        }
    }

    public void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE Candidat SET nom_candidat = ? WHERE candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomCandidat);
            preparedStatement.setInt(2, candidatId);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM Candidat WHERE candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, candidatId);
            preparedStatement.executeUpdate();
        }
    }

    public void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM Candidat WHERE candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, candidatId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.candidatId = resultSet.getInt("candidat_id");
                    this.nomCandidat = resultSet.getString("nom_candidat");
                }
            }
        }
    }
}
