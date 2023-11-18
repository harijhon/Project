/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEntity;

import java.io.Serializable;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void update() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void delete() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void details() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
