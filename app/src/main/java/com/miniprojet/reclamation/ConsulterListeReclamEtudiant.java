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

public class ConsulterListeReclamEtudiant extends AppCompatActivity {
Button retourPanEtudiant;
Button btnRegler;
EditText editext;
    DatabaseHandler db = new DatabaseHandler(this, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*On recupere les composants de l'activité*/
        setContentView(R.layout.activity_consulter_liste_reclam_etudiant);
        btnRegler=findViewById(R.id.btnRegler);
        editext=findViewById(R.id.editText2);
        Intent b = getIntent();
        final String login = b.getStringExtra("EXTRA_LOGIN");
        String mdp = b.getStringExtra("EXTRA_MDP");
        String idd = db.getEidByLoginPassword(login,mdp);
        System.out.println("le idd est "+idd+"");
        List<ReclamationV3> list_ReclamationV3 = db.getReclamationsEtudiantById(idd);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);

        /*On injecte les composants dans l'adaptateur adequat*/

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter2(this,list_ReclamationV3));

        /*Action sur le bouton regler*/
        btnRegler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rid = editext.getText().toString();
                db.suppReclamation(rid);
                String np = db.getNamePbyRID(rid);
                db.AddHistoricSupp(login,rid,np);
                finish();
                startActivity(getIntent());
            }
        });

    }
}
