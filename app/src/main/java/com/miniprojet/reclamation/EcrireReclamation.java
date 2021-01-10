package com.miniprojet.reclamation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class EcrireReclamation extends AppCompatActivity {
    Spinner spinner;
    Button btnEnvoyerReclam;
        DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecrire_reclamation);
        btnEnvoyerReclam=findViewById(R.id.btnEnvoyerReclam);
        spinner=findViewById(R.id.spinner);

        List<Professeur> profs=db.getAllProfesseurs();

        ArrayAdapter<Professeur> dataAdapter = new ArrayAdapter<Professeur>(this,android.R.layout.simple_spinner_item, profs);
        /*ON affiche une liste deroulante qui contient tous les professeur*/
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        btnEnvoyerReclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    /*COnfirmation de lenvoi BOITE de dialog*/
                new AlertDialog.Builder(btnEnvoyerReclam.getContext())

                        .setMessage("Voulez vous confirmer l'envoi de la reclamation?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                EditText edittext = (EditText) findViewById(R.id.editText6);
                                String reclam = edittext.getText().toString();
                                Intent b = getIntent();
                                String login = b.getStringExtra("EXTRA_LOGIN");
                                String mdp = b.getStringExtra("EXTRA_MDP");

                                Professeur profselected = (Professeur) spinner.getSelectedItem();
                                final String nomaffiche=profselected.getNom();
                                final String prenomaffiche=profselected.getPrenom();

                                String eid=db.GetIdUserDB(login,mdp);
                                Reclamation rec = new Reclamation(reclam,0);
                                db.ajouterReclamation(rec,eid);
                                String rid=db.getRid(reclam,eid);
                                String gid=db.getGid(nomaffiche,prenomaffiche);
                                db.ajouterGerer(rid,gid);

                                Toast toast = Toast.makeText(EcrireReclamation.this, "Vous avez envoyé une reclamation à "+nomaffiche+" "+prenomaffiche+" ", Toast.LENGTH_SHORT);
                                View toastView = toast.getView();
                                toastView.setBackgroundResource(R.color.vert);
                                toast.show();
                                finish();

                            }
                        })
                        /*sinon*/
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();



            }

        });


    }
}
