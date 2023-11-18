/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varotraEntity;

import java.sql.Date;

/**
 *
 * @author ismae
 */
public class Mutation extends Mere {
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
