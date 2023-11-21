/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectionUtils;

import myEntity.Bureau;
import myEntity.Candidat;
import myEntity.Resultat;
import myException.ElectionException;

/**
 *
 * @author RazOnTheFloor
 */
public class ResultatUtils {
    static String checkNegative(int resultat){
        if(resultat<0){ return " Valeur negative";}
        return null;
    }
    static String checkBureau(int idBureau, int idCandidat){
        String error = "";
        error += BureauUtils.checkValiditer(idBureau);
        if(Resultat.findByBureauID(idBureau)!= null){
            error+= " Resultat deja inscrit";
        }if(Candidat.findById(idCandidat)!=null){
            error+= " Candidat non existant";
        }
        return error;
    }
    static String checkLogique(int resultat, int idBureau){
        String error ="";
        if(Bureau.getTotalResteDeVoix(idBureau) < resultat){
            error += " Nombre de voix incoherent";
        }if(Bureau.getNombreDeVoixTotal(idBureau) < resultat ){
                error+= " Nombre de voix superieur aux total de voix";
        }
        return error;
    }
    
    public static void checkData(int idBureau, int idCandidat, int resultat) throws ElectionException{
        String error = "";
        error += checkNegative(resultat);
        error += checkBureau(idBureau,idCandidat);
        error += checkLogique(resultat, idBureau);
        if (error.equalsIgnoreCase("")) {
            throw new ElectionException(error);
        }
    }
}
