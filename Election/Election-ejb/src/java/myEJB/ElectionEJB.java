/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myEJB;

import ElectionUtils.BureauUtils;
import Util.MyCon;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import myEntity.Bureau;
import myEntity.Candidat;
import myEntity.District;
import myEntity.Region;

/**
 *
 * @author RazOnTheFloor
 */
@Stateful
public class ElectionEJB implements ElectionEJBLocal {
    List<Bureau> bureau = null;
    List<Candidat> candidat = null;
    List<District> district = null;
    List<Region> region = null;

    @Override
    public void setBureau(int id, String nom, int idDisctrict, int total, int blanc, int valide) throws Exception {
        BureauUtils.checkDataAndSend(id, idDisctrict, total, blanc, valide, nom);
    }
    
    @PostConstruct
    @Override
    public void creatEJB(){
        Connection con = null;
        try {
            con = MyCon.getConnection();
            this.bureau = Bureau.getAllBureaus(con);
            this.candidat = Candidat.getAll(con);
            this.region = Region.getAll(con);
            this.district = District.getAll(con);
        } catch (SQLException ex) {
            Logger.getLogger(ElectionEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Bureau> getBureau() {
        return this.bureau;
    }

    @Override
    public List<Candidat> getCandidat() {
        return this.candidat;
    }

    @Override
    public List<District> getDistrict() {
        return this.district;
    }

    @Override
    public List<Region> getRegion() {
        return this.region;
    }
    
}
