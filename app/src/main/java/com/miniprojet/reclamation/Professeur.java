package com.miniprojet.reclamation;

    public class Professeur {
        private String pseudo;
        private String nom;
        private String prenom;
        private String type;
        private String mdp;

        public Professeur(String pseudo,String nom, String prenom,String type, String mdp) {
            this.nom = nom;
            this.pseudo=pseudo;
            this.prenom = prenom;
            this.type = type;
            this.mdp=mdp;
        }


        public String getPseudo() {
            return pseudo;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
