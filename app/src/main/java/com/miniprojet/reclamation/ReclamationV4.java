package com.miniprojet.reclamation;

public class ReclamationV4 {

    private String rid;
    private String contenu;
    private String date_heure;
    private String etat;
    private String nomp;
    private String prenomp;
    private String date_heure_modif;
    private String gid;
    private String nom;
    private String prenom;

    public ReclamationV4(String rid, String contenu, String date_heure, String etat, String nomp, String prenomp, String date_heure_modif, String gid, String nom, String prenom) {
        this.rid = rid;
        this.contenu = contenu;
        this.date_heure = date_heure;
        this.etat = etat;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.date_heure_modif = date_heure_modif;
        this.gid = gid;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getDate_heure_modif() {
        return date_heure_modif;
    }

    public void setDate_heure_modif(String date_heure_modif) {
        this.date_heure_modif = date_heure_modif;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}


