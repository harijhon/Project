/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectionUtils;

import Util.MyCon;
import java.sql.Connection;
import myEntity.Bureau;
import myException.ElectionException;

/**
 *
 * @author RazOnTheFloor
 */
public class BureauUtils {
    static String checkNonValiditer(Connection con,int id,int idDistrict) throws Exception {
        if(Bureau.findByID(con,id) == null){ 
            return " Bureau de vote non existant";
        } 
        return "";
    }
    static String checkValiditer(Connection con,int id)throws Exception {
        if(Bureau.findByID(con,id) == null){ 
            return " Bureau de vote deja existant";
        } 
        return "";
    }
    static String checkLogique(int total, int blanc, int valide,int id){
        String error = "";
        
        if(Bureau.getNombreDeVoixTotal(id)< total){
            error+= "Nombre de voix total invalide";
        }
        if(total != (blanc + valide)){
            error+= "Anomalie entre le nombre de vote valide et de vote blanc";
        }
        return error;
    }
    
    public static void checkDataAndSend(int id,int idDistrict,int total, int blanc, int valide, String nom)throws Exception{
        Connection con = null;
        try{
            con = MyCon.getConnection();
            String error ="";
            error+=checkNonValiditer(con,id,idDistrict);
            error += checkLogique(total,blanc,valide,id);
            if (error.equalsIgnoreCase("")) {
                throw new ElectionException(error);
            }
            Bureau.create(con, id, nom, idDistrict, Bureau.getNombreDeVoixTotal(id), total, blanc, valide);
        }catch(Exception e){
            throw e;
        }finally{
            if(con!=null){
                con.close();
            }
        }
    }
    
}
