/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEntity;

/**
 *
 * @author ismae
 */
import Util.MyCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Olona extends Mere {
    private String idOlona;
    private String nom;

    public Olona(String idOlona, String nom) {
        this.idOlona = idOlona;
        this.nom = nom;
    }

    public Olona() {
    }

    public String getIdOlona() {
        return idOlona;
    }

    public void setIdOlona(String idOlona) {
        this.idOlona = idOlona;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // CREATE
    @Override
    public void create() throws SQLException {
        try (Connection connection = MyCon.getConnection()) {
            String insertQuery = "INSERT INTO olona (idOlona, nom) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, idOlona);
                preparedStatement.setString(2, nom);
                preparedStatement.executeUpdate();
            }
        }
    }

    // READ (GET ALL)
    public static List<Olona> getAll() throws SQLException {
        List<Olona> olonas = new ArrayList<>();
        try (Connection connection = MyCon.getConnection()) {
            String selectQuery = "SELECT * FROM olona";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Olona olona = new Olona();
                        olona.setIdOlona(resultSet.getString("idOlona"));
                        olona.setNom(resultSet.getString("nom"));
                        olonas.add(olona);
                    }
                }
            }
        }
        return olonas;
    }

    // READ (GET BY ID)
    public static Olona getById(String idOlona) throws SQLException {
        Olona olona = null;
        try (Connection connection = MyCon.getConnection()) {
            String selectQuery = "SELECT * FROM olona WHERE idOlona = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, idOlona);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        olona = new Olona();
                        olona.setIdOlona(resultSet.getString("idOlona"));
                        olona.setNom(resultSet.getString("nom"));
                    }
                }
            }
        }
        return olona;
    }

    // UPDATE
    @Override
    public void update() throws SQLException {
        try (Connection connection = MyCon.getConnection()) {
            String updateQuery = "UPDATE olona SET nom = ? WHERE idOlona = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, idOlona);
                preparedStatement.executeUpdate();
            }
        }
    }

    // DELETE
    @Override
    public void delete() throws SQLException {
        try (Connection connection = MyCon.getConnection()) {
            String deleteQuery = "DELETE FROM olona WHERE idOlona = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, idOlona);
                preparedStatement.executeUpdate();
            }
        }
    }

    // Other methods as needed...

    @Override
    public void details() throws SQLException {
    try (Connection connection = MyCon.getConnection()) {
        String selectQuery = "SELECT * FROM olona WHERE idOlona = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, idOlona);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("ID Olona: " + resultSet.getString("idOlona"));
                    System.out.println("Nom: " + resultSet.getString("nom"));
                    // Ajoutez d'autres détails si nécessaire
                } else {
                    System.out.println("Olona introuvable avec l'ID : " + idOlona);
                }
            }
        }
    }
}

}

