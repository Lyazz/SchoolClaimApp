package com.miniprojet.reclamation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this, 1);
    Button btnIscr;
   RadioGroup radio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        btnIscr=findViewById(R.id.btnIscr);

        /*ACtion sur bouton sinscrire*/

        btnIscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio= (RadioGroup)findViewById(R.id.radioGroup);
                 int checked = radio.getCheckedRadioButtonId();
                EditText editText1 = (EditText) findViewById(R.id.champNom);
                EditText editText2 = (EditText) findViewById(R.id.champPrenom);
                EditText editText3 = (EditText) findViewById(R.id.champMat);
                EditText editText4 = (EditText) findViewById(R.id.champMdp);
                EditText editText5 = (EditText) findViewById(R.id.champMdp2);
                final String nom = editText1.getText().toString();
                final String prenom = editText2.getText().toString();
                final String mat = editText3.getText().toString();
                final String mdpp = editText4.getText().toString();
                String mdp2 = editText5.getText().toString();

                /*ON verifie si le login existe deja si retourne une erreur sinon on procede a linsription*/
                Cursor exEtud =db.checkUsersDB(mat,mdpp);
                Cursor exProf=db.checkManagersDB(mat,mdpp);

                if((exEtud.moveToFirst()==true)||(exProf.moveToFirst()==true)){
                    Toast toast = Toast.makeText(InscriptionActivity.this, "Ce login est déja pris", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.color.rouge);
                    toast.show();
                }
               else {// des champs manquent
                    if (nom.isEmpty() || prenom.isEmpty() || mat.isEmpty() || mdpp.isEmpty() || mdp2.isEmpty()) {
                        Toast toast = Toast.makeText(InscriptionActivity.this, "Un ou plusieurs champs sont manquants", Toast.LENGTH_SHORT);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.color.rouge);
                        toast.show();
                    } else {
                        if (mdpp.equals(mdp2)) {
                            if (checked == R.id.RadioEtud) {
//On affiche la boite de daialog pour la confrimation

                                new AlertDialog.Builder(editText1.getContext())

                                        .setMessage("Voulez vous confirmer?").setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Etudiant etud = new Etudiant(nom, prenom, mat, mdpp);
                                        db.ajouterEtudiant(etud);
                                        Toast toast = Toast.makeText(InscriptionActivity.this, "Vous vous etes inscrit(e) en tant qu'étudiant", Toast.LENGTH_SHORT);
                                        View toastView = toast.getView();
                                        toastView.setBackgroundResource(R.color.vert);
                                        toast.show();
                                        Intent b = new Intent(InscriptionActivity.this, AccueilActivity.class);
                                        startActivity(b);
                                    }
                                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).show();


                            } else {

//On affiche la boite de daialog pour la confrimation
                                new AlertDialog.Builder(editText1.getContext())

                                        .setMessage("Voulez vous confirmer?").setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Professeur prof = new Professeur(mat, nom, prenom, "prof", mdpp);
                                        db.ajouterProfesseur(prof);
                                        Toast toast = Toast.makeText(InscriptionActivity.this, "Vous vous etes inscrit(e) en tant que professeur", Toast.LENGTH_SHORT);
                                        View toastView = toast.getView();
                                        toastView.setBackgroundResource(R.color.vert);
                                        toast.show();
                                        Intent b = new Intent(InscriptionActivity.this, AccueilActivity.class);
                                        startActivity(b);
                                    }
                                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).show();


                            }
                        } else {//afficher msg d'erreur les mdp ne corespondent pas
                            Toast toast = Toast.makeText(InscriptionActivity.this, "Les mots de passe ne sont pas identiques", Toast.LENGTH_SHORT);
                            View toastView = toast.getView();
                            toastView.setBackgroundResource(R.color.rouge);
                            toast.show();
                        }
                    }
                }           }

        });
    }

}
