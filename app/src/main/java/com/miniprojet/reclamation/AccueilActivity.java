package com.miniprojet.reclamation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccueilActivity extends AppCompatActivity {


    Button btnEtudiant;
    Button buttonProfesseur;
    Button buttonAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        btnEtudiant=findViewById(R.id.btnEtudiant);
        buttonProfesseur= findViewById(R.id.buttonProfesseur);
        buttonAdmin= findViewById(R.id.buttonAdmin);

        /*Action sur le button Administrateur*/
        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(AccueilActivity.this,MainGestionnaireActivity.class);
                startActivity(b);
            }
        });

        /*Action sur le button Professeur*/
        buttonProfesseur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(AccueilActivity.this,MainActivity.class);
                startActivity(b);
            }
        });
        /*Action sur le button ETudiant*/
        btnEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(AccueilActivity.this,MainEtudiant.class);
                startActivity(b);
            }
        });


    }
}
