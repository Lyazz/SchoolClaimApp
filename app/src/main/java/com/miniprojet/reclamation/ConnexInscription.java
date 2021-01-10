package com.miniprojet.reclamation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConnexInscription extends AppCompatActivity {


    Button btnChoixCon;
    Button btnChoixInsc;
    TextView test;
    DatabaseHandler db = new DatabaseHandler(this, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*On recupere les composants de l'activité*/
        setContentView(R.layout.activity_connex_inscription);
        btnChoixCon=findViewById(R.id.btnChoixCon);
        btnChoixInsc= findViewById(R.id.btnChoixInsc);
        test= (TextView) findViewById(R.id.textView12);



        /*Action sur le bouton Inscirption*/
        btnChoixInsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(ConnexInscription.this,InscriptionActivity.class);
                startActivity(b);
            }
        });
        /*Action sur le bouton Connexion */
        btnChoixCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(ConnexInscription.this,AccueilActivity.class);
                startActivity(b);
            }
        });

        /*Action sur le bouton A propos*/
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(ConnexInscription.this,About.class);
                startActivity(b);
            }
        });
    }
}
