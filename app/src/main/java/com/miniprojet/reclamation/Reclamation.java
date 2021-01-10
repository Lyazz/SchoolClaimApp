package com.miniprojet.reclamation;

public class Reclamation {
    private String contenu;
    private int etat;

    public Reclamation(String contenu, int etat) {
        this.contenu = contenu;
        this.etat = etat;

    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }


    @Override
    public String toString(){

        return ""+contenu+"";
    }



}
