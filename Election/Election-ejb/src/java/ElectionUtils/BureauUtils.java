/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectionUtils;

import myEntity.Bureau;
import myException.ElectionException;

/**
 *
 * @author RazOnTheFloor
 */
public class BureauUtils {
    static String checkNonValiditer(int id){
        if(Bureau.findByID(id)== null){ 
            return " Bureau de vote non existant";
        } 
        return "";
    }
    static String checkValiditer(int id){
        if(Bureau.findByID(id)!= null){ 
            return " Bureau de vote deja existant";
        } 
        return "";
    }
    static String checkLogique(int total, int blanc, int valide){
        String error = "";
        
        if(Bureau.getNombreDeVoixTotal(valide)< total){
            error+= "Nombre de voix total invalide";
        }
        if(total != (blanc + valide)){
            error+= "Anomalie entre le nombre de vote valide et de vote blanc";
        }
        return error;
    }
    
    public static void checkData(int id,int total, int blanc, int valide)throws ElectionException{
        String error ="";
        error+=checkNonValiditer(id);
        error += checkLogique(total,blanc,valide);
        if (error.equalsIgnoreCase("")) {
            throw new ElectionException(error);
        }
    }
    
}
