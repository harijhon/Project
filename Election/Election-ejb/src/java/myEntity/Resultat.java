package myEntity;

import ElectionUtils.ResultatUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Resultat {

    public static Object findByBureauID(int idBureau) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int bureauVoteId;
    private int candidatId;
    private int nombreVote;

    // Constructeurs
    public Resultat() {
    }

    public Resultat(int bureauVoteId, int candidatId, int nombreVote)throws Exception {
        ResultatUtils.checkData(nombreVote, candidatId, candidatId);
        this.bureauVoteId = bureauVoteId;
        this.candidatId = candidatId;
        this.nombreVote = nombreVote;
    }

    // Getters and Setters
    public int getBureauVoteId() {
        return bureauVoteId;
    }

    public void setBureauVoteId(int bureauVoteId) {
        this.bureauVoteId = bureauVoteId;
    }

    public int getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(int candidatId) {
        this.candidatId = candidatId;
    }

    public int getNombreVote() {
        return nombreVote;
    }

    public void setNombreVote(int nombreVote) {
        this.nombreVote = nombreVote;
    }

    // CRUD Methods

    public void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO Resultat (bureau_vote_id, candidat_id, nombre_vote) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, bureauVoteId);
            preparedStatement.setInt(2, candidatId);
            preparedStatement.setInt(3, nombreVote);
            preparedStatement.executeUpdate();
        }
    }

    public void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE Resultat SET nombre_vote = ? WHERE bureau_vote_id = ? AND candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, nombreVote);
            preparedStatement.setInt(2, bureauVoteId);
            preparedStatement.setInt(3, candidatId);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM Resultat WHERE bureau_vote_id = ? AND candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bureauVoteId);
            preparedStatement.setInt(2, candidatId);
            preparedStatement.executeUpdate();
        }
    }

    public void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM Resultat WHERE bureau_vote_id = ? AND candidat_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, bureauVoteId);
            preparedStatement.setInt(2, candidatId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.bureauVoteId = resultSet.getInt("bureau_vote_id");
                    this.candidatId = resultSet.getInt("candidat_id");
                    this.nombreVote = resultSet.getInt("nombre_vote");
                }
            }
        }
    }
}

