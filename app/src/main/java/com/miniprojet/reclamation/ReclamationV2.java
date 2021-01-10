package com.miniprojet.reclamation;

public class ReclamationV2 {
    private String contenu;
    private String etat;
    private String rid;
    private String date_heure;
    private String eid;

    public ReclamationV2(String contenu, String etat,String rid,String date_heure,String eid) {
        this.contenu = contenu;
        this.etat = etat;
        this.rid=rid;
        this.date_heure=date_heure;
        this.eid=eid;

    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }


    public String getDate() {
        return date_heure;
    }

    public void setDate(String date_heure) {
        this.date_heure = date_heure;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    @Override
    public String toString(){

        return contenu;
    }



}
