/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombyEntity;

/**
 *
 * @author ismae
 */
public class Omby extends Mere {

//    Attribut
    private String idOmby;
    private String nom;
    private double poids;
    private Localisation localisation;
    private Olona proprioActuel;
//    Getters and Setters

    public String getIdOmby() {
        return idOmby;
    }

    public void setIdOmby(String idOmby) {
        this.idOmby = idOmby;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Olona getProprioActuel() {
        return proprioActuel;
    }

    public void setProprioActuel(Olona proprioActuel) {
        this.proprioActuel = proprioActuel;
    }

    public Omby(String idOmby, String nom, double poids, Localisation localisation, Olona proprioActuel) {
        this.idOmby = idOmby;
        this.nom = nom;
        this.poids = poids;
        this.localisation = localisation;
        this.proprioActuel = proprioActuel;
    }

    public Omby() {
    }

    public Omby(String idOmby, String nom, double poids) {
        this.idOmby = idOmby;
        this.nom = nom;
        this.poids = poids;
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
    
    public static void reassignAll( String[] omby, Olona olona ){
        
    }
    
}
