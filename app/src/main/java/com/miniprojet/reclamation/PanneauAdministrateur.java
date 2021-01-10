package com.miniprojet.reclamation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PanneauAdministrateur extends AppCompatActivity {
    Button btnAffStats;
    Button btnChercherReclam;
    TextView test;
    Button btnGererReclams;
    Button button7;
    DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panneau_administrateur);
        btnAffStats=findViewById(R.id.btnAffStats);
        btnChercherReclam=findViewById(R.id.btnChercherReclam);
        btnGererReclams=findViewById(R.id.button);
        button7=findViewById(R.id.button7);
        test= (TextView) findViewById(R.id.test);
//Panneau principal de l'admin pour gerer et tt
        int rr=db.getNbResolved();
        int nn=db.getNbNotResolved();
        final String rrr="Le nombre de reclamations resolues est "+rr+"";
        final String nnn="Le nombre de reclamations non resolues est "+nn+"";

        btnAffStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(v.getContext())

                        .setTitle("Statistiques")
                        .setMessage(""+rrr+"\n"+nnn+"")
                        .setNegativeButton("Masquer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });

        btnChercherReclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauAdministrateur.this,DateChercherReclam.class);
                b.putExtra("EXTRA_LOGINP","admin");
                startActivity(b);

            }
        });

        btnGererReclams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauAdministrateur.this,GererReclams.class);
                startActivity(b);

            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(PanneauAdministrateur.this,ConnexInscription.class);
                startActivity(b);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauAdministrateur.this,GererUsers.class);
                startActivity(b);

            }
        });

    }
}
