package myEntity;

import ElectionUtils.BureauUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bureau {

    public static int getNombreDeVoixTotal(int valide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int bureauId;
    private String nomBureau;
    private int districtId;
    private int nbreOlonaAfakaMifidy;
    private int nbreOlonaNifidy;
    private int nbreVatoFotsy;
    private int nbreVatoValide;

    
    public Bureau() {
    }

    public Bureau(int bureauId, String nomBureau, int districtId, int nbreOlonaAfakaMifidy, int nbreOlonaNifidy, int nbreVatoFotsy, int nbreVatoValide) throws Exception {
        BureauUtils.checkDataAndSend(bureauId, districtId, nbreOlonaNifidy, nbreVatoFotsy, nbreVatoValide, nomBureau);
        this.bureauId = bureauId;
        this.nomBureau = nomBureau;
        this.districtId = districtId;
        this.nbreOlonaAfakaMifidy = nbreOlonaAfakaMifidy;
        this.nbreOlonaNifidy = nbreOlonaNifidy;
        this.nbreVatoFotsy = nbreVatoFotsy;
        this.nbreVatoValide = nbreVatoValide;
    }

    // Getters and Setters
    public int getBureauId() {
        return bureauId;
    }

    public void setBureauId(int bureauId) {
        this.bureauId = bureauId;
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

    public int getNbreVatoFotsy() {
        return nbreVatoFotsy;
    }

    public void setNbreVatoFotsy(int nbreVatoFotsy) {
        this.nbreVatoFotsy = nbreVatoFotsy;
    }

    public int getNbreVatoValide() {
        return nbreVatoValide;
    }

    public void setNbreVatoValide(int nbreVatoValide) {
        this.nbreVatoValide = nbreVatoValide;
    }

    
    public static void create(Connection connex,int bureauId, String nomBureau, int districtId, int nbreOlonaAfakaMifidy, int nbreOlonaNifidy, int nbreVatoFotsy, int nbreVatoValide) throws SQLException {
        String insertQuery = "INSERT INTO bureau_vote (nom_bureau, district_id, nbre_olona_afaka_mifidy, nbre_olona_nifidy, nbre_vato_fotsy, nbre_vato_valide) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, nbreOlonaAfakaMifidy);
            preparedStatement.setInt(4, nbreOlonaNifidy);
            preparedStatement.setInt(5, nbreVatoFotsy);
            preparedStatement.setInt(6, nbreVatoValide);
            preparedStatement.executeUpdate();
        }
    }

    
    public void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE bureau_vote SET nom_bureau = ?, district_id = ?, nbre_olona_afaka_mifidy = ?, nbre_olona_nifidy = ?, nbre_vato_fotsy = ?, nbre_vato_valide = ? WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, nbreOlonaAfakaMifidy);
            preparedStatement.setInt(4, nbreOlonaNifidy);
            preparedStatement.setInt(5, nbreVatoFotsy);
            preparedStatement.setInt(6, nbreVatoValide);
            preparedStatement.setInt(7, bureauId);
            preparedStatement.executeUpdate();
        }
    }

    
    public void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bureauId);
            preparedStatement.executeUpdate();
        }
    }


    public void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, bureauId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                
                    this.bureauId = resultSet.getInt("bureau_vote_id");
                    this.nomBureau = resultSet.getString("nom_bureau");
                    this.districtId = resultSet.getInt("district_id");
                    this.nbreOlonaAfakaMifidy = resultSet.getInt("nbre_olona_afaka_mifidy");
                    this.nbreOlonaNifidy = resultSet.getInt("nbre_olona_nifidy");
                    this.nbreVatoFotsy = resultSet.getInt("nbre_vato_fotsy");
                    this.nbreVatoValide = resultSet.getInt("nbre_vato_valide");
                }
            }
        }
    }
    public  static Bureau findByID(Connection connex, int id) throws SQLException {
        Bureau b = new Bureau();
        String selectQuery = "SELECT * FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                
                    b.bureauId = resultSet.getInt("bureau_vote_id");
                    b.nomBureau = resultSet.getString("nom_bureau");
                    b.districtId = resultSet.getInt("district_id");
                    b.nbreOlonaAfakaMifidy = resultSet.getInt("nbre_olona_afaka_mifidy");
                    b.nbreOlonaNifidy = resultSet.getInt("nbre_olona_nifidy");
                    b.nbreVatoFotsy = resultSet.getInt("nbre_vato_fotsy");
                    b.nbreVatoValide = resultSet.getInt("nbre_vato_valide");
                }
            }
        }
        return b;
    }

    
    public static List<Bureau> getAllBureaus(Connection connex) throws SQLException {
        List<Bureau> bureaus = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM bureau_vote";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Bureau bureau = new Bureau();
                bureau.setBureauId(resultSet.getInt("bureau_vote_id"));
                bureau.setNomBureau(resultSet.getString("nom_bureau"));
                bureau.setDistrictId(resultSet.getInt("district_id"));
                bureau.setNbreOlonaAfakaMifidy(resultSet.getInt("nbre_olona_afaka_mifidy"));
                bureau.setNbreOlonaNifidy(resultSet.getInt("nbre_olona_nifidy"));
                bureau.setNbreVatoFotsy(resultSet.getInt("nbre_vato_fotsy"));
                bureau.setNbreVatoValide(resultSet.getInt("nbre_vato_valide"));
                bureaus.add(bureau);
            }
        }
        return bureaus;
    }

    
}
