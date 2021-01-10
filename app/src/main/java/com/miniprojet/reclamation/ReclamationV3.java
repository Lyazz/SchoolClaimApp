package com.miniprojet.reclamation;

public class ReclamationV3 {

    private String rid;
    private String contenu;
    private String date_heure;
    private String etat;
    private String nomp;
    private String prenomp;

    public ReclamationV3(String rid, String contenu, String date_heure, String etat, String nomp, String prenomp) {
        this.rid = rid;
        this.contenu = contenu;
        this.date_heure = date_heure;
        this.etat = etat;
        this.nomp = nomp;
        this.prenomp = prenomp;
    }


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(String date_heure) {
        this.date_heure = date_heure;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getPrenomp() {
        return prenomp;
    }

    public void setPrenomp(String prenomp) {
        this.prenomp = prenomp;
    }
}


