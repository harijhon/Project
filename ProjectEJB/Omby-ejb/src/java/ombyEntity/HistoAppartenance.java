/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEntity;

import Util.MyCon;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class HistoAppartenance extends Mere implements Serializable {
//    Attributs
    private String idAppartenance;
    private Omby omby;
    private Olona olona;
    private Date dateDebut;

    public HistoAppartenance(String idAppartenance, Omby omby, Olona olona, Date dateDebut) {
        this.idAppartenance = idAppartenance;
        this.omby = omby;
        this.olona = olona;
        this.dateDebut = dateDebut;
    }

    public HistoAppartenance(String omby, Olona olona, Date dateDebut) {
        this.omby = new Omby();
        this.omby.setIdOmby(omby);
        this.olona = olona;
        this.dateDebut = dateDebut;
    }

    public String getIdAppartenance() {
        return idAppartenance;
    }

    public void setIdAppartenance(String idAppartenance) {
        this.idAppartenance = idAppartenance;
    }

    public Omby getOmby() {
        return omby;
    }

    public void setOmby(Omby omby) {
        this.omby = omby;
    }

    public Olona getOlona() {
        return olona;
    }

    public void setOlona(Olona olona) {
        this.olona = olona;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    
    
    
    @Override
void create() throws Exception {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        
        // Prepare an INSERT statement to add the new historical record
        String query = "INSERT INTO histo_appartenance (idAppartenance, omby_id, olona_id, dateDebut) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idAppartenance);
        statement.setString(2, omby.getIdOmby()); // Assuming getIdOmby() returns the Omby ID
        statement.setString(3, olona.getIdOlona()); // Assuming getIdOlona() returns the Olona ID
        statement.setDate(4, new java.sql.Date(dateDebut.getTime()));
        
        // Execute the SQL statement to insert the record
        statement.executeUpdate();
        
        statement.close();
    } catch (SQLException e) {
        throw new Exception("Error creating HistoAppartenance: " + e.getMessage());
    }
}


    @Override
void update() throws Exception {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        
        // Prepare an UPDATE statement to modify the historical record based on idAppartenance
        String query = "UPDATE histo_appartenance SET omby_id=?, olona_id=?, dateDebut=? WHERE idAppartenance=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, omby.getIdOmby()); // Assuming getIdOmby() returns the Omby ID
        statement.setString(2, olona.getIdOlona()); // Assuming getIdOlona() returns the Olona ID
        statement.setDate(3, new java.sql.Date(dateDebut.getTime()));
        statement.setString(4, idAppartenance);
        
        // Execute the SQL statement to update the record
        statement.executeUpdate();
        
        statement.close();
    } catch (SQLException e) {
        throw new Exception("Error updating HistoAppartenance: " + e.getMessage());
    }
}


    @Override
void delete() throws Exception {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        
        // Prepare a DELETE statement to remove the historical record based on idAppartenance
        String query = "DELETE FROM histo_appartenance WHERE idAppartenance=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idAppartenance);
        
        // Execute the SQL statement to delete the record
        statement.executeUpdate();
        
        statement.close();
    } catch (SQLException e) {
        throw new Exception("Error deleting HistoAppartenance: " + e.getMessage());
    }
}


    @Override
void details() throws Exception {
    try {
        Connection connection = MyCon.getConnection(); // Get a database connection from MyCon
        
        // Prepare a SELECT statement to fetch the details of the historical record based on idAppartenance
        String query = "SELECT omby_id, olona_id, dateDebut FROM histo_appartenance WHERE idAppartenance=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idAppartenance);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            String ombyId = resultSet.getString("omby_id");
            String olonaId = resultSet.getString("olona_id");
            Date startDate = resultSet.getDate("dateDebut");
            
            // You can print or display the details as needed
            System.out.println("Omby ID: " + ombyId);
            System.out.println("Olona ID: " + olonaId);
            System.out.println("Start Date: " + startDate);
        } else {
            throw new Exception("HistoAppartenance not found in the database.");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        throw new Exception("Error fetching HistoAppartenance details: " + e.getMessage());
    }
}

    
}
