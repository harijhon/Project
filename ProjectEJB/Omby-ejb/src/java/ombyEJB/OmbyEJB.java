/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEJB;

import java.sql.SQLException;
import java.util.Date;
import ombyEntity.Localisation;
import ombyEntity.Olona;
import ombyEntity.Omby;
import javax.ejb.Local;

/**
 *
 * @author ismae
 */
@Local
public interface OmbyEJB {
//    omby functions
    void createOmby(Omby omby);
    Omby getOmbyById(String idOmby);
    void updateOmby(Omby omby);
    void deleteOmby(String idOmby);
    void removeAssignementOf(String idOmby);
    boolean isProprietyOf(String[] ombyIdTab,Olona olona);
    void findAt(String idOmby,String idLocalisation);
    
//    Olona functions
    Olona login(String userName,String pswrd);
    void assignOmbyTo(String[] omby,Olona olona,Date date);
    Omby[] getAllOmby(Olona olona);
    Olona[] getAlOtherUser();
    Olona findById(String id);
//    Localisation
    void createLocalisation(Localisation localisation);
    Localisation[] getAll();

    public void handleSQLException(SQLException e);
}
