package com.miniprojet.reclamation;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class AfficherReclamCherchee extends AppCompatActivity {

   TextView dateText;
    DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_reclam_cherchee);
        Intent b = getIntent();
        dateText=findViewById(R.id.textView20);
        /*on envoie les variable vers l'activité suivante*/
        final String login = b.getStringExtra("EXTRA_LOGINP");
        final String mdp = b.getStringExtra("EXTRA_MDPP");
        final String date = b.getStringExtra("EXTRA_DATE");
        final String dateT = b.getStringExtra("EXTRA_DATET");
        final String appelant =b.getStringExtra("EXTRA_CALL");
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);
        /*On injecte les valeur dans l'adaptateur de la liste*/
        dateText.setText("Les reclamations à la date "+dateT+" sont:");
        rv.setLayoutManager(new LinearLayoutManager(this));
        /* SI l'activité appelante est admin on affiche toutes les reclamation sinon que celle du prof concerné o ubien de l'etufiant*/
        /*Ca nous permettra de créer qu'une seul activité d'arrivée*/
        if(login.equals("admin")){
            List<ReclamationV4> list =db.GetReclByDateAdmin(date);
            rv.setAdapter(new MyAdapter3(this,list));
        }
        else if(appelant.equals("prof")){
            String id = db.getGidByLoginPassword(login,mdp);
            List<ReclamationV2> list =db.GetReclByDateEid(date,id);
            rv.setAdapter(new MyAdapter(this,list));
        }
        else if(appelant.equals("etud")){
            String id = db.getEidByLoginPassword(login,mdp);
            List<ReclamationV2> list =db.GetReclByDateEtudiant(date,id);
            rv.setAdapter(new MyAdapter(this,list));
        }





    }
}
