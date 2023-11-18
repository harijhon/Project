/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varotraEntity;

import Util.MyCon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismae
 */
public class Mutation extends Mere {

    public static Mutation[] getSellsOf(String idOlona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    String idMutation;
    String idOmby;
    String idMpividy;
    String idMpivarotra;
    Date dateMutation;
    String lieu;

    public Mutation(String idOmby, String idMpividy, String idMpivarotra, Date dateMutation, String lieu) {
        this.idOmby = idOmby;
        this.idMpividy = idMpividy;
        this.idMpivarotra = idMpivarotra;
        this.dateMutation = dateMutation;
        this.lieu = lieu;
    }

    public Mutation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdMutation() {
        return idMutation;
    }

    public void setIdMutation(String idMutation) {
        this.idMutation = idMutation;
    }

    public String getIdOmby() {
        return idOmby;
    }

    public void setIdOmby(String idOmby) {
        this.idOmby = idOmby;
    }

    public String getIdMpividy() {
        return idMpividy;
    }

    public void setIdMpividy(String idMpividy) {
        this.idMpividy = idMpividy;
    }

    public String getIdMpivarotra() {
        return idMpivarotra;
    }

    public void setIdMpivarotra(String idMpivarotra) {
        this.idMpivarotra = idMpivarotra;
    }

    public Date getDateMutation() {
        return dateMutation;
    }

    public void setDateMutation(Date dateMutation) {
        this.dateMutation = dateMutation;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    
    @Override
    public void create() throws Exception {
        try {
            Connection connection = MyCon.getConnection(); // Get a database connection from MyCon

            // Prepare an INSERT statement to add the new mutation record
            String query = "INSERT INTO mutation (idOmby, idMpividy, idMpivarotra, dateMutation, lieu) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idOmby);
            statement.setString(2, idMpividy);
            statement.setString(3, idMpivarotra);
            statement.setDate(4, new java.sql.Date(dateMutation.getTime()));
            statement.setString(5, lieu);

            // Execute the SQL statement to insert the record
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new Exception("Error creating Mutation: " + e.getMessage());
        }
    }


    @Override
   public void update() throws Exception {
        try {
            Connection connection = MyCon.getConnection(); // Get a database connection from MyCon

            // Prepare an UPDATE statement to modify the mutation record based on idMutation
            String query = "UPDATE mutation SET idOmby=?, idMpividy=?, idMpivarotra=?, dateMutation=?, lieu=? WHERE idMutation=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idOmby);
            statement.setString(2, idMpividy);
            statement.setString(3, idMpivarotra);
            statement.setDate(4, new java.sql.Date(dateMutation.getTime()));
            statement.setString(5, lieu);
            statement.setString(6, idMutation);

            // Execute the SQL statement to update the record
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new Exception("Error updating Mutation: " + e.getMessage());
        }
    }



    public static void delete(String id) throws Exception {
        try {
            Connection connection = MyCon.getConnection(); // Get a database connection from MyCon

            // Prepare a DELETE statement to remove the mutation record based on idMutation
            String query = "DELETE FROM mutation WHERE idMutation=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);

            // Execute the SQL statement to delete the record
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new Exception("Error deleting Mutation: " + e.getMessage());
        }
    }


    @Override
    public void details() throws Exception {
        try {
            Connection connection = MyCon.getConnection(); // Get a database connection from MyCon

            // Prepare a SELECT statement to fetch the details of the mutation record based on idMutation
            String query = "SELECT idOmby, idMpividy, idMpivarotra, dateMutation, lieu FROM mutation WHERE idMutation=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idMutation);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idOmby = resultSet.getString("idOmby");
                idMpividy = resultSet.getString("idMpividy");
                idMpivarotra = resultSet.getString("idMpivarotra");
                dateMutation = resultSet.getDate("dateMutation");
                lieu = resultSet.getString("lieu");

                // You can print or display the details as needed
                System.out.println("Omby ID: " + idOmby);
                System.out.println("Mpividy ID: " + idMpividy);
                System.out.println("Mpivarotra ID: " + idMpivarotra);
                System.out.println("Date of Mutation: " + dateMutation);
                System.out.println("Lieu: " + lieu);
            } else {
                throw new Exception("Mutation not found in the database.");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new Exception("Error fetching Mutation details: " + e.getMessage());
        }
    }

    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Mutation[] getAllSellsOf(String idOlona) {
    // Créez une liste pour stocker les mutations associées à IdOlona
    List<Mutation> mutationList = new ArrayList<>();

    try {
        // Établissez une connexion à la base de données (vous devez adapter cette partie à votre système de base de données)
        Connection connection = MyCon.getConnection(); // Suppose que MyCon gère la connexion à la base de données

        // Préparez une requête SQL SELECT pour récupérer les mutations liées à l'IdOlona
        String selectQuery = "SELECT * FROM mutation WHERE idOlona = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, idOlona);

        // Exécutez la requête et récupérez les résultats
        ResultSet resultSet = statement.executeQuery();

        // Parcourez les résultats et ajoutez-les à la liste des mutations
        while (resultSet.next()) {
            String idMutation = resultSet.getString("idMutation");
            String idOmby = resultSet.getString("idOmby");
            String idMpividy = resultSet.getString("idMpividy");
            String idMpivarotra = resultSet.getString("idMpivarotra");
            Date dateMutation = resultSet.getDate("dateMutation");
            String lieu = resultSet.getString("lieu");

            Mutation mutation = new Mutation(idOmby, idMpividy, idMpivarotra, dateMutation, lieu);
            mutation.setIdMutation(idMutation);
            mutationList.add(mutation);
        }

        // Fermez les ressources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Gérez l'exception de manière appropriée, par exemple, loggez-la
    }

    // Convertissez la liste de mutations en un tableau
    return mutationList.toArray(new Mutation[0]);
    
    
}
    public static Mutation[] getAllSellsMadeBy(String idMpivarotra) {
    List<Mutation> mutationList = new ArrayList<>();

    try {
        Connection connection = MyCon.getConnection(); // Assurez-vous que MyCon gère la connexion à la base de données

        String selectQuery = "SELECT * FROM mutation WHERE idMpivarotra = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, idMpivarotra);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String idMutation = resultSet.getString("idMutation");
            String idOmby = resultSet.getString("idOmby");
            String idMpividy = resultSet.getString("idMpividy");
            Date dateMutation = resultSet.getDate("dateMutation");
            String lieu = resultSet.getString("lieu");

            Mutation mutation = new Mutation(idOmby, idMpividy, idMpivarotra, dateMutation, lieu);
            mutation.setIdMutation(idMutation);
            mutationList.add(mutation);
        }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Gérez l'exception de manière appropriée
    }

    return mutationList.toArray(new Mutation[0]);
}
    
    public static void createMultipleMutation(String[] omby, String acheteur, String vendeur, Date date) {
    try {
        Connection connection = MyCon.getConnection(); // Assurez-vous que MyCon gère la connexion à la base de données

        String insertQuery = "INSERT INTO mutation (idOmby, idMpividy, idMpivarotra, dateMutation, lieu) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);

        for (String ombyId : omby) {
            statement.setString(1, ombyId);
            statement.setString(2, acheteur);
            statement.setString(3, vendeur);
            statement.setDate(4, new java.sql.Date(date.getTime()));
            statement.setString(5, "Lieu_de_la_Mutation"); // Remplacez par le lieu approprié

            statement.addBatch(); // Ajoutez la requête à un lot pour l'exécution en une seule transaction
        }

        statement.executeBatch(); // Exécute le lot de requêtes

        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Gérez l'exception de manière appropriée
    }
}
    public static Mutation[] getAll() {
    List<Mutation> mutationList = new ArrayList<>();

    try {
        Connection connection = MyCon.getConnection(); // Assurez-vous que MyCon gère la connexion à la base de données

        String selectQuery = "SELECT * FROM mutation";
        PreparedStatement statement = connection.prepareStatement(selectQuery);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String idMutation = resultSet.getString("idMutation");
            String idOmby = resultSet.getString("idOmby");
            String idMpividy = resultSet.getString("idMpividy");
            String idMpivarotra = resultSet.getString("idMpivarotra");
            Date dateMutation = resultSet.getDate("dateMutation");
            String lieu = resultSet.getString("lieu");

            Mutation mutation = new Mutation(idOmby, idMpividy, idMpivarotra, dateMutation, lieu);
            mutation.setIdMutation(idMutation);
            mutationList.add(mutation);
        }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Gérez l'exception de manière appropriée
    }

    return mutationList.toArray(new Mutation[0]);
}





}
