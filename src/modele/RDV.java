package modele;

import java.sql.Time;
import java.util.Date;

public class RDV {
    private int idRDV;
    private Date dateRDV;
    private Time heure;
    private String motif;
    private int idPatient;
    private int idSpecialiste;
    private int lieu;

    //Constructeur
    public RDV(int idRDV, Date dateRDV, Time heure, String motif, int idPatient, int idSpecialiste, int lieu){
        this.idRDV = idRDV;
        this.dateRDV = dateRDV;
        this.heure = heure;
        this.motif = motif;
        this.idPatient = idPatient;
        this.idSpecialiste = idSpecialiste;
        this.lieu = lieu;
    }

    //getters
    public int getIdRDV() {return idRDV;}
    public Date getDateRDV() {return dateRDV;}
    public Time getHeure() {return heure;}
    public String getMotif() {return motif;}
    public int getIdPatient() {return idPatient;}
    public int getIdSpecialiste() {return idSpecialiste;}
    public int getLieu() {return lieu;}


}
