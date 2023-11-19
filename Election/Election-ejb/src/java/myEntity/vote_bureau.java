package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vote_bureau  {

    private int bureauVoteId;
    private String nomBureau;
    private int districtId;

    // Constructeurs
    public vote_bureau() {
    }

    public vote_bureau(int bureauVoteId, String nomBureau, int districtId) {
        this.bureauVoteId = bureauVoteId;
        this.nomBureau = nomBureau;
        this.districtId = districtId;
    }

    // Getters and Setters
    public int getBureauVoteId() {
        return bureauVoteId;
    }

    public void setBureauVoteId(int bureauVoteId) {
        this.bureauVoteId = bureauVoteId;
    }

    public String getNomBureau() {
        return nomBureau;
    }

    public void setNomBureau(String nomBureau) {
        this.nomBureau = nomBureau;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }


    void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO bureau_vote (nom_bureau, district_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.executeUpdate();
        }
    }


    void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE bureau_vote SET nom_bureau = ?, district_id = ? WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, bureauVoteId);
            preparedStatement.executeUpdate();
        }
    }


    void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bureauVoteId);
            preparedStatement.executeUpdate();
        }
    }


    void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, bureauVoteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.bureauVoteId = resultSet.getInt("bureau_vote_id");
                    this.nomBureau = resultSet.getString("nom_bureau");
                    this.districtId = resultSet.getInt("district_id");
                }
            }
        }
    }

    
}

