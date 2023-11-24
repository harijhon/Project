/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myEJB;

import ElectionUtils.BureauUtils;
import java.util.List;
import javax.ejb.Local;
import myEntity.*;

/**
 *
 * @author RazOnTheFloor
 */
@Local
public interface ElectionEJBLocal {
    public abstract void setBureau(int id, String nom, int idDisctrict,int total,int blanc,int valide )throws Exception;
    public abstract List<Bureau> getBureau();
    public abstract List<Candidat> getCandidat();
    public abstract List<District> getDistrict();
    public abstract List<Region> getRegion();

    public void creatEJB();
}
