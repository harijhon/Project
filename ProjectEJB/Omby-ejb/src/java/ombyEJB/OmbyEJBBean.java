/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEJB;

import java.util.Date;
import javax.ejb.Stateless;
import ombyEntity.Localisation;
import ombyEntity.Olona;
import ombyEntity.Omby;

/**
 *
 * @author ismae
 */
@Stateless
public class OmbyEJBBean implements OmbyEJB {

    @Override
    public void createOmby(Omby omby) {
    }

    @Override
    public Omby getOmbyById(String idOmby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOmby(Omby omby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOmby(String idOmby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Olona login(String userName, String pswrd) {
        if ("Jean".equals(userName) && pswrd.equals("jean")) {
            Olona olona = new Olona();
            olona.setIdOlona("OL1");
            return olona;
        }
        return null;
    }

    @Override
    public Omby[] getAllOmby(Olona olona) {
        if (olona.getIdOlona().equals("OL1")) {
            Omby[] ombyArray = new Omby[2];
            ombyArray[0] = new Omby("OM2", "Omby1", 100.0);
            ombyArray[1] = new Omby("OM3", "Omby2", 150.0);
            return ombyArray;
        }
        return null;
    }

    @Override
    public void addNewLocalisation(Localisation localisation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAssignementOf(String idOmby) {
    }

    @Override
    public void assignOmbyTo(String[] omby, Olona olona,Date date) {
        if (this.isProprietyOf(omby, olona)) {
            Omby.reassignAll(omby, olona);
        }
    }

    @Override
    public Olona[] getAlOtherUser() {
        Olona[] olona = new Olona[3];
        olona[0] = new Olona("OL2", "Jeanne");
        olona[1] = new Olona("OL3", "Jhon");
        olona[2] = new Olona("OL4", "Jane");
        return olona;
    }

    @Override
    public Olona findById(String id) {
        Olona[] olona = new Olona[3];
        olona[0] = new Olona("OL2", "Jeanne");
        olona[1] = new Olona("OL3", "Jhon");
        olona[2] = new Olona("OL4", "Jane");
        for (Olona o : olona) {
            if (o.getIdOlona().equals(id)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public boolean isProprietyOf(String[] ombyIdTab, Olona olona) {
        return true;
    }

    @Override
    public Localisation[] getAll() {
        Localisation[] lieus = new Localisation[3];
        lieus[0] = new Localisation("LOC1","Tana");
        lieus[1] = new Localisation("LOC2","Tamatave");
        lieus[2] = new Localisation("LOC3","Fianaratsoa");
        return lieus;
    }

    @Override
    public void findAt(String idOmby, String idLocalisation) {
    }

}
