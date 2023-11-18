/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varotraEJB;
import java.sql.Date;
import javax.ejb.Stateless;
import varotraEntity.Mutation;

/**
 *
 * @author ismae
 */
@Stateless
public class VarotraEJBBean implements VarotraEJB {

    @Override
    public void createMutation(Mutation mutation) throws Exception {
      mutation.create();
}

    @Override
    public void updateMutation(Mutation mutation) throws Exception {
        mutation.update();
    }

    @Override
    public void deleteMutation(String idMutation) throws Exception  {
       Mutation mutation = new Mutation();
        mutation.setIdMutation(idMutation); 
        mutation.delete();
    }

    @Override
    public Mutation detailsOfMutation(String idMutation) throws Exception {
        Mutation mutation = new Mutation();
        mutation.setIdMutation(idMutation);
        mutation.details();
        return null;
    }
    
    @Override
    public Mutation[] getAllSellsOf(String idOlona) {
    Mutation[] mutations = Mutation.getAllSellsOf(idOlona);
    return mutations;
}


    @Override
    public Mutation[] getAllSellsMadeBy(String idMpivarotra) {
    return Mutation.getAllSellsMadeBy(idMpivarotra);
}


    @Override
    public void createMultipleMutation(String[] omby, String acheteur, String vendeur, Date date) {
    Mutation.createMultipleMutation(omby, acheteur, vendeur, date);
}


    @Override
    public Mutation[] getAll() {
    return Mutation.getAll();
}

    
}
