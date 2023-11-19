package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vote_bureau {

    private int bureauVoteId;
    private String nomBureau;
    private int districtId;
    private int nbreOlonaAfakaMifidy;
    private int nbreOlonaNifidy;
    private int nbreOlonaVoafidy;
    private int nbreVatoMety;

    // Constructeurs
    public vote_bureau() {
    }

    public vote_bureau(int bureauVoteId, String nomBureau, int districtId, int nbreOlonaAfakaMifidy, int nbreOlonaNifidy, int nbreOlonaVoafidy, int nbreVatoMety) {
        this.bureauVoteId = bureauVoteId;
        this.nomBureau = nomBureau;
        this.districtId = districtId;
        this.nbreOlonaAfakaMifidy = nbreOlonaAfakaMifidy;
        this.nbreOlonaNifidy = nbreOlonaNifidy;
        this.nbreOlonaVoafidy = nbreOlonaVoafidy;
        this.nbreVatoMety = nbreVatoMety;
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

    public int getNbreOlonaAfakaMifidy() {
        return nbreOlonaAfakaMifidy;
    }

    public void setNbreOlonaAfakaMifidy(int nbreOlonaAfakaMifidy) {
        this.nbreOlonaAfakaMifidy = nbreOlonaAfakaMifidy;
    }

    public int getNbreOlonaNifidy() {
        return nbreOlonaNifidy;
    }

    public void setNbreOlonaNifidy(int nbreOlonaNifidy) {
        this.nbreOlonaNifidy = nbreOlonaNifidy;
    }

    public int getNbreOlonaVoafidy() {
        return nbreOlonaVoafidy;
    }

    public void setNbreOlonaVoafidy(int nbreOlonaVoafidy) {
        this.nbreOlonaVoafidy = nbreOlonaVoafidy;
    }

    public int getNbreVatoMety() {
        return nbreVatoMety;
    }

    public void setNbreVatoMety(int nbreVatoMety) {
        this.nbreVatoMety = nbreVatoMety;
    }

    void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO bureau_vote (nom_bureau, district_id, nbre_olona_afaka_mifidy, nbre_olona_nifidy, nbre_olona_voafidy, nbre_vato_mety) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, nbreOlonaAfakaMifidy);
            preparedStatement.setInt(4, nbreOlonaNifidy);
            preparedStatement.setInt(5, nbreOlonaVoafidy);
            preparedStatement.setInt(6, nbreVatoMety);
            preparedStatement.executeUpdate();
        }
    }

    void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE bureau_vote SET nom_bureau = ?, district_id = ?, nbre_olona_afaka_mifidy = ?, nbre_olona_nifidy = ?, nbre_olona_voafidy = ?, nbre_vato_mety = ? WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, nbreOlonaAfakaMifidy);
            preparedStatement.setInt(4, nbreOlonaNifidy);
            preparedStatement.setInt(5, nbreOlonaVoafidy);
            preparedStatement.setInt(6, nbreVatoMety);
            preparedStatement.setInt(7, bureauVoteId);
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
                    this.nbreOlonaAfakaMifidy = resultSet.getInt("nbre_olona_afaka_mifidy");
                    this.nbreOlonaNifidy = resultSet.getInt("nbre_olona_nifidy");
                    this.nbreOlonaVoafidy = resultSet.getInt("nbre_olona_voafidy");
                    this.nbreVatoMety = resultSet.getInt("nbre_vato_mety");
                }
            }
        }
    }
}
