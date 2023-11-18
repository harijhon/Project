/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warEntity;

import java.util.Date;

/**
 *
 * @author RazOnTheFloor
 */
public class Plainte {

    int idPlainte;
    String idOmby, lieuPlainte, lieuResolution;
    Date datePlainte, dateResolution;

    public int getIdPlainte() {
        return idPlainte;
    }

    public void setIdPlainte(int idPlainte) {
        this.idPlainte = idPlainte;
    }

    public String getIdOmby() {
        return idOmby;
    }

    public void setIdOmby(String idOmby) {
        this.idOmby = idOmby;
    }

    public String getLieuPlainte() {
        return lieuPlainte;
    }

    public void setLieuPlainte(String lieuPlainte) {
        this.lieuPlainte = lieuPlainte;
    }

    public String getLieuResolution() {
        return lieuResolution;
    }

    public void setLieuResolution(String lieuResolution) {
        this.lieuResolution = lieuResolution;
    }

    public Date getDatePlainte() {
        return datePlainte;
    }

    public void setDatePlainte(Date datePlainte) {
        this.datePlainte = datePlainte;
    }

    public Date getDateResolution() {
        return dateResolution;
    }

    public void setDateResolution(Date dateResolution) {
        this.dateResolution = dateResolution;
    }


}
