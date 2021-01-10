package com.miniprojet.reclamation;


/*CLasse de l'etudiant avec toutes ses caracteristique*/
public class Etudiant {
    private String nom;
    private String prenom;
    private String mat;
    private String mdp;

    public Etudiant(String nom, String prenom,String mat, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mat = mat;
        this.mdp=mdp;
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

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString(){

        return ""+nom+" "+prenom+"";
    }

}
