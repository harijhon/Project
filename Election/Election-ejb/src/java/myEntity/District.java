package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class District {

    private int districtId;
    private String nomDistrict;
    private int regionId;

    // Constructeurs
    public District() {
    }

    public District(int districtId, String nomDistrict, int regionId) {
        this.districtId = districtId;
        this.nomDistrict = nomDistrict;
        this.regionId = regionId;
    }

    // Getters and Setters
    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getNomDistrict() {
        return nomDistrict;
    }

    public void setNomDistrict(String nomDistrict) {
        this.nomDistrict = nomDistrict;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    // Méthode pour créer un nouveau district
    void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO District (nom_district, region_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nomDistrict);
            preparedStatement.setInt(2, regionId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour le nom d'un district
    void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE District SET nom_district = ?, region_id = ? WHERE district_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomDistrict);
            preparedStatement.setInt(2, regionId);
            preparedStatement.setInt(3, districtId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer un district
    void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM District WHERE district_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, districtId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher les détails d'un district
    void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM District WHERE district_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, districtId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.districtId = resultSet.getInt("district_id");
                    this.nomDistrict = resultSet.getString("nom_district");
                    this.regionId = resultSet.getInt("region_id");
                }
            }
        }
    }

    // Autres méthodes spécifiques à la classe District (à compléter)
}
