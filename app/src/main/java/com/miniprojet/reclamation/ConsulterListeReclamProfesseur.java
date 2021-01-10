package com.miniprojet.reclamation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ConsulterListeReclamProfesseur extends AppCompatActivity {
    Button retourPanEtudiant;
    Button btnRegler;
    EditText editext;
    DatabaseHandler db = new DatabaseHandler(this, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Action sur le bouton Inscirption*/

        setContentView(R.layout.activity_consulter_liste_reclam_professeur);
        btnRegler=findViewById(R.id.btnRegler);
        editext=findViewById(R.id.editText2);
        /*On envoie les variables vers l'activité suivante*/
        Intent b = getIntent();
        final String login = b.getStringExtra("EXTRA_LOGINP");
        String mdp = b.getStringExtra("EXTRA_MDPP");
        String gid = db.getGidByLoginPassword(login,mdp);
        List<ReclamationV2> list_ReclamationV2 = db.getReclamationsProfesseurById(gid);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        /*On injecte les composant sur l'adaptateur pour afficher la liste des reclam du professeur*/
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(this,list_ReclamationV2));

        /*Action sur le bouton Regler*/
        btnRegler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rid = editext.getText().toString();
                db.AddHistoricRegler(login,rid);
                db.ReglerReclamation(rid);
                finish();
            startActivity(getIntent());
            }
        });

    }
}
