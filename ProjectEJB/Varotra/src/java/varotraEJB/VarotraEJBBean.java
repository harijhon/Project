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
    public void createMutation(Mutation mutation) {
    }

    @Override
    public void updateMutation(Mutation mutation) {
    }

    @Override
    public void deleteMutation(String idMutation) {
    }

    @Override
    public Mutation detailsOfMutation(String idMutation) {
        return new Mutation("MUT3","OL1","OL3",Date.valueOf("2023-11-05"),"LOC1");
    }

    @Override
    public Mutation[] getAllSellsOf(String IdOlona) {
        Mutation[] m = new Mutation[3];
        m[0]=new Mutation("MUT1","OL2","OL1",Date.valueOf("2023-11-05"),"LOC1");
        m[1]=new Mutation("MUT2","OL2","OL1",Date.valueOf("2023-11-05"),"LOC1");
        m[2]=new Mutation("MUT3","OL1","OL3",Date.valueOf("2023-11-05"),"LOC1");
        return m;
    }

    @Override
    public Mutation[] getAllSellsMadeBy(String IdOlona) {
        Mutation[] m = new Mutation[3];
        m[0]=new Mutation("MUT1","OL2","OL1",Date.valueOf("2023-11-05"),"LOC1");
        m[1]=new Mutation("MUT2","OL2","OL1",Date.valueOf("2023-11-05"),"LOC1");
        m[2]=new Mutation("MUT3","OL1","OL3",Date.valueOf("2023-11-05"),"LOC1");
        return m;
    }

    @Override
    public void createMultipleMutation(String[] omby, String acheteur, String vendeur, Date date) {
    }

    @Override
    public Mutation[] getAll() {
        Mutation[] m = new Mutation[3];
        m[0]=new Mutation("MUT1","OL2","OL1",Date.valueOf("2023-11-05"),"LOC1");
        m[1]=new Mutation("MUT2","OL2","OL1",Date.valueOf("2023-11-05"),"LOC1");
        m[2]=new Mutation("MUT3","OL1","OL3",Date.valueOf("2023-11-05"),"LOC1");
        return m;
    }
    
}
