package com.miniprojet.reclamation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class GererUsers extends AppCompatActivity {
    Spinner spinner2;
    Spinner spinner3;
    Button button8;
    Button button9;
    DatabaseHandler db = new DatabaseHandler(this, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_users);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        List<Professeur> profs = db.getAllProfesseurs();
        List<Etudiant> etuds = db.getAllStudents();

        ArrayAdapter<Professeur> dataAdapter = new ArrayAdapter<Professeur>(this, android.R.layout.simple_spinner_item, profs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

        ArrayAdapter<Etudiant> dataAdapter2 = new ArrayAdapter<Etudiant>(this, android.R.layout.simple_spinner_item, etuds);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter2);
        /*ACtion sur bouton qui nous envoie vers une autre activité*/
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Professeur profselected = (Professeur) spinner2.getSelectedItem();
                 String login=profselected.getPseudo();
                 String mdp=profselected.getMdp();
                String gid = db.getGidByLoginPassword(login,mdp);
                db.suppProfesseur(gid);
                finish();
                startActivity(getIntent());


            }
        });


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Etudiant etudselected = (Etudiant) spinner3.getSelectedItem();
                 String login1=etudselected.getMat();
                 String mdp2=etudselected.getMdp();
                String eid = db.getEidByLoginPassword(login1,mdp2);
                db.suppEtudiant(eid);
                finish();
                startActivity(getIntent());


            }
        });
    }
}