package com.miniprojet.reclamation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PanneauProfesseur extends AppCompatActivity {

    Button btnReclamRecu ;
 Button btnCherchReclam ;
Button btnReclamReglees;
Button btnHistorique;
    TextView test;
TextView textView22;

    DatabaseHandler db = new DatabaseHandler(this, 1);
    //Panneau principal du prof pour gerer et tt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent b = getIntent();
        final String Login = b.getStringExtra("EXTRA_LOGINP");
        final String MDP = b.getStringExtra("EXTRA_MDPP");
        String gid = db.getGidByLoginPassword(Login,MDP);
        String Corrdinates = db.getNamebyGID(gid);

        setContentView(R.layout.activity_panneau_professeur);
        btnReclamRecu=findViewById(R.id.btnReclamRecu);
        btnReclamReglees=findViewById(R.id.btnReclamReglees);
        btnHistorique=findViewById(R.id.btnHistorique);
        btnCherchReclam=findViewById(R.id.btnCherchReclam);
        test= (TextView) findViewById(R.id.test);
        textView22=findViewById(R.id.textView22);
        textView22.setText("Bienvenue "+Corrdinates+"");
        btnReclamRecu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauProfesseur.this,ConsulterListeReclamProfesseur.class);
                b.putExtra("EXTRA_LOGINP",Login);
                b.putExtra("EXTRA_MDPP",MDP);
                startActivity(b);
            }
        });

        btnCherchReclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauProfesseur.this,DateChercherReclam.class);
                b.putExtra("EXTRA_LOGINP",Login);
                b.putExtra("EXTRA_MDPP",MDP);
                b.putExtra("EXTRA_CALL","prof");
                startActivity(b);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(PanneauProfesseur.this,ConnexInscription.class);
                startActivity(b);
            }
        });

        btnReclamReglees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauProfesseur.this,ReclamRegleeProf.class);
                b.putExtra("EXTRA_LOGINP",Login);
                b.putExtra("EXTRA_MDPP",MDP);
                startActivity(b);
            }
        });
        btnHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauProfesseur.this,Historique.class);
                b.putExtra("EXTRA_LOGINP",Login);
                b.putExtra("EXTRA_MDPP",MDP);
                startActivity(b);
            }
        });

    }
}
