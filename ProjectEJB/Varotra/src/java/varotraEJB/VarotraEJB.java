/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varotraEJB;

import java.sql.Date;
import javax.ejb.Local;
import varotraEntity.Mutation;

/**
 *
 * @author ismae
 */
@Local
public interface VarotraEJB {
    void createMutation(Mutation mutation);
    void updateMutation(Mutation mutation);
    void deleteMutation(String idMutation);
    Mutation detailsOfMutation(String idMutation);
    
    Mutation[] getAllSellsOf(String IdOlona); 
    Mutation[] getAllSellsMadeBy(String IdOlona); 
    Mutation[] getAll(); 
    
    void createMultipleMutation(String[] omby, String acheteur, String vendeur, Date date);
}
