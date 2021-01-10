package com.miniprojet.reclamation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class PanneauEtudiant extends AppCompatActivity {
    Button btnEcrireReclamation;
    Button btnConsulterHistoriqueEtud;
    TextView test;
    Button button5;
    Button button10;

    DatabaseHandler db = new DatabaseHandler(this, 1);
//Panneau principal de l'etudiant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent b = getIntent();
        final String Login = b.getStringExtra("EXTRA_LOGIN");
        final String MDP = b.getStringExtra("EXTRA_MDP");

        setContentView(R.layout.activity_panneau_etudiant);

        btnEcrireReclamation=findViewById(R.id.btnEcrireReclamation);
        button5=findViewById(R.id.button5);
        button10=findViewById(R.id.button10);
        btnConsulterHistoriqueEtud=findViewById(R.id.btnConsulterHistoriqueEtud);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        test= (TextView) findViewById(R.id.test);
       String eid=db.getEidByLoginPassword(Login,MDP);
        String name =db.getNamebyEID(eid);
        textView2.setText("Bienvenue "+name+"");

        btnEcrireReclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauEtudiant.this,EcrireReclamation.class);
                b.putExtra("EXTRA_LOGIN",Login);
                b.putExtra("EXTRA_MDP",MDP);
                    startActivity(b);


            }
        });

        btnConsulterHistoriqueEtud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauEtudiant.this,ConsulterListeReclamEtudiant.class);
                b.putExtra("EXTRA_LOGIN",Login);
                b.putExtra("EXTRA_MDP",MDP);
                startActivity(b);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauEtudiant.this,Historique.class);
                b.putExtra("EXTRA_LOGINP",Login);
                startActivity(b);

            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent b =  new Intent(PanneauEtudiant.this,DateChercherReclam.class);
                b.putExtra("EXTRA_LOGINP",Login);
                b.putExtra("EXTRA_MDPP",MDP);
                b.putExtra("EXTRA_CALL","etud");
                startActivity(b);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(PanneauEtudiant.this,ConnexInscription.class);
                startActivity(b);
            }
        });
    }
}
