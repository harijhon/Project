/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectionUtils;

import java.sql.Connection;
import myEntity.Bureau;
import myEntity.Candidat;
import myEntity.Resultat;
import myException.ElectionException;

/**
 *
 * @author RazOnTheFloor
 */
public class ResultatUtils {
    static String checkNegative(Connection con, int resultat){
        if(resultat<0){ return " Valeur negative";}
        return null;
    }
    static String checkBureau(Connection con, int idBureau, int idCandidat){
        String error = "";
        error += BureauUtils.checkValiditer(con,idBureau);
        if(Resultat.findByBureauID(idBureau)!= null){
            error+= " Resultat deja inscrit";
        }if(Candidat.findById(idCandidat)!=null){
            error+= " Candidat non existant";
        }   
        return error;
    }
    static String checkLogique(Connection con, int resultat, int idBureau){
        String error ="";
        if(Bureau.getTotalResteDeVoix(idBureau) < resultat){
            error += " Nombre de voix incoherent";
        }if(Bureau.getNombreDeVoixTotal(idBureau) < resultat ){
                error+= " Nombre de voix superieur aux total de voix";
        }
        return error;
    }
    
    public static void checkData(int idBureau, int idCandidat, int resultat) throws Exception{
        Connection con = null;
        try{
        String error = "";
        error += checkNegative(con,resultat);
        error += checkBureau(con,idBureau,idCandidat);
        error += checkLogique(con,resultat, idBureau);
        if (error.equalsIgnoreCase("")) {
            throw new ElectionException(error);
        }
     }catch(Exception e){
         throw e;
     }finally{
          if (con!= null){
              con.close();
          }
        }       
    }
       
}
