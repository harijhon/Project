/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import myEntity.Bureau;
import myException.ElectionException;


public class BureauUtils {
    static String checkNonValiditer(Connection con,int id){
        if(Bureau.findByID(id)== null){ 
            return " Bureau de vote non existant";
        } 
        return "";
    }
    static String checkValiditer(Connection con,int id){
        if(Bureau.findByID(id)!= null){ 
            return " Bureau de vote deja existant";
        } 
        return "";
    }
    static String checkLogique(Connection con,int total, int blanc, int valide){
        String error = "";
        
        if(Bureau.getNombreDeVoixTotal(valide)< total){
            error+= "Nombre de voix total invalide"
                    + "";
        }
        if(total != (blanc + valide)){
            error+= "Anomalie entre le nombre de vote valide et de vote blanc";
        }
        return error;
    }
    
    public static void checkData(int id,int total, int blanc, int valide)throws Exception{
        Connection con = null;
        try{
            String error ="";
            error+=checkNonValiditer(con,id);
            error += checkLogique(con,total,blanc,valide);
            if (error.equalsIgnoreCase("")) {
                throw new ElectionException(error);
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(con!= null){
                con.close();
            }
            
        }
    }
    
    
    
}
