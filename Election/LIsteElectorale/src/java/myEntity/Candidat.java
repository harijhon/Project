package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Candidat 
{

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

    // Méthode pour créer un nouveau candidat
    public void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO Candidat (nom_candidat) VALUES (?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nomCandidat);
            preparedStatement.executeUpdate();

            // Récupérer l'ID généré automatiquement
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.candidatId = generatedKeys.getInt(1);
                }
            }
        }
    }

    // Méthode pour mettre à jour le nom d'un candidat
    public void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE Candidat SET nom_candidat = ? WHERE candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomCandidat);
            preparedStatement.setInt(2, candidatId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer un candidat
    public void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM Candidat WHERE candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, candidatId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher les détails d'un candidat
    public void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM Candidat WHERE candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, candidatId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.candidatId = resultSet.getInt("candidat_id");
                    this.nomCandidat = resultSet.getString("nom_candidat");
                }
            }
        }
    }

    // Méthode pour obtenir la liste de tous les candidats
    public static List<Candidat> getAllCandidates(Connection connex) throws SQLException {
        List<Candidat> candidates = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM Candidat";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Candidat candidate = new Candidat();
                candidate.setCandidatId(resultSet.getInt("candidat_id"));
                candidate.setNomCandidat(resultSet.getString("nom_candidat"));
                candidates.add(candidate);
            }
        }
        return candidates;
    }

}
