package myEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListeElectorale {

    private int listeId;
    private String nomListe;
    private int bureauId;
    private String cin;

    // Constructeurs
    public ListeElectorale() {
    }

    public ListeElectorale(int listeId, String nomListe, int bureauId, String cin) {
        this.listeId = listeId;
        this.nomListe = nomListe;
        this.bureauId = bureauId;
        this.cin = cin;
    }

    // Getters and Setters
    public int getListeId() {
        return listeId;
    }

    public void setListeId(int listeId) {
        this.listeId = listeId;
    }

    public String getNomListe() {
        return nomListe;
    }

    public void setNomListe(String nomListe) {
        this.nomListe = nomListe;
    }

    public int getBureauId() {
        return bureauId;
    }

    public void setBureauId(int bureauId) {
        this.bureauId = bureauId;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    // Méthode pour créer une nouvelle liste électorale
    public void create(Connection connex) throws SQLException {
        String insertQuery = "INSERT INTO Liste_electorale (nom_liste, bureau_id, CIN) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connex.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nomListe);
            preparedStatement.setInt(2, bureauId);
            preparedStatement.setString(3, cin);
            preparedStatement.executeUpdate();

            // Récupérer l'ID généré automatiquement
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.listeId = generatedKeys.getInt(1);
                }
            }
        }
    }

    // Méthode pour mettre à jour le nom d'une liste électorale
    public void update(Connection connex) throws SQLException {
        String updateQuery = "UPDATE Liste_electorale SET nom_liste = ?, bureau_id = ?, CIN = ? WHERE liste_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nomListe);
            preparedStatement.setInt(2, bureauId);
            preparedStatement.setString(3, cin);
            preparedStatement.setInt(4, listeId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer une liste électorale
    public void delete(Connection connex) throws SQLException {
        String deleteQuery = "DELETE FROM Liste_electorale WHERE liste_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, listeId);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour afficher les détails d'une liste électorale
    public void details(Connection connex) throws SQLException {
        String selectQuery = "SELECT * FROM Liste_electorale WHERE liste_id = ?";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, listeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Remplir les attributs avec les données de la base de données
                    this.listeId = resultSet.getInt("liste_id");
                    this.nomListe = resultSet.getString("nom_liste");
                    this.bureauId = resultSet.getInt("bureau_id");
                    this.cin = resultSet.getString("cin");
                }
            }
        }
    }

    public static List<ListeElectorale> getAllLists(Connection connex) throws SQLException {
        List<ListeElectorale> lists = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM Liste_electorale";
        try (PreparedStatement preparedStatement = connex.prepareStatement(selectAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ListeElectorale list = new ListeElectorale();
                list.setListeId(resultSet.getInt("liste_id"));
                list.setNomListe(resultSet.getString("nom_liste"));
                list.setBureauId(resultSet.getInt("bureau_id"));
                list.setCin(resultSet.getString("cin"));
                lists.add(list);
            }
        }
        return lists;
    }

}
