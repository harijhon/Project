package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bureau {

    private int bureauId;
    private String nomBureau;
    private int districtId;
    private int nbreOlonaAfakaMifidy;
    private int nbreVatoFotsy;
    private int nbreVatoValide;

    // Constructeurs
    public Bureau() {
    }

    public Bureau(int bureauId, String nomBureau, int districtId, int nbreOlonaAfakaMifidy, int nbreVatoFotsy, int nbreVatoValide) {
        this.bureauId = bureauId;
        this.nomBureau = nomBureau;
        this.districtId = districtId;
        this.nbreOlonaAfakaMifidy = nbreOlonaAfakaMifidy;
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

    // Méthode pour créer un nouveau bureau
    public void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO bureau_vote (nom_bureau, district_id, nbre_olona_afaka_mifidy, nbre_vato_fotsy, nbre_vato_valide) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, nbreOlonaAfakaMifidy);
            preparedStatement.setInt(4, nbreVatoFotsy);
            preparedStatement.setInt(5, nbreVatoValide);
            preparedStatement.executeUpdate();

            // Récupérer l'ID généré automatiquement
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.bureauId = generatedKeys.getInt(1);
                }
            }
        }
    }

    // Méthode pour mettre à jour les informations d'un bureau
    public void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE bureau_vote SET nom_bureau = ?, district_id = ?, nbre_olona_afaka_mifidy = ?, nbre_vato_fotsy = ?, nbre_vato_valide = ? WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomBureau);
            preparedStatement.setInt(2, districtId);
            preparedStatement.setInt(3, nbreOlonaAfakaMifidy);
            preparedStatement.setInt(4, nbreVatoFotsy);
            preparedStatement.setInt(5, nbreVatoValide);
            preparedStatement.setInt(6, bureauId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer un bureau
    public void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bureauId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher les détails d'un bureau
    public void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM bureau_vote WHERE bureau_vote_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, bureauId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.bureauId = resultSet.getInt("bureau_vote_id");
                    this.nomBureau = resultSet.getString("nom_bureau");
                    this.districtId = resultSet.getInt("district_id");
                    this.nbreOlonaAfakaMifidy = resultSet.getInt("nbre_olona_afaka_mifidy");
                    this.nbreVatoFotsy = resultSet.getInt("nbre_vato_fotsy");
                    this.nbreVatoValide = resultSet.getInt("nbre_vato_valide");
                }
            }
        }
    }

    // Méthode pour obtenir la liste de tous les bureaux
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
                bureau.setNbreVatoFotsy(resultSet.getInt("nbre_vato_fotsy"));
                bureau.setNbreVatoValide(resultSet.getInt("nbre_vato_valide"));
                bureaus.add(bureau);
            }
        }
        return bureaus;
    }

    // Autres méthodes spécifiques à la classe Bureau (à compléter)
}
